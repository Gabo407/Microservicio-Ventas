package com.ventas.principal.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ventas {
    private int idVenta;
    private int cantProducto;
    private int totalVenta;
    private String fechaPago;
    private String metodoPago;
}
