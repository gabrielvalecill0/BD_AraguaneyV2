package Proyecto.ComunidadAraguaney.ExportarExcel;

import Proyecto.ComunidadAraguaney.Models.Discapacitados;
import Proyecto.ComunidadAraguaney.Models.Enfermos;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelDiscapacitados {
    
      
    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    
    private List<Discapacitados> listaDatos;
    
    public ExcelDiscapacitados(List<Discapacitados> listaDatos){
    this.listaDatos = listaDatos;
    libro= new XSSFWorkbook();
    hoja = libro.createSheet("Enfermos");
    
    }
    
    private void cabeceraTabla(){
        Row fila = hoja.createRow(0);
        
        CellStyle estilo = libro.createCellStyle();
        
        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue("CEDULA");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue("DISCAPACIDAD");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue("OBSERVACION");
        celda.setCellStyle(estilo);
       
    }
    
    private void escribirDatos(){
    int numeroFilas = 1;
    
    CellStyle estilo = libro.createCellStyle();
    
    for(Discapacitados dato : listaDatos){
        Row fila = hoja.createRow(numeroFilas++);
        
        Cell celda = fila.createCell(0);
        celda.setCellValue(dato.getIdDiscapacidad());
        hoja.autoSizeColumn(0);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue(dato.getDatosPersonales().getCedula());
        hoja.autoSizeColumn(1);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue(dato.getDiscapacidad());
        hoja.autoSizeColumn(2);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue(dato.getObservacion());
        hoja.autoSizeColumn(3);
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
