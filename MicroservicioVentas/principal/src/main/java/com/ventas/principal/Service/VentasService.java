package com.ventas.principal.Service;

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

    /*private final List<Ventas> ventas = new ArrayList<>();

    public VentasService(){
        ventas.add(new Ventas(1, 5, 5000, "15/10/2025", "Tarjeta"));
    }

    public List<Ventas> getAllVentas(){
        return ventas;
    }*/



    public String crearVentas(Ventas vent){
        try {
        Boolean estado = ventasRepository.existsByIdVenta(vent.getIdVenta());
        if (!estado) {
            VentasEntity ventEntity = new VentasEntity();
            ventEntity.setIdVenta(vent.getIdVenta());
            ventEntity.setCantProducto(vent.getCantProducto());
            ventEntity.setTotalVenta(vent.getTotalVenta());
            ventEntity.setFechaPago(vent.getFechaPago());
            ventEntity.setMetodoPago(vent.getMetodoPago());

            ventasRepository.save(ventEntity);
            return "Venta creada con éxito"; 
        } else {
            return "La venta ya existe";
        }
        } catch (Exception e) {
        return "Error al crear la venta: " + e.getMessage();
        }
    }

    @Transactional
    public String borrarVenta(int idVenta){
        VentasEntity existente = ventasRepository.findByIdVenta(idVenta).orElse(null);
        if (existente != null){
            ventasRepository.delete(existente);
            return "Venta eliminada Correctamente";
        }
        return "Venta no encontrada";
    }

    

    public Ventas ObtenerVentas(int idVentas){
        try {
            VentasEntity ventaEntity = ventasRepository.findByIdVenta(idVentas);
            if (ventaEntity != null) {
                return new Ventas(
                ventaEntity.getIdVenta(),
                ventaEntity.getCantProducto(),
                ventaEntity.getTotalVenta(),
                ventaEntity.getFechaPago(),
                ventaEntity.getMetodoPago()
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public VentasDto ObtenerVentasDto(int cantProducto){
        try {
            VentasEntity ventaEntity = ventasRepository.findBycantProducto(cantProducto);
            return new VentasDto(
                ventaEntity.getCantProducto(),
                ventaEntity.getTotalVenta(),
                ventaEntity.getFechaPago(),
                ventaEntity.getMetodoPago()
            );
        } catch (Exception e) {
            return null;
        }
    }

    public String actualizarVenta(int idVenta, Ventas nuevaVenta) {
        try {
            VentasEntity ventaExistente = ventasRepository.findByIdVenta(idVenta);
            if (ventaExistente != null) {
                ventaExistente.setCantProducto(nuevaVenta.getCantProducto());
                ventaExistente.setTotalVenta(nuevaVenta.getTotalVenta());
                ventaExistente.setFechaPago(nuevaVenta.getFechaPago());
                ventaExistente.setMetodoPago(nuevaVenta.getMetodoPago());
                ventasRepository.save(ventaExistente);

                return "Venta actualizada con éxito";
            } else {
                return "Venta no encontrada";
            }
        } catch (Exception e) {
            return "Error al actualizar la venta: " + e.getMessage();
        }    
    }
}