package Proyecto.ComunidadAraguaney.Pdf;

import Proyecto.ComunidadAraguaney.Models.Bombonas;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class BombonasPdf {

    List<Bombonas> lista;

    public BombonasPdf(List<Bombonas> lista) {
        super();
        this.lista = lista;
    }

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {

        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        
        celda.setPhrase(new Phrase("ID BOMBONAS", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("BOMBONAS A COMPRAR", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("10KG", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("18KG", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("43KG", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("EFECTIVO", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("REFERENCIA", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("MONTO", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("CI JEFE", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("FECHA", fuente));
        tabla.addCell(celda);

    }

    private void escribirDatos(PdfPTable tabla) {

        for (Bombonas dato : lista) {

            tabla.addCell(String.valueOf(dato.getIdBombonas()));
            tabla.addCell(String.valueOf(dato.getBombonasComprar()));
            tabla.addCell(String.valueOf(dato.getKg10()));
            tabla.addCell(String.valueOf(dato.getKg18()));
            tabla.addCell(String.valueOf(dato.getKg43()));
            tabla.addCell(String.valueOf(dato.isEfectivo()));
            tabla.addCell(String.valueOf(dato.getReferencia()));
            tabla.addCell(String.valueOf(dato.getMonto()));
            tabla.addCell(String.valueOf(dato.getJefeFamilia().getCedulaJefe()));
            tabla.addCell(dato.getFecha());

        }

    }

    public void exportar(HttpServletResponse response) throws IOException, DocumentException {

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Reporte Gestion De Bombonas", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(10);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatos(tabla);

        documento.add(tabla);
        documento.close();

    }

}
