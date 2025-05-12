package com.ventas.principal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.principal.Model.Ventas;
import com.ventas.principal.Model.dto.VentasDto;
import com.ventas.principal.Model.entity.VentasEntity;
import com.ventas.principal.Repository.VentasRepository;

@Service
public class VentasService {
    @Autowired
    private VentasRepository ventasRepository;
    
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

    public String borrarVenta(int idVenta){
        for (Ventas vent : ventas){
            if(vent.getIdVenta() == idVenta){
                ventas.remove(vent);
                return "Venta fue eliminada";
            } else {
                return null;
            }
        }
    }

    public Ventas ObtenerVentas(int idVentas){
        try {
            VentasEntity ventas = ventasRepository.findByIdVenta(idVentas);
            if (ventas != null) {
                Ventas vent = new Ventas(
                    ventas.getIdVenta(),
                    ventas.getCantProducto(),
                    ventas.getTotalVenta(),
                    ventas.getFechaPago(),
                    ventas.getMetodoPago()
                );
                return vent;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public VentasDto ObtenerVentasDto(int cantProducto){
        try {
            VentasEntity ventas = ventasRepository.findBycantProducto(cantProducto);
            VentasDto nuevaVenta = new VentasDto(
                ventas.getCantProducto(),
                ventas.getTotalVenta(),
                ventas.getFechaPago(),
                ventas.getMetodoPago()
            );
            return nuevaVenta;
        } catch (Exception e) { 
            return null;
        }
    } 
}