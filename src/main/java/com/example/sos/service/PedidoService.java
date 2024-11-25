package com.example.sos.service;

import com.example.sos.dto.PedidoDTO;
import com.example.sos.dto.RangoFechasDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService {
    PedidoDTO crearPedido(PedidoDTO pedidoDTO);

    PedidoDTO obtenerPedidoPorId(Long id);

    List<PedidoDTO> obtenerPedidosPorEstado(String estado);

    List<PedidoDTO> obtenerPedidosEntreFechas(RangoFechasDTO rangoFechasDTO);

    void actualizarEstadoPedido(Long id, String estado);

    void cancelarPedido(Long id);
}
