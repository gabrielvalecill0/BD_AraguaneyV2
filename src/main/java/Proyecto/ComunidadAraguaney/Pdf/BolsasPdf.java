package Proyecto.ComunidadAraguaney.Pdf;

import Proyecto.ComunidadAraguaney.Models.Bolsas;
import Proyecto.ComunidadAraguaney.Models.Bombonas;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class BolsasPdf {

    List<Bolsas> lista;

    public BolsasPdf(List<Bolsas> lista) {
        super();
        this.lista = lista;
    }

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {

        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        
        celda.setPhrase(new Phrase("ID BOLSAS", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("BOLSAS A COMPRAR", fuente));
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

        for (Bolsas dato : lista) {

            tabla.addCell(String.valueOf(dato.getIdBolsas()));
            tabla.addCell(String.valueOf(dato.getBolsasComprar()));
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

        Paragraph titulo = new Paragraph("Reporte Gestion De Bolsas Clap", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatos(tabla);

        documento.add(tabla);
        documento.close();

    }

}
