package com.example.sos.service;

import com.example.sos.dto.PedidoDTO;
import com.example.sos.dto.PedidoDTOAdapter;
import com.example.sos.dto.RangoFechasDTO;
import com.example.sos.models.Pedido;
import com.example.sos.models.Status;
import com.example.sos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoDTOAdapter dtoAdapter;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoDTOAdapter dtoAdapter) {
        this.pedidoRepository = pedidoRepository;
        this.dtoAdapter = dtoAdapter;
    }

    @Override
    @Transactional
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO) {

        Pedido pedido = dtoAdapter.convertToEntity(pedidoDTO);
        Pedido savedPedido = pedidoRepository.save(pedido);

        return dtoAdapter.convertToDTO(savedPedido);
    }

    @Override
    public PedidoDTO obtenerPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return dtoAdapter.convertToDTO(pedido);
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorEstado(String estado) {
        List<Pedido> pedidos = pedidoRepository.findByEstado(Status.valueOf(estado));
        return pedidos.stream().map(dtoAdapter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> obtenerPedidosEntreFechas(RangoFechasDTO rangoFechasDTO) {
        List<Pedido> pedidos = pedidoRepository.findByFechaCreacionBetween(rangoFechasDTO.getFechaInicio(), rangoFechasDTO.getFechaFin());
        return pedidos.stream().map(dtoAdapter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void actualizarEstadoPedido(Long id, String estado) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(Status.valueOf(estado));
        pedidoRepository.save(pedido);
    }

    @Override
    public void cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(Status.CANCELADO);
        pedidoRepository.save(pedido);
    }
}
