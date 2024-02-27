package Proyecto.ComunidadAraguaney.Controller;

import Proyecto.ComunidadAraguaney.ExportarExcel.ExcelDatosPersonales;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Pdf.DatosPersonalesPdf;
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
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@Controller
public class DatosPersonalesAdministrador {
    
    @Autowired
    DatosPersonalesServicio servicio;
    
    @GetMapping("/index")
    public String index(Model model){
        List<DatosPersonales> datosPersonales = servicio.listar();
        int ninos = servicio.calcularNinos();
        int mayores = servicio.calcularMayores();
        int personas = servicio.personas();
        int adultos = servicio.contadorAdultos();
        
        model.addAttribute("objectoListar", datosPersonales);
        model.addAttribute("calcularNinos",ninos);
        model.addAttribute("calcularMayores",mayores);
        model.addAttribute("Personas",personas);
        model.addAttribute("Adultos",adultos);
        return "/pagina/DatosPersonales.html";
    }
    
    @GetMapping("/DatosPersonalesIngresar")
    public String ingresar(Model model){
        model.addAttribute("DatosPersonales",new DatosPersonales());
        return "/planilla/FormatoDatosPersonales.html";
    }
    
    @PostMapping("/DatosPersonalesGuardar")
    public String guardar(@Valid DatosPersonales p){
        servicio.guardar(p);
        return "redirect:/index";
    }
    
    @GetMapping("/DatosPersonalesEditar{cedula}")
    public String editar(@PathVariable int cedula, Model model){
        
        Optional <DatosPersonales> datos = servicio.listarPorCedula(cedula);
        model.addAttribute("DatosPersonales", datos);
        
        return "/planilla/FormatoDatosPersonales.html";
    }
    
    @GetMapping("/DatosPersonalesEliminar{cedula}")
    public String borrar (Model model, @PathVariable int cedula){
    servicio.borrar(cedula);
    return "redirect:/index";
    }
    
    @GetMapping("/DatosPersonalesExcel")
    public void exportar (HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
    
    String cabecera = "Content-Disposition";
    String valor = "attachment; filename=DatosPersonales.xlsx";
    
    response.setHeader(cabecera,valor);
    
    List<DatosPersonales> datos = servicio.listar();
    
    ExcelDatosPersonales exportar = new ExcelDatosPersonales(datos);
    exportar.exportar(response);
    }
    
    @GetMapping("/DatosPersonalesPdf")
    public void exportarPdf(HttpServletResponse response) throws IOException, DocumentException{
    
        response.setContentType("application/pdf");
        
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = formato.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=DatosPersonales: "+fechaActual +".pdf";
        
        response.setHeader(cabecera,valor);
        
        List<DatosPersonales> datos = servicio.listar();
        
        DatosPersonalesPdf exportar = new DatosPersonalesPdf(datos);
        exportar.exportar(response);
        
    }
    
}