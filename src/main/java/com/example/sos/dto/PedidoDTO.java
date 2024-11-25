package com.example.sos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;
    private String estado;
    private LocalDateTime fechaCreacion;
    private List<ProductDTO> products;
}
