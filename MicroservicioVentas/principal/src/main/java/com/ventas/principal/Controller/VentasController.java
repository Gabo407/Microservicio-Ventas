package com.ventas.principal.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.principal.Model.Ventas;
import com.ventas.principal.Model.dto.VentasDto;
import com.ventas.principal.Service.VentasService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ventas")
public class VentasController {
    @Autowired
    private VentasService ventasService;

    @PostMapping
    @Operation(summary = "Este endpoint permite AGREGAR ventas")
    public ResponseEntity<String> crearVenta(@Valid @RequestBody Ventas venta) {
        ventasService.crearVenta(venta);
        return ResponseEntity.ok("Venta creada exitosamente");
    }

    @GetMapping("/obtenerVenta/{idVenta}")
    public ResponseEntity<Ventas> obtenerVenta(@PathVariable int idVenta) {
        Ventas venta = ventasService.obtenerVenta(idVenta);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> obtenerTodasLasVentas() {
        List<Ventas> ventas = ventasService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }

    @PutMapping("/actualizarVenta/{idVenta}")
    public ResponseEntity<?> actualizarVenta(@PathVariable int idVenta, @Valid @RequestBody Ventas venta) {
        try {
            String resultado = ventasService.actualizarVenta(idVenta, venta);
            if (resultado.equals("Venta no encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Venta no encontrada"));
            }
            return ResponseEntity.ok(Map.of("mensaje", "Venta actualizada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("mensaje", "Error al actualizar la venta: " + e.getMessage()));
        }
    }

    @DeleteMapping("/eliminarVenta/{idVenta}")
    public ResponseEntity<String> eliminarVenta(@PathVariable int idVenta) {
        String mensaje = ventasService.eliminarVenta(idVenta);
        if ("Venta eliminada correctamente".equals(mensaje)) {
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtenerVentaDto/{idVenta}")
    public ResponseEntity<VentasDto> obtenerVentaDto(@PathVariable int idVenta) {
        VentasDto ventaDto = ventasService.obtenerVentaPorId(idVenta);
        if (ventaDto != null) {
            return ResponseEntity.ok(ventaDto);
        }
        return ResponseEntity.notFound().build();
    }
}