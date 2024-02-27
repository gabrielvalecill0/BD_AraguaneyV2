package Proyecto.ComunidadAraguaney.ExportarExcel;

import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelDatosPersonales {

    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    
    private List<DatosPersonales> listaDatos;
    
    public ExcelDatosPersonales(List<DatosPersonales> listaDatos){
    this.listaDatos = listaDatos;
    libro= new XSSFWorkbook();
    hoja = libro.createSheet("DatosPersonales");
    
    }
    
    private void cabeceraTabla(){
        Row fila = hoja.createRow(0);
        
        CellStyle estilo = libro.createCellStyle();
        
        Cell celda = fila.createCell(0);
        celda.setCellValue("CEDULA");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue("NOMBRE");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue("APELLIDO");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue("DIRECCION");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(4);
        celda.setCellValue("STATUS");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(5);
        celda.setCellValue("FECHA NACIMIENTO");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(6);
        celda.setCellValue("FECHA RESIDENCIA");
        celda.setCellStyle(estilo);
    }
    
    private void escribirDatos(){
    int numeroFilas = 1;
    
    CellStyle estilo = libro.createCellStyle();
    
    for(DatosPersonales dato : listaDatos){
        Row fila = hoja.createRow(numeroFilas++);
        
        Cell celda = fila.createCell(0);
        celda.setCellValue(dato.getCedula());
        hoja.autoSizeColumn(0);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue(dato.getNombre().toString());
        hoja.autoSizeColumn(1);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue(dato.getApellido());
        hoja.autoSizeColumn(2);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue(dato.getDireccion());
        hoja.autoSizeColumn(3);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(4);
        celda.setCellValue(dato.getStatus());
        hoja.autoSizeColumn(4);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(5);
        celda.setCellValue(dato.getfNacimiento().toString());
        hoja.autoSizeColumn(5);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(6);
        celda.setCellValue(dato.getfResidencia().toString());
        hoja.autoSizeColumn(6);
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