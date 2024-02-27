package Proyecto.ComunidadAraguaney.Controller;

import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelBombonas;
import Proyecto.ComunidadAraguaney.Models.Bombonas;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Pdf.BombonasPdf;
import Proyecto.ComunidadAraguaney.Servicio.BombonasServicio;
import Proyecto.ComunidadAraguaney.Servicio.DatosPersonalesServicio;
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
public class BombonasAdministrador {
    
    @Autowired
    BombonasServicio servicio;
    
    @GetMapping("/Bombonas")
    public String index(Model model){
        List<Bombonas> Bombonas = servicio.listar();
        int totalBombonas = servicio.totalBombonas();
        int kg10 =servicio.Bombonas10kg();
        int kg18 =servicio.Bombonas18kg();
        int kg43 =servicio.Bombonas43kg();
        int montoTotal =servicio.montoTotal();
        
        model.addAttribute("objectoListar", Bombonas);
        model.addAttribute("totalBombonas", totalBombonas);
        model.addAttribute("kg10", kg10);
        model.addAttribute("kg18", kg18);
        model.addAttribute("kg43", kg43);
        model.addAttribute("montoTotal", montoTotal);

        return "/pagina/Bombonas.html";
    }
    
    @GetMapping("/BombonasIngresar")
    public String ingresar(Model model){
        model.addAttribute("Bombonas",new Bombonas());
        return "/planilla/FormatoBombonas.html";
    }
    
    @PostMapping("/BombonasGuardar")
    public String guardar(@Valid Bombonas p){
        servicio.guardar(p);
        return "redirect:/Bombonas";
    }
    
    @GetMapping("/BombonasEditar{cedula}")
    public String editar(@PathVariable int cedula, Model model){
        
        Optional <Bombonas> Bombonas = servicio.listarPorCedula(cedula);
        model.addAttribute("Bombonas", Bombonas);
        
        return "/planilla/FormatoBombonas.html";
    }
    
    @GetMapping("/BombonasEliminar{cedula}")
    public String borrar (Model model, @PathVariable int cedula){
    servicio.borrar(cedula);
    return "redirect:/Bombonas";
    }
    
    @GetMapping("/BombonasExcel")
    public void exportar (HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
    
    String cabecera = "Content-Disposition";
    String valor = "attachment; filename=GestionDeBombonas.xlsx";
    
    response.setHeader(cabecera,valor);
    
    List<Bombonas> datos = servicio.listar();
    
    ExcelBombonas exportar = new ExcelBombonas(datos);
    exportar.exportar(response);
    }
    
    @GetMapping("/BombonasPdf")
    public void exportarPdf(HttpServletResponse response) throws IOException, DocumentException{
    
        response.setContentType("application/pdf");
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = formato.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Bombonas: "+fechaActual +".pdf";
        
        response.setHeader(cabecera,valor);
        
        List<Bombonas> datos = servicio.listar();
        
        BombonasPdf exportar = new BombonasPdf(datos);
        exportar.exportar(response);
        
    }
    
}