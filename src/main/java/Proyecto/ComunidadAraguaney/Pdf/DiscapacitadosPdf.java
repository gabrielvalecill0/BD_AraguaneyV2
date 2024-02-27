package Proyecto.ComunidadAraguaney.Pdf;

import Proyecto.ComunidadAraguaney.Models.Discapacitados;
import Proyecto.ComunidadAraguaney.Models.Enfermos;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class DiscapacitadosPdf {
    
     List<Discapacitados> lista;

    public DiscapacitadosPdf(List<Discapacitados> lista) {
        super();
        this.lista = lista;
    }

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {

        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        
        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("CEDULA", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("DISCAPACIDAD", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("OBSERVACIÒN", fuente));
        tabla.addCell(celda);
        
    }

    private void escribirDatos(PdfPTable tabla) {

        for (Discapacitados dato : lista) {

            tabla.addCell(String.valueOf(dato.getIdDiscapacidad()));
            tabla.addCell(String.valueOf(dato.getDatosPersonales().getCedula()));
            tabla.addCell(dato.getDiscapacidad());
            tabla.addCell(dato.getObservacion());

        }

    }

    public void exportar(HttpServletResponse response) throws IOException, DocumentException {

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Reporte Discapacitados", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatos(tabla);

        documento.add(tabla);
        documento.close();

    }

    
}
