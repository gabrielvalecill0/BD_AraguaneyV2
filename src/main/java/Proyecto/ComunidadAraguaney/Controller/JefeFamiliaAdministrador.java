package Proyecto.ComunidadAraguaney.Controller;

import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelDatosPersonales;
import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelJefeFamilia;
import Proyecto.ComunidadAraguaney.Models.JefeFamilia;
import Proyecto.ComunidadAraguaney.Pdf.JefeFamiliaPdf;
import Proyecto.ComunidadAraguaney.Servicio.JefeFamiliaServicio;
import Proyecto.ComunidadAraguaney.Servicio.JefeFamiliaServicio;
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
public class JefeFamiliaAdministrador {
    
    @Autowired
    JefeFamiliaServicio servicio;
    
    @GetMapping("/JefeFamilia")
    public String index(Model model){
        List<JefeFamilia> JefeFamilia = servicio.listar();
        int discapacitados = servicio.nroDiscapacitados();
        int bombonasF =servicio.recibesBombonasF();
        int bombonasV =servicio.recibesBombonasV();
        int clapF =servicio.recibesClapF();
        int clapV =servicio.recibesClapV();
        int jefes = servicio.nroJefes();
        
        model.addAttribute("objectoListar", JefeFamilia);
        model.addAttribute("Discapacitados",discapacitados);
        model.addAttribute("BombonasF",bombonasF);
        model.addAttribute("BombonasV",bombonasV);
        model.addAttribute("ClapF",clapF);
        model.addAttribute("ClapV",clapV);
        model.addAttribute("jefes",jefes);
        return "/pagina/JefeFamilia.html";
    }
    
    @GetMapping("/JefeFamiliaIngresar")
    public String ingresar(Model model){
        model.addAttribute("JefeFamilia",new JefeFamilia());
        return "/planilla/FormatoJefeFamilia.html";
    }
    
    @PostMapping("/JefeFamiliaGuardar")
    public String guardar(@Valid JefeFamilia p){
        servicio.guardar(p);
        return "redirect:/JefeFamilia";
    }
    
    @GetMapping("/JefeFamiliaEditar{cedula}")
    public String editar(@PathVariable int cedula, Model model){
        
        Optional <JefeFamilia> JefeFamilia = servicio.listarPorCedula(cedula);
        model.addAttribute("JefeFamilia", JefeFamilia);
        
        return "/planilla/FormatoJefeFamilia.html";
    }
    
    @GetMapping("/JefeFamiliaEliminar{cedula}")
    public String borrar (Model model, @PathVariable int cedula){
    servicio.borrar(cedula);
    return "redirect:/JefeFamilia";
    }
    
    @GetMapping("/JefeFamiliaExcel")
    public void exportar (HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
    
    String cabecera = "Content-Disposition";
    String valor = "attachment; filename=JefesDeFamilia.xlsx";
    
    response.setHeader(cabecera,valor);
    
    List<JefeFamilia> datos = servicio.listar();
    
    ExcelJefeFamilia exportar = new ExcelJefeFamilia(datos);
    exportar.exportar(response);
    }
    
    @GetMapping("/JefeFamiliaPdf")
    public void exportarPdf(HttpServletResponse response) throws IOException, DocumentException{
    
        response.setContentType("application/pdf");
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = formato.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Jefes De Familia: "+fechaActual +".pdf";
        
        response.setHeader(cabecera,valor);
        
        List<JefeFamilia> datos = servicio.listar();
        
        JefeFamiliaPdf exportar = new JefeFamiliaPdf(datos);
        exportar.exportar(response);
        
    }
    
}