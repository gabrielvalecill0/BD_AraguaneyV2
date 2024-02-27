package Proyecto.ComunidadAraguaney.ExportarExcel;

import Proyecto.ComunidadAraguaney.Models.Bombonas;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelBombonas {
  
    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    
    private List<Bombonas> listaDatos;
    
    public ExcelBombonas(List<Bombonas> listaDatos){
    this.listaDatos = listaDatos;
    libro= new XSSFWorkbook();
    hoja = libro.createSheet("Bombonas");
    
    }
    
    private void cabeceraTabla(){
        Row fila = hoja.createRow(0);
        
        CellStyle estilo = libro.createCellStyle();
        
        Cell celda = fila.createCell(0);
        celda.setCellValue("ID BOMBONA");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue("CEDULA JEFE");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue("BOMBONAS A COMPRAR");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue("10KG");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(4);
        celda.setCellValue("18KG");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(5);
        celda.setCellValue("43KG");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(6);
        celda.setCellValue("EFECTIVO");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(7);
        celda.setCellValue("REFERENCIA");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(8);
        celda.setCellValue("MONTO");
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(9);
        celda.setCellValue("FECHA");
        celda.setCellStyle(estilo);
        
    }
    
    private void escribirDatos(){
    int numeroFilas = 1;
    
    CellStyle estilo = libro.createCellStyle();
    
    for(Bombonas dato : listaDatos){
        Row fila = hoja.createRow(numeroFilas++);
        
        Cell celda = fila.createCell(0);
        celda.setCellValue(dato.getIdBombonas());
        hoja.autoSizeColumn(0);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(1);
        celda.setCellValue(dato.getJefeFamilia().getCedulaJefe());
        hoja.autoSizeColumn(1);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(2);
        celda.setCellValue(dato.getBombonasComprar());
        hoja.autoSizeColumn(2);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(3);
        celda.setCellValue(dato.getKg10());
        hoja.autoSizeColumn(3);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(4);
        celda.setCellValue(dato.getKg18());
        hoja.autoSizeColumn(4);
        celda.setCellStyle(estilo);
    
        celda = fila.createCell(5);
        celda.setCellValue(dato.getKg43());
        hoja.autoSizeColumn(5);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(6);
        celda.setCellValue(dato.isEfectivo()? 1.0 : 0.0);
        hoja.autoSizeColumn(6);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(7);
        celda.setCellValue(dato.getReferencia());
        hoja.autoSizeColumn(7);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(8);
        celda.setCellValue(dato.getMonto());
        hoja.autoSizeColumn(8);
        celda.setCellStyle(estilo);
        
        celda = fila.createCell(9);
        celda.setCellValue(dato.getFecha().toString());
        hoja.autoSizeColumn(9);
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
