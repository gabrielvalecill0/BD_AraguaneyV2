package Proyecto.ComunidadAraguaney.Controller;

import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelDatosPersonales;
import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelEnfermos;
import Proyecto.ComunidadAraguaney.Models.Enfermos;
import Proyecto.ComunidadAraguaney.Pdf.EnfermosPdf;
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
public class EnfermosAdministrador {
    
    @Autowired
    EnfermosServicio servicio;
    
    @GetMapping("/Enfermos")
    public String index(Model model){
        List<Enfermos> enfermos = servicio.listar();
        
        model.addAttribute("objectoListar", enfermos);
        
        return "/pagina/Enfermos.html";
    }
    
    @GetMapping("/EnfermosIngresar")
    public String ingresar(Model model){
        model.addAttribute("Enfermos",new Enfermos());
        return "/planilla/FormatoEnfermos.html";
    }
    
    @PostMapping("/EnfermosGuardar")
    public String guardar(@Valid Enfermos p){
        servicio.guardar(p);
        return "redirect:/Enfermos";
    }
    
    @GetMapping("/EnfermosEditar{cedula}")
    public String editar(@PathVariable int cedula, Model model){
        
        Optional <Enfermos> Enfermos = servicio.listarPorCedula(cedula);
        model.addAttribute("Enfermos", Enfermos);
        
        return "/planilla/FormatoEnfermos.html";
    }
    
    @GetMapping("/EnfermosEliminar{cedula}")
    public String borrar (Model model, @PathVariable int cedula){
    servicio.borrar(cedula);
    return "redirect:/Enfermos";
    }
    
    @GetMapping("/EnfermosExcel")
    public void exportar (HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
    
    String cabecera = "Content-Disposition";
    String valor = "attachment; filename=PacientesEnfermos.xlsx";
    
    response.setHeader(cabecera,valor);
    
    List<Enfermos> datos = servicio.listar();
    
    ExcelEnfermos exportar = new ExcelEnfermos(datos);
    exportar.exportar(response);
    }
    
    @GetMapping("/EnfermosPdf")
    public void exportarPdf(HttpServletResponse response) throws IOException, DocumentException{
    
        response.setContentType("application/pdf");
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = formato.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Discapacitados: "+fechaActual +".pdf";
        
        response.setHeader(cabecera,valor);
        
        List<Enfermos> datos = servicio.listar();
        
        EnfermosPdf exportar = new EnfermosPdf(datos);
        exportar.exportar(response);
        
    }
    
}