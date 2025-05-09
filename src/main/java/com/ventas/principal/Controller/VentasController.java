package com.ventas.principal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.principal.Model.Ventas;
import com.ventas.principal.Service.VentasService;


@RestController
public class VentasController {
    @Autowired
    private VentasService ventasService;

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
    
}