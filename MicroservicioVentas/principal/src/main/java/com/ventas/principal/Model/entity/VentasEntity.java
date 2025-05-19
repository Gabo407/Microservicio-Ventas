package com.ventas.principal.Model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class VentasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;

    @Column(name = "cantProducto")
    private int cantProducto;

    @Column(name = "totalVenta")
    private int totalVenta;

    @Column(name = "fechaPago")
    private String fechaPago;

    @Column(name = "metodoPago")
    private String metodoPago;

    public VentasEntity orElse(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }
}