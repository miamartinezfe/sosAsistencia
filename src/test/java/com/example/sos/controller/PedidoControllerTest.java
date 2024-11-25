package com.example.sos.controller;

import com.example.sos.dto.PedidoDTO;
import com.example.sos.dto.RangoFechasDTO;
import com.example.sos.dto.UpdateEstadoDTO;
import com.example.sos.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearPedido_createsNewPedido() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        PedidoDTO nuevoPedido = new PedidoDTO();
        when(pedidoService.crearPedido(pedidoDTO)).thenReturn(nuevoPedido);

        ResponseEntity<PedidoDTO> response = pedidoController.crearPedido(pedidoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(nuevoPedido, response.getBody());
    }

    @Test
    void obtenerPedidoPorId_returnsPedido() {
        Long id = 1L;
        PedidoDTO pedidoDTO = new PedidoDTO();
        when(pedidoService.obtenerPedidoPorId(id)).thenReturn(pedidoDTO);

        ResponseEntity<PedidoDTO> response = pedidoController.obtenerPedidoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidoDTO, response.getBody());
    }

    @Test
    void obtenerPedidosPorEstado_returnsPedidos() {
        String estado = "PENDING";
        List<PedidoDTO> pedidos = Collections.singletonList(new PedidoDTO());
        when(pedidoService.obtenerPedidosPorEstado(estado)).thenReturn(pedidos);

        ResponseEntity<List<PedidoDTO>> response = pedidoController.obtenerPedidoPorId(estado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidos, response.getBody());
    }

    @Test
    void obtenerPedidosEntreFechas_returnsPedidos() {
        RangoFechasDTO rangoFechasDTO = new RangoFechasDTO();
        List<PedidoDTO> pedidos = Collections.singletonList(new PedidoDTO());
        when(pedidoService.obtenerPedidosEntreFechas(rangoFechasDTO)).thenReturn(pedidos);

        ResponseEntity<List<PedidoDTO>> response = pedidoController.obtenerPedidosEntreFechas(rangoFechasDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidos, response.getBody());
    }

    @Test
    void actualizarEstadoPedido_updatesEstado() {
        Long id = 1L;
        UpdateEstadoDTO estadoDTO = new UpdateEstadoDTO();
        estadoDTO.setEstado("COMPLETED");

        ResponseEntity<Void> response = pedidoController.actualizarEstadoPedido(id, estadoDTO);

        verify(pedidoService, times(1)).actualizarEstadoPedido(id, estadoDTO.getEstado());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void cancelarPedido_cancelsPedido() {
        Long id = 1L;

        ResponseEntity<Void> response = pedidoController.cancelarPedido(id);

        verify(pedidoService, times(1)).cancelarPedido(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
