package Proyecto.ComunidadAraguaney.Pdf;

import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Models.JefeFamilia;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;


public class JefeFamiliaPdf {
    
    List<JefeFamilia> lista;

    public JefeFamiliaPdf(List<JefeFamilia> lista) {
        super();
        this.lista = lista;
    }

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {

        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        
        celda.setPhrase(new Phrase("CI JEFE", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("RECIBES BOMBONAS", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("RECIBES CLAP", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("NRO DISCAPACITADOS", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("CARGA FAMILIAR", fuente));
        tabla.addCell(celda);
    }

    private void escribirDatos(PdfPTable tabla) {

        for (JefeFamilia dato : lista) {

            tabla.addCell(String.valueOf(dato.getCedulaJefe()));
            tabla.addCell(String.valueOf(dato.isRecibesBombonas()));
            tabla.addCell(String.valueOf(dato.isRecibesClap()));
            tabla.addCell(String.valueOf(dato.getNroDiscapacitados()));
            tabla.addCell(String.valueOf(dato.getCargaFamiliar()));


        }

    }

    public void exportar(HttpServletResponse response) throws IOException, DocumentException {

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Reporte Jefes De Familia", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatos(tabla);

        documento.add(tabla);
        documento.close();

    }
    
}
