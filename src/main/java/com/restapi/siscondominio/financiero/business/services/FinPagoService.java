package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinPagoDTO;
import com.restapi.siscondominio.financiero.business.dto.FinReciboCabeceraDTO;
import com.restapi.siscondominio.financiero.business.dto.FinReciboDTO;
import com.restapi.siscondominio.financiero.business.dto.RegistroPagosDTO;
import com.restapi.siscondominio.financiero.business.exceptions.IncorrectValueException;
import com.restapi.siscondominio.financiero.business.exceptions.ResourceNotFoundException;
import com.restapi.siscondominio.financiero.business.speficications.FinReciboSpecification;
import com.restapi.siscondominio.financiero.business.vo.FinPagoComunVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoDiferidoVO;
import com.restapi.siscondominio.financiero.persistence.entities.*;
import com.restapi.siscondominio.financiero.persistence.repositories.*;
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
    @Autowired
    private FinReciboCabeceraRepository finReciboCabeceraRepository;

    public Page<FinPagoDTO> getAll(@NotNull Integer size, Integer page) {
        Sort sorter = Sort.by("pagFecha").descending();
        return toPageDTO(finPagoRepository.findAll(PageRequest.of(page, size, sorter)));
    }

    public List<FinPagoDTO> getAll() {
        Sort sorter = Sort.by("pagFecha").descending();
        return toListDTO(finPagoRepository.findAll(sorter));
    }

    public Page<FinReciboCabecera> getAllRecibos(@NotNull Integer size, Integer page) {
        Sort sorter = Sort.by("pagFecha").descending();
        return finReciboCabeceraRepository.findAll(PageRequest.of(page, size, sorter));
    }

    public List<FinReciboCabecera> getAllRecibos() {
        Sort sorter = Sort.by("pagFecha").descending();
        return finReciboCabeceraRepository.findAll(sorter);
    }

    public FinPagoDTO getById(Long id) {
        FinPago original = requireOne(id);
        return toDTO(original);
    }

    public List<FinDeudaPago> pagarDiferido(String cedulaInquilino, @NotNull FinPagoDiferidoVO requestBody)
            throws Exception {

        List<FinDeuda> deudasPendientes = findDeudasByInquilino(cedulaInquilino);
        if (requestBody.getPagValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IncorrectValueException("El valor ingresado no es valido");
        }
        BigDecimal dineroDisponible = requestBody.getPagValor();
        FinPago pagoRealizado;
        List<FinDeudaPago> detalles = new ArrayList<>();
        try {
            // Se guarda el pago agregando la fecha actual
            pagoRealizado = guardarPago(requestBody);
            for (FinDeuda deuda : deudasPendientes) {
                dineroDisponible = dineroDisponible.subtract(deuda.getDeuSaldo());
                //Se crea los registros en la tabla intermedia deuda_pago

                //Actualizacion a los valores de saldo y estado de la deuda
                if (dineroDisponible.compareTo(BigDecimal.ZERO) <= 0) {
                    detalles.add(guardarDeudaPago(pagoRealizado, deuda, dineroDisponible.add(deuda.getDeuSaldo())));
                    deuda.setDeuSaldo(dineroDisponible.abs());
                    deuda.setDeuCancelado(dineroDisponible.equals(new BigDecimal("0.000")));
                    finDeudaRepository.save(deuda);
                    break;
                } else {
                    detalles.add(guardarDeudaPago(pagoRealizado, deuda, deuda.getDeuSaldo()));
                    deuda.setDeuSaldo(new BigDecimal("0.000"));
                    deuda.setDeuCancelado(true);
                    finDeudaRepository.save(deuda);
                }
            }
        } catch (Exception e) {
            throw new Exception("¡Se ha presentado un error inesperado!");
        }
        return detalles;
    }

    public FinReciboDTO crearPagoDiferido(@NotNull FinPagoDiferidoVO requestBody) throws Exception {
        FinPago pago = pagarDiferido(requestBody.getUsuCedula(), requestBody).get(0).getPag();
        return getReciboByPago(pago.getPagId());
    }

    public FinReciboDTO crearPagoComun(FinPagoComunVO requestBody) throws Exception {
        FinDeuda deuda = finDeudaRepository.findById(requestBody.getDeuId())
                .orElseThrow(() -> new ResourceNotFoundException("Deuda no encontrada: "
                        + requestBody.getDeuId()));
        FinPago pagoGuardado;
        try {
            pagoGuardado = finPagoRepository.save(FinPago.builder()
                    .pagValor(deuda.getDeuSaldo())
                    .pagFecha(LocalDate.now())
                    .cedTesorero(requestBody.getCedTesorero())
                    .build());
            guardarDeudaPago(pagoGuardado, deuda, deuda.getDeuSaldo());

            deuda.setDeuSaldo(BigDecimal.ZERO);
            deuda.setDeuCancelado(true);

            finDeudaRepository.save(deuda);

        } catch (Exception e) {
            throw new Exception("¡Se ha presentado un error inesperado!");
        }
        return getReciboByPago(pagoGuardado.getPagId());
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

    public FinPago guardarPago(FinPagoDiferidoVO pago) {
        FinPago bean = new FinPago();
        BeanUtils.copyProperties(pago, bean);
        bean.setPagFecha(LocalDate.now());
        return finPagoRepository.save(bean);
    }

    private FinDeudaPago guardarDeudaPago(FinPago pagoRealizado, FinDeuda deuda, BigDecimal valorPagado) {
        FinDeudaPago detalle = FinDeudaPago.builder()
                .pag(pagoRealizado)
                .deu(deuda)
                .depValorPagado(valorPagado)
                .build();
        return finDeudaPagoRepository.save(detalle);
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
