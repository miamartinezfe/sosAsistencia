package com.example.sos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RangoFechasDTO {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
