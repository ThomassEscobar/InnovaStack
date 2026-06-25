package com.consultoria.reportes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.reportes.client.FacturacionFeign;
import com.consultoria.reportes.dto.DashBoardDTO;
import com.consultoria.reportes.model.Reportes;
import com.consultoria.reportes.repository.ReporteRepository;


@Service
public class ReporteService {

    @Autowired
    private ReporteRepository repo;

    @Autowired
    private FacturacionFeign feign;

    public DashBoardDTO generarReporte() {

        Integer pendientes = feign.obtenerPendientes();
        Integer pagadas = feign.obtenerPagadas();
        Double ingresos = feign.obtenerIngresos();

        Reportes reporte = new Reportes();

        reporte.setTicketsPendientes(pendientes);
        reporte.setTotalProyectosActivos(pagadas);
        reporte.setIngresosMes(ingresos);

        Reportes guardar = repo.save(reporte);

        return new DashBoardDTO(
                guardar.getTotalProyectosActivos(),
                guardar.getIngresosMes(),
                guardar.getTicketsPendientes());

    }

    public List<DashBoardDTO> listar() {

        return repo.findAll()
                .stream()
                .map(rep -> new DashBoardDTO(
                        rep.getTotalProyectosActivos(),
                        rep.getIngresosMes(),
                        rep.getTicketsPendientes()))
                .collect(Collectors.toList());

    }

}