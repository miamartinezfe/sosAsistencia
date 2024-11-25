package com.example.sos.dto;

import com.example.sos.models.Pedido;
import com.example.sos.models.Product;
import com.example.sos.models.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class PedidoDTOAdapter {

    public PedidoDTO convertToDTO(Pedido pedido) {
        return PedidoDTO.builder()
                .id(pedido.getId())
                .estado(pedido.getEstado().name())
                .fechaCreacion(pedido.getFechaCreacion())
                .products(pedido.getProducts().stream()
                        .map(product -> ProductDTO.builder()
                                .id(product.getId())
                                .nombre(product.getNombre())
                                .precio(product.getPrecio())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public Pedido convertToEntity(PedidoDTO pedidoDTO) {
        Pedido newPedido = new Pedido();
        newPedido.setEstado(Status.valueOf(pedidoDTO.getEstado()));
        newPedido.setFechaCreacion(LocalDateTime.now());
        newPedido.setProducts(pedidoDTO.getProducts().stream()
                .map(productDTO -> {
                    Product product = new Product();
                    product.setId(productDTO.getId());
                    product.setNombre(productDTO.getNombre());
                    product.setPrecio(productDTO.getPrecio());
                    product.setPedido(newPedido);
                    return product;
                }).collect(Collectors.toList()));

        return newPedido;
    }
}
