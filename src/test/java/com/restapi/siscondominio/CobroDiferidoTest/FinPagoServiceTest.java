package com.restapi.siscondominio.CobroDiferidoTest;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinReciboDTO;
import com.restapi.siscondominio.financiero.business.dto.RegistroPagosDTO;
import com.restapi.siscondominio.financiero.business.exceptions.IncorrectValueException;
import com.restapi.siscondominio.financiero.business.exceptions.ResourceNotFoundException;
import com.restapi.siscondominio.financiero.business.services.FinDeudaService;
import com.restapi.siscondominio.financiero.business.services.FinPagoService;
import com.restapi.siscondominio.financiero.business.vo.FinPagoDiferidoVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeuda;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeudaPago;
import com.restapi.siscondominio.financiero.persistence.entities.FinPago;
import com.restapi.siscondominio.financiero.persistence.entities.FinRecibo;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaPagoRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinReciboRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class FinPagoServiceTest {

    //Realizamos un Mock de las clases adicionales que son necesarias para probar el servicio
    //Este mock simulara el comportamiento de repository
    @Mock
    private FinPagoRepository pagoRepository;
    @Mock
    private FinDeudaPagoRepository deudaPagoRepository;
    @Mock
    private CtrUsuarioRepository usuarioRepository;
    @Mock
    private FinDeudaService deudaService;
    @Mock
    private FinDeudaRepository deudaRepository;
    @Mock
    private FinReciboRepository reciboRepository;
    @InjectMocks
    private FinPagoService pagoService;

    private AutoCloseable closeable;
    private CtrUsuario usuarioPrueba;
    private List<FinDeuda> deudasPendientes;
    private List<FinRecibo> recibo;


    //Ejecutamos el metodo setup antes de cada test
    //Este metodo creara un objeto de tipo 'FinPago' que puede ser usado para insertar o consultar
    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        usuarioPrueba = new CtrUsuario("0507896547", "Juan Alexis", "Andrade Perez",
                "jaandradep@gmail.com",null,null, true, null,
                null, null, null, null,null,null);

        FinDeuda deuda1 = FinDeuda.builder()
                .usuCedula(usuarioPrueba).deuSaldo(new BigDecimal(110))
                .deuCancelado(false).build();

        FinDeuda deuda2 = FinDeuda.builder()
                .usuCedula(usuarioPrueba).deuSaldo(new BigDecimal(15))
                .deuCancelado(false).build();

        FinDeuda deuda3 = FinDeuda.builder()
                .usuCedula(usuarioPrueba).deuSaldo(new BigDecimal(110))
                .deuCancelado(false).build();

        deudasPendientes = List.of(deuda1,deuda2, deuda3);

        FinRecibo registro1 = FinRecibo.builder()
                .usuCedula("0507896547")
                .recValorAdeudado(new BigDecimal(110))
                .depValorPagado(new BigDecimal(110)).recSaldo(BigDecimal.ZERO).build();

        FinRecibo registro2 = FinRecibo.builder()
                .usuCedula("0507896547")
                .recValorAdeudado(new BigDecimal(15))
                .depValorPagado(new BigDecimal(15)).recSaldo(BigDecimal.ZERO).build();

        recibo = List.of(registro1,registro2);

    }

    //Una vez el test termine la ejecucion de la prueba se ejecuta el metodo closeService
    //Este se encargara de cerrar todos los recursos usados
    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @DisplayName("Test inquilino no encontrado")
    @Test
    void testUsuarioNoEncontrado() {
        //Given
        given(usuarioRepository.findById(any(String.class))).willReturn(Optional.empty());

        //When
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> pagoService.pagarDiferido("0102116381", new FinPagoDiferidoVO()));

        //Then
        assertEquals("Inquilino no encontrado: 0102116381", thrown.getMessage());
        verify(pagoRepository, never()).save(any(FinPago.class));
    }

    @DisplayName("Test inquilino no tiene deudas")
    @Test
    void testNoExistenDeudas() {
        //Given
        given(usuarioRepository.findById(any(String.class))).willReturn(Optional.of(usuarioPrueba));
        given(deudaService.getDeudasByUser(any(String.class))).willReturn(List.of());
        //When

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> pagoService.pagarDiferido("0507896547", new FinPagoDiferidoVO()));

        //Then
        assertEquals("No posee deudas por el momento", thrown.getMessage());
        verify(pagoRepository, never()).save(any(FinPago.class));
    }

    @DisplayName("Test el valor ingresado no es valido")
    @Test
    void testValorIncorrecto() {
        //Given
        given(usuarioRepository.findById(any(String.class))).willReturn(Optional.of(usuarioPrueba));
        given(deudaService.getDeudasByUser(any(String.class))).willReturn(deudasPendientes);

        FinPagoDiferidoVO pagoIngresado = FinPagoDiferidoVO.builder()
                .cedTesorero("1002585459")
                .pagValor(new BigDecimal(-10))
                .build();

        //When
        IncorrectValueException thrown = assertThrows(IncorrectValueException.class,
                () -> pagoService.pagarDiferido("0507896547", pagoIngresado));

        //Then
        assertEquals("El valor ingresado no es valido", thrown.getMessage());
        verify(pagoRepository, never()).save(any(FinPago.class));
    }

    @DisplayName("Test pagar el total de las deudas")
    @Test
    void testPagarTotal() throws Exception {
        //Given
        given(usuarioRepository.findById(any(String.class))).willReturn(Optional.of(usuarioPrueba));
        given(deudaService.getDeudasByUser(any(String.class))).willReturn(deudasPendientes);
        given(pagoRepository.save(any(FinPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaPagoRepository.save(any(FinDeudaPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaRepository.save(any(FinDeuda.class))).willAnswer(i -> i.getArgument(0));

        FinPagoDiferidoVO pagoIngresado = FinPagoDiferidoVO.builder()
                .cedTesorero("1002585459")
                .pagValor(new BigDecimal(235)).build();

        //Resultado esperado
        List<FinDeuda> esperado = List.of(
                FinDeuda.builder().usuCedula(usuarioPrueba).deuSaldo(new BigDecimal("0.000")).deuCancelado(true).build(),
                FinDeuda.builder().usuCedula(usuarioPrueba).deuSaldo(new BigDecimal("0.000")).deuCancelado(true).build());
        //When
        List<FinDeudaPago> obtenido = pagoService.pagarDiferido("0507896547",pagoIngresado);

        //Then
        //comprueba que el usuario es el correcto
        assertEquals(esperado.get(0).getUsuCedula(),obtenido.get(0).getDeu().getUsuCedula());
        //Comprueba que el saldo pendiente es 0
        assertEquals(esperado.get(0).getDeuSaldo(),obtenido.get(0).getDeu().getDeuSaldo());
        //Comprueba que se cambio el estado a pagado
        assertEquals(esperado.get(1).getDeuCancelado(), obtenido.get(1).getDeu().getDeuCancelado());
    }

    @DisplayName("Test pagar una deuda")
    @Test
    void testPagarUnaDeuda() throws Exception {
        //Given
        given(usuarioRepository.findById(any(String.class))).willReturn(Optional.of(usuarioPrueba));
        given(deudaService.getDeudasByUser(any(String.class))).willReturn(deudasPendientes);
        given(pagoRepository.save(any(FinPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaPagoRepository.save(any(FinDeudaPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaRepository.save(any(FinDeuda.class))).willAnswer(i -> i.getArgument(0));
        FinPagoDiferidoVO pagoIngresado = FinPagoDiferidoVO.builder()
                .cedTesorero("1002585459")
                .pagValor(new BigDecimal("110.000")).build();

        //Resultado esperado
        FinDeuda deudaEsperada1 = FinDeuda.builder().usuCedula(usuarioPrueba)
                .deuSaldo(new BigDecimal("0.000")).deuCancelado(true).build();

        //When
        List<FinDeudaPago> obtenido = pagoService.pagarDiferido("0507896547",pagoIngresado);

        //Then
        //Comprueba que el saldo pendiente y si se encuentra cancelado
        assertEquals(deudaEsperada1.getDeuSaldo(),obtenido.get(0).getDeu().getDeuSaldo());
        assertEquals(deudaEsperada1.getDeuCancelado(), obtenido.get(0).getDeu().getDeuCancelado());
        assertEquals(1, obtenido.size());
    }

    @DisplayName("Test pagar las deudas posibles")
    @Test
    void testPagarDeudasPosibles() throws Exception {
        //Given
        given(usuarioRepository.findById(any(String.class))).willReturn(Optional.of(usuarioPrueba));
        given(deudaService.getDeudasByUser(any(String.class))).willReturn(deudasPendientes);
        given(pagoRepository.save(any(FinPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaPagoRepository.save(any(FinDeudaPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaRepository.save(any(FinDeuda.class))).willAnswer(i -> i.getArgument(0));
        FinPagoDiferidoVO pagoIngresado = FinPagoDiferidoVO.builder()
                .cedTesorero("1002585459")
                .pagValor(new BigDecimal(150)).build();

        //Resultado esperado
        FinDeuda deudaEsperada2 = FinDeuda.builder().usuCedula(usuarioPrueba)
                .deuSaldo(BigDecimal.ZERO).deuCancelado(true).build();
        FinDeuda deudaEsperada3 = FinDeuda.builder().usuCedula(usuarioPrueba)
                .deuSaldo(new BigDecimal(85)).deuCancelado(false).build();

        //When
        List<FinDeudaPago> obtenido = pagoService.pagarDiferido("0507896547",pagoIngresado);

        //Then
        assertEquals(3, obtenido.size());
        assertEquals(deudaEsperada2.getDeuCancelado(), obtenido.get(1).getDeu().getDeuCancelado());
        assertEquals(deudaEsperada3.getDeuSaldo(),obtenido.get(2).getDeu().getDeuSaldo());
        assertEquals(deudaEsperada3.getDeuCancelado(), obtenido.get(2).getDeu().getDeuCancelado());
    }

    @DisplayName("Test error inesperado en el pago")
    @Test
    void testErrorInesperado() {
        //Given
        given(usuarioRepository.findById(any(String.class))).willReturn(Optional.of(usuarioPrueba));
        given(deudaService.getDeudasByUser(any(String.class))).willReturn(deudasPendientes);
        given(pagoRepository.save(any(FinPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaPagoRepository.save(any(FinDeudaPago.class))).willAnswer(i -> i.getArgument(0));
        given(deudaRepository.save(any(FinDeuda.class))).willAnswer(i -> i.getArgument(0));

        //When
        assertThrows(Exception.class,
                () -> pagoService.pagarDiferido("0507896547", null));

        //Then
        verify(pagoRepository, never()).save(any(FinPago.class));
    }

    @DisplayName("Test obtener recibo por pago")
    @Test
    void testGetRecibo() {
        //Given
        given(reciboRepository.findAll(any(Specification.class))).willReturn(recibo);

        RegistroPagosDTO detalle = RegistroPagosDTO.builder().recValorAdeudado(new BigDecimal(110))
                .depValorPagado(new BigDecimal(110)).recSaldo(BigDecimal.ZERO).build();
        FinReciboDTO reciboEsperado = FinReciboDTO.builder().usuCedula("0507896547")
                .pagValor(new BigDecimal(125)).detalles(List.of(detalle)).build();

        //When
        FinReciboDTO reciboResultado = pagoService.getReciboByPago(1);

        //Then
        assertEquals(reciboEsperado.getUsuCedula(),reciboResultado.getUsuCedula());
        assertEquals(reciboEsperado.getDetalles().get(0).getDepValorPagado(),
                reciboResultado.getDetalles().get(0).getDepValorPagado());
        assertEquals(reciboEsperado.getDetalles().get(0).getRecSaldo(),
                reciboResultado.getDetalles().get(0).getRecSaldo());
    }



}









