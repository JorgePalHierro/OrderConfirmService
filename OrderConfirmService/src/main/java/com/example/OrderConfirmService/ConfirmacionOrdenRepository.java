package com.example.OrderConfirmService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConfirmacionOrdenRepository extends JpaRepository<ConfirmacionOrden, ConfirmacionOrdenId> {

    @Query("SELECT c FROM ConfirmacionOrden c WHERE c.numeroOrden = :numeroOrden " +
           "AND c.fecha = :fecha AND c.transaccion = :transaccion " +
           "AND c.terminal = :terminal AND c.tienda = :tienda AND c.consecutivo = :consecutivo")
    ConfirmacionOrden findByNumeroOrdenAndFechaAndTransaccionAndTerminalAndTiendaAndConsecutivo(
            @Param("numeroOrden") String numeroOrden,
            @Param("fecha") String fecha,
            @Param("transaccion") String transaccion,
            @Param("terminal") String terminal,
            @Param("tienda") String tienda,
            @Param("consecutivo") String consecutivo);
	
}
