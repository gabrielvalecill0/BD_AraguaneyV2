package Proyecto.ComunidadAraguaney.Controller;

import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelDiscapacitados;
import Proyecto.ComunidadAraguaney.Models.Discapacitados;
import Proyecto.ComunidadAraguaney.Models.Enfermos;
import Proyecto.ComunidadAraguaney.Pdf.DiscapacitadosPdf;
import Proyecto.ComunidadAraguaney.Servicio.DiscapacitadosServicio;
import Proyecto.ComunidadAraguaney.Servicio.EnfermosServicio;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@Controller
public class DiscapacitadosAdministrador {
    
    @Autowired
    DiscapacitadosServicio servicio;
    
    @GetMapping("/Discapacitados")
    public String index(Model model){
        List<Discapacitados> Discapacitados = servicio.listar();
        
        model.addAttribute("objectoListar", Discapacitados);
        
        return "/pagina/Discapacitados.html";
    }
    
    @GetMapping("/DiscapacitadosIngresar")
    public String ingresar(Model model){
        model.addAttribute("Discapacitados",new Discapacitados());
        return "/planilla/FormatoDiscapacitados.html";
    }
    
    @PostMapping("/DiscapacitadosGuardar")
    public String guardar(@Valid Discapacitados p){
        servicio.guardar(p);
        return "redirect:/Discapacitados";
    }
    
    @GetMapping("/DiscapacitadosEditar{cedula}")
    public String editar(@PathVariable int cedula, Model model){
        
        Optional <Discapacitados> Discapacitados = servicio.listarPorCedula(cedula);
        model.addAttribute("Discapacitados", Discapacitados);
        
        return "/planilla/FormatoDiscapacitados.html";
    }
    
    @GetMapping("/DiscapacitadosEliminar{cedula}")
    public String borrar (Model model, @PathVariable int cedula){
    servicio.borrar(cedula);
    return "redirect:/Discapacitados";
    }
    
    @GetMapping("/DiscapacitadosExcel")
    public void exportar (HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
    
    String cabecera = "Content-Disposition";
    String valor = "attachment; filename=PacientesDiscapacitados.xlsx";
    
    response.setHeader(cabecera,valor);
    
    List<Discapacitados> datos = servicio.listar();
    
    ExcelDiscapacitados exportar = new ExcelDiscapacitados(datos);
    exportar.exportar(response);
    }
    
    @GetMapping("/DiscapacitadosPdf")
    public void exportarPdf(HttpServletResponse response) throws IOException, DocumentException{
    
        response.setContentType("application/pdf");
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = formato.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Discapacitados: "+fechaActual +".pdf";
        
        response.setHeader(cabecera,valor);
        
        List<Discapacitados> datos = servicio.listar();
        
        DiscapacitadosPdf exportar = new DiscapacitadosPdf(datos);
        exportar.exportar(response);
        
    }
    
}