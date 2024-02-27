package Proyecto.ComunidadAraguaney.Pdf;

import Proyecto.ComunidadAraguaney.Models.Discapacitados;
import Proyecto.ComunidadAraguaney.Models.Enfermos;
import Proyecto.ComunidadAraguaney.Models.Integrantes;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class IntegrantesPdf {
    
     List<Integrantes> lista;

    public IntegrantesPdf(List<Integrantes> lista) {
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

        celda.setPhrase(new Phrase("CI JEFE DE FAMILIA", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("CI INTEGRANTE", fuente));
        tabla.addCell(celda);

    }

    private void escribirDatos(PdfPTable tabla) {

        for (Integrantes dato : lista) {

            tabla.addCell(String.valueOf(dato.getIdIntegrante()));
            tabla.addCell(String.valueOf(dato.getJefeFamilia().getCedulaJefe()));
            tabla.addCell(String.valueOf(dato.getDatosPersonales().getCedula()));

        }

    }

    public void exportar(HttpServletResponse response) throws IOException, DocumentException {

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Reporte Integrantes", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(3);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatos(tabla);

        documento.add(tabla);
        documento.close();

    }

    
}
