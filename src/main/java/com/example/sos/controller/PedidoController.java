package com.example.sos.controller;

import com.example.sos.dto.PedidoDTO;
import com.example.sos.dto.RangoFechasDTO;
import com.example.sos.dto.UpdateEstadoDTO;
import com.example.sos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO nuevoPedido = pedidoService.crearPedido(pedidoDTO);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/status/{estado}")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidoPorId(@PathVariable String estado) {
        List<PedidoDTO> pedidos = pedidoService.obtenerPedidosPorEstado(estado);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidosEntreFechas(@RequestBody RangoFechasDTO rangoFechasDTO) {
        List<PedidoDTO> pedidos = pedidoService.obtenerPedidosEntreFechas(rangoFechasDTO);
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> actualizarEstadoPedido(@PathVariable Long id, @RequestBody UpdateEstadoDTO estadoDTO) {
        pedidoService.actualizarEstadoPedido(id, estadoDTO.getEstado());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
