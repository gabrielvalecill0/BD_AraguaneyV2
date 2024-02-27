package Proyecto.ComunidadAraguaney.ExportarExcel;

import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Models.JefeFamilia;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelJefeFamilia {
    
    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    
    private List<JefeFamilia> listaDatos;
    
    public ExcelJefeFamilia(List<JefeFamilia> listaDatos){
    this.listaDatos = listaDatos;
    libro= new XSSFWorkbook();
    hoja = libro.createSheet("JefeFamilia");
    
    }
    
    private void cabeceraTabla(){
        Row fila = hoja.createRow(0);
        
        CellStyle estilo = libro.createCellStyle();
        
        Cell celda = fila.createCell(0);
        celda.setCellValue("CEDULA JEFE");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue("DISCAPACITADOS");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue("¿RECIBES BOMBONAS?");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue("¿RECIBES CLAP?");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(4);
        celda.setCellValue("CARGA FAMILIAR");
        celda.setCellStyle(estilo);
        
    }
    
    private void escribirDatos(){
    int numeroFilas = 1;
    
    CellStyle estilo = libro.createCellStyle();
    
    for(JefeFamilia dato : listaDatos){
        Row fila = hoja.createRow(numeroFilas++);
        
        Cell celda = fila.createCell(0);
        celda.setCellValue(dato.getCedulaJefe());
        hoja.autoSizeColumn(0);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue(dato.getNroDiscapacitados());
        hoja.autoSizeColumn(1);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue(dato.isRecibesBombonas());
        hoja.autoSizeColumn(2);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue(dato.isRecibesClap());
        hoja.autoSizeColumn(3);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(4);
        celda.setCellValue(dato.getCargaFamiliar());
        hoja.autoSizeColumn(4);
        celda.setCellStyle(estilo);
    
    }
    }
    
    public void exportar(HttpServletResponse response) throws IOException {
    cabeceraTabla();
    escribirDatos();
    
    ServletOutputStream outputStream = response.getOutputStream();
    libro.write(outputStream);
    libro.close();
    outputStream.close();
    }
    
}
