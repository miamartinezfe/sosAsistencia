package com.example.sos.repository;

import com.example.sos.models.Pedido;
import com.example.sos.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(Status estado);

    List<Pedido> findByFechaCreacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
