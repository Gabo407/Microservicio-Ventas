package com.ventas.principal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.principal.Model.Ventas;
import com.ventas.principal.Model.dto.VentasDto;
import com.ventas.principal.Service.VentasService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class VentasController {
    @Autowired
    private VentasService ventasService;

    VentasService accionesVentas = new VentasService();
    @Operation(summary = "Este endpoint trae todas las ventas")

    @GetMapping("/ventas")
    public List<Ventas> mostrarVentas(){
        return accionesVentas.getAllVentas();
    }

    @PostMapping("/crearVentas")
    public ResponseEntity<String> obtenerVentas(@RequestBody Ventas vent) {
        return ResponseEntity.ok(ventasService.crearVentas(vent));
    }

    @GetMapping("/obtenerVenta/{idVenta}")
    public ResponseEntity<Ventas> obtenerVentas(@PathVariable int idVenta){
        Ventas ventas = ventasService.ObtenerVentas(idVenta);
        if (ventas != null) {
            return ResponseEntity.ok(ventas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/ventas/{id}")
    public String borrarVenta(@PathVariable int id){
        return accionesVentas.borrarVenta(id);
    }

    @GetMapping("/obtenerVentaDto/{cantProducto}")
    public ResponseEntity<VentasDto> obtenerVentasDto(@PathVariable int cantProducto){
        if (ventasService.ObtenerVentasDto(cantProducto) != null) {
            return ResponseEntity.ok(ventasService.ObtenerVentasDto(cantProducto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarVenta/{idVenta}")
    public ResponseEntity<String> actualizarVenta(@PathVariable int idVenta, @RequestBody Ventas nuevaVenta) {
        String resultado = ventasService.actualizarVenta(idVenta, nuevaVenta);
        return switch (resultado) {
            case "Venta actualizada con éxito" -> ResponseEntity.ok(resultado);
            case "Venta no encontrada" -> ResponseEntity.notFound().build();
            default -> ResponseEntity.badRequest().body(resultado);
        };
    }
}