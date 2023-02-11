package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinDeudaDTO;
import com.restapi.siscondominio.financiero.business.dto.FinPagoDTO;
import com.restapi.siscondominio.financiero.business.dto.FinReciboDTO;
import com.restapi.siscondominio.financiero.business.dto.RegistroPagosDTO;
import com.restapi.siscondominio.financiero.business.exceptions.IncorrectValueException;
import com.restapi.siscondominio.financiero.business.exceptions.ResourceNotFoundException;
import com.restapi.siscondominio.financiero.business.speficications.FinReciboSpecification;
import com.restapi.siscondominio.financiero.business.vo.FinPagoVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeuda;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeudaPago;
import com.restapi.siscondominio.financiero.persistence.entities.FinPago;
import com.restapi.siscondominio.financiero.persistence.entities.FinRecibo;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaPagoRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinReciboRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FinPagoService extends Servicio<FinPago, FinPagoDTO> {

    @Autowired
    private FinPagoRepository finPagoRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    @Autowired
    private FinDeudaService finDeudaService;
    @Autowired
    private FinReciboRepository finReciboRepository;
    @Autowired
    private FinDeudaPagoRepository finDeudaPagoRepository;
    @Autowired
    private FinDeudaRepository finDeudaRepository;

    public Page<FinPagoDTO> getAll(@NotNull Integer size, Integer page) {
        Sort sorter = Sort.by("pagFecha").descending();
        return toPageDTO(finPagoRepository.findAll(PageRequest.of(page, size, sorter)));
    }

    public List<FinPagoDTO> getAll() {
        Sort sorter = Sort.by("pagFecha").descending();
        return toListDTO(finPagoRepository.findAll(sorter));
    }

    public FinPagoDTO getById(Long id) {
        FinPago original = requireOne(id);
        return toDTO(original);
    }

    public Long save(FinPagoVO vO) {
        FinPago bean = new FinPago();
        BeanUtils.copyProperties(vO, bean);
        bean = finPagoRepository.save(bean);
        return bean.getPagId();
    }

    public List<FinDeuda> findDeudasByInquilino(String cedulaInquilino) throws ResourceNotFoundException {
        ctrUsuarioRepository.findById(cedulaInquilino)
                .orElseThrow(() -> new ResourceNotFoundException("Inquilino no encontrado: "
                        + cedulaInquilino));

        List<FinDeuda> deudas = finDeudaService.getDeudasByUser(cedulaInquilino);
        if (deudas.size() == 0) {
            throw new ResourceNotFoundException("No posee deudas por el momento");
        }
        return deudas;
    }

    public FinPago guardarPago(FinPagoVO pago) {
        FinPago bean = new FinPago();
        BeanUtils.copyProperties(pago, bean);
        bean.setPagFecha(LocalDate.now());
        return finPagoRepository.save(bean);
    }

    private void guardarDeudaPago(FinPago pagoRealizado, FinDeuda deuda, BigDecimal deuSaldo) {
        FinDeudaPago detalle = FinDeudaPago.builder()
                .pag(pagoRealizado)
                .deu(deuda)
                .depValorPagado(deuSaldo)
                .build();
        finDeudaPagoRepository.save(detalle);
    }

    public List<FinDeuda> pagarDiferido(String cedulaInquilino, @NotNull FinPagoVO requestBody)
            throws Exception {

        List<FinDeuda> deudasPendientes = findDeudasByInquilino(cedulaInquilino);
        if (requestBody.getPagValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IncorrectValueException("El valor ingresado no es valido");
        }
        BigDecimal dineroDisponible = requestBody.getPagValor();
        List<FinDeuda> deudasPagadas = new ArrayList<>();

        try {
            // Se guarda el pago agregando la fecha actual
            FinPago pagoRealizado = guardarPago(requestBody);
            for (FinDeuda deuda : deudasPendientes) {
                dineroDisponible = dineroDisponible.subtract(deuda.getDeuSaldo());
                //Se crea los registros en la tabla intermedia deuda_pago

                //Actualizacion a los valores de saldo y estado de la deuda
                if (dineroDisponible.compareTo(BigDecimal.ZERO) <= 0) {
                    guardarDeudaPago(pagoRealizado, deuda, dineroDisponible.add(deuda.getDeuSaldo()));
                    deuda.setDeuSaldo(dineroDisponible.abs());
                    deuda.setDeuCancelado(dineroDisponible.equals(new BigDecimal("0.000")));
                    deudasPagadas.add(finDeudaRepository.save(deuda));
                    break;
                } else {
                    guardarDeudaPago(pagoRealizado, deuda, deuda.getDeuSaldo());
                    deuda.setDeuSaldo(new BigDecimal("0.000"));
                    deuda.setDeuCancelado(true);
                    deudasPagadas.add(finDeudaRepository.save(deuda));
                }
            }
        } catch (Exception e) {
            throw new Exception("¡Se ha presentado un error inesperado!");
        }
        return deudasPagadas;
    }

    public List<FinDeudaDTO> crearPago(String cedulaInquilino, @NotNull FinPagoVO requestBody) throws Exception {
        return finDeudaService.toListDTO(pagarDiferido(cedulaInquilino, requestBody));
    }


    public FinReciboDTO getReciboByPago(long pagoId) {
        //Se consulta la informacion del recibo de la vista fin_recibo
        Specification<FinRecibo> thisPago = Specification.where(FinReciboSpecification.
                hasPagoId(pagoId));
        List<FinRecibo> registros = finReciboRepository.findAll(thisPago);

        //retornamos el recibo para presentacion (DTO)
        return toReciboDTO(registros);
    }

    private FinReciboDTO toReciboDTO(List<FinRecibo> registros) {
        List<RegistroPagosDTO> detalles = new ArrayList<>();
        //Agregamos los detalles
        for (FinRecibo recibo : registros) {
            detalles.add(RegistroPagosDTO.builder()
                    .recValorAdeudado(recibo.getRecValorAdeudado())
                    .depValorPagado(recibo.getDepValorPagado())
                    .recSaldo(recibo.getRecSaldo()).deuFechaCorte(recibo.getDeuFechaCorte())
                    .tdeNombre(recibo.getTdeNombre())
                    .deuId(recibo.getDeuId()).build());
        }
        //Estructuramos el recibo a DTO
        FinRecibo recibo = registros.get(0);

        return FinReciboDTO.builder()
                .pagId(recibo.getPagId()).pagValor(recibo.getPagValor())
                .usuCedula(recibo.getUsuCedula()).pagFecha(recibo.getPagFecha())
                .usuCorreo(recibo.getUsuCorreo()).usuApellidos(recibo.getUsuApellidos())
                .cedTesorero(recibo.getCedTesorero()).usuNombres(recibo.getUsuNombres())
                .detalles(detalles).build();
    }

    private FinPago requireOne(Long id) {
        return finPagoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public FinPagoDTO toDTO(FinPago original) {
        FinPagoDTO bean = new FinPagoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

}
