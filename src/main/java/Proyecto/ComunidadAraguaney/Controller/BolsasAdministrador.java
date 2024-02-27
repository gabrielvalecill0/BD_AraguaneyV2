package Proyecto.ComunidadAraguaney.Controller;


import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelBolsas;
import Proyecto.ComunidadAraguaney.Models.Bolsas;
import Proyecto.ComunidadAraguaney.Pdf.BolsasPdf;
import Proyecto.ComunidadAraguaney.Servicio.BolsasServicio;
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
public class BolsasAdministrador {
    
    @Autowired
    BolsasServicio servicio;
    
    @GetMapping("/Bolsas")
    public String index(Model model){
        List<Bolsas> Bolsas = servicio.listar();
        
        model.addAttribute("objectoListar", Bolsas);

        return "/pagina/Bolsas.html";
    }
    
    @GetMapping("/BolsasIngresar")
    public String ingresar(Model model){
        model.addAttribute("Bolsas",new Bolsas());
        return "/planilla/FormatoBolsas.html";
    }
    
    @PostMapping("/BolsasGuardar")
    public String guardar(@Valid Bolsas p){
        servicio.guardar(p);
        return "redirect:/Bolsas";
    }
    
    @GetMapping("/BolsasEditar{cedula}")
    public String editar(@PathVariable int cedula, Model model){
        
        Optional <Bolsas> Bolsas = servicio.listarPorCedula(cedula);
        model.addAttribute("Bolsas", Bolsas);
        
        return "/planilla/FormatoBolsas.html";
    }
    
    @GetMapping("/BolsasEliminar{cedula}")
    public String borrar (Model model, @PathVariable int cedula){
    servicio.borrar(cedula);
    return "redirect:/Bolsas";
    }
    
    @GetMapping("/BolsasExcel")
    public void exportar (HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
    
    String cabecera = "Content-Disposition";
    String valor = "attachment; filename=GestionDeBolsas.xlsx";
    
    response.setHeader(cabecera,valor);
    
    List<Bolsas> datos = servicio.listar();
    
    ExcelBolsas exportar = new ExcelBolsas(datos);
    exportar.exportar(response);
    }
    
    @GetMapping("/BolsasPdf")
    public void exportarPdf(HttpServletResponse response) throws IOException, DocumentException{
    
        response.setContentType("application/pdf");
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = formato.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Bolsas: "+fechaActual +".pdf";
        
        response.setHeader(cabecera,valor);
        
        List<Bolsas> datos = servicio.listar();
        
        BolsasPdf exportar = new BolsasPdf(datos);
        exportar.exportar(response);
        
    }
    
}