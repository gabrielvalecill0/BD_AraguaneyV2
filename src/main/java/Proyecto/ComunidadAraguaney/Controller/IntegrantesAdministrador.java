package Proyecto.ComunidadAraguaney.Controller;

import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelIntegrantes;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Models.Integrantes;
import Proyecto.ComunidadAraguaney.Pdf.IntegrantesPdf;
import Proyecto.ComunidadAraguaney.Servicio.DatosPersonalesServicio;
import Proyecto.ComunidadAraguaney.Servicio.IntegrantesServicio;
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
public class IntegrantesAdministrador {
    
    @Autowired
    IntegrantesServicio servicio;
    
    @GetMapping("/Integrantes")
    public String index(Model model){
        List<Integrantes> Integrantes = servicio.listar();
        
        model.addAttribute("objectoListar", Integrantes);

        return "/pagina/Integrantes.html";
    }
    
    @GetMapping("/IntegrantesIngresar")
    public String ingresar(Model model){
        model.addAttribute("Integrantes",new Integrantes());
        return "/planilla/FormatoIntegrantes.html";
    }
    
    @PostMapping("/IntegrantesGuardar")
    public String guardar(@Valid Integrantes p){
        servicio.guardar(p);
        return "redirect:/Integrantes";
    }
    
    @GetMapping("/IntegrantesEditar{cedula}")
    public String editar(@PathVariable int cedula, Model model){
        
        Optional <Integrantes> integrantes = servicio.listarPorCedula(cedula);
        model.addAttribute("Integrantes", integrantes);
        
        return "/planilla/FormatoIntegrantes.html";
    }
    
    @GetMapping("/IntegrantesEliminar{cedula}")
    public String borrar (Model model, @PathVariable int cedula){
    servicio.borrar(cedula);
    return "redirect:/Integrantes";
    }
    
    @GetMapping("/IntegrantesExcel")
    public void exportar (HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
    
    String cabecera = "Content-Disposition";
    String valor = "attachment; filename=Integrantes.xlsx";
    
    response.setHeader(cabecera,valor);
    
    List<Integrantes> datos = servicio.listar();
    
    ExcelIntegrantes exportar = new ExcelIntegrantes(datos);
    exportar.exportar(response);
    }
    
    @GetMapping("/IntegrantesPdf")
    public void exportarPdf(HttpServletResponse response) throws IOException, DocumentException{
    
        response.setContentType("application/pdf");
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = formato.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Bombonas: "+fechaActual +".pdf";
        
        response.setHeader(cabecera,valor);
        
        List<Integrantes> datos = servicio.listar();
        
        IntegrantesPdf exportar = new IntegrantesPdf(datos);
        exportar.exportar(response);
        
    }
    
}