package com.ventas.principal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.principal.Model.Ventas;
import com.ventas.principal.Model.dto.VentasDto;
import com.ventas.principal.Model.entity.VentasEntity;
import com.ventas.principal.Repository.VentasRepository;

import jakarta.transaction.Transactional;

@Service
public class VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    @Transactional
    public String crearVenta(Ventas venta) {
        // Puedes agregar una validación para evitar duplicados si lo deseas
        VentasEntity ventaNueva = mapToEntity(venta);
        ventasRepository.save(ventaNueva);
        return "Venta creada exitosamente";
    }

    public Ventas obtenerVenta(int idVenta) {
        VentasEntity venta = ventasRepository.findById(idVenta).orElse(null);
        if (venta != null) {
            return mapToModel(venta);
        }
        return null;
    }

    public List<Ventas> obtenerTodasLasVentas() {
        return ventasRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public String actualizarVenta(int idVenta, Ventas venta) {
        VentasEntity existente = ventasRepository.findById(idVenta).orElse(null);
        if (existente != null) {
            existente.setCantProducto(venta.getCantProducto());
            existente.setTotalVenta(venta.getTotalVenta());
            existente.setFechaPago(venta.getFechaPago());
            existente.setMetodoPago(venta.getMetodoPago());
            ventasRepository.save(existente);
            return "Producto actualizado exitosamente";
        }
        return "Producto no encontrado";
    }

    @Transactional
    public String eliminarVenta(int idVenta) {
        VentasEntity existente = ventasRepository.findById(idVenta).orElse(null);
        if (existente != null) {
            ventasRepository.delete(existente);
            return "Venta eliminada correctamente";
        }
        return "Venta no encontrada";
    }

    // Métodos auxiliares para mapear entre Ventas y VentasEntity
    private VentasEntity mapToEntity(Ventas venta) {
        VentasEntity entity = new VentasEntity();
        entity.setIdVenta(venta.getIdVenta());
        entity.setCantProducto(venta.getCantProducto());
        entity.setTotalVenta(venta.getTotalVenta());
        entity.setFechaPago(venta.getFechaPago());
        entity.setMetodoPago(venta.getMetodoPago());
        return entity;
    }

    private Ventas mapToModel(VentasEntity entity) {
        return new Ventas(
                entity.getIdVenta(),
                entity.getCantProducto(),
                entity.getTotalVenta(),
                entity.getFechaPago(),
                entity.getMetodoPago()
        );
    }

    public VentasDto obtenerVentaPorId(int idVenta) {
        VentasEntity venta = ventasRepository.findById(idVenta).orElse(null);
        if (venta == null) {
            return null;
        }
        return new VentasDto(
                venta.getCantProducto(),
                venta.getTotalVenta(),
                venta.getFechaPago(),
                venta.getMetodoPago()
        );
    }
}