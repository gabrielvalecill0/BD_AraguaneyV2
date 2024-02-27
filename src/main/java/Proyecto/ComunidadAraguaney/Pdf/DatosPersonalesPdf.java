package Proyecto.ComunidadAraguaney.Pdf;

import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class DatosPersonalesPdf {

    List<DatosPersonales> lista;

    public DatosPersonalesPdf(List<DatosPersonales> lista) {
        super();
        this.lista = lista;
    }

    private void escribirCabeceraDeLaTabla(PdfPTable tabla) {

        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        
        celda.setPhrase(new Phrase("CEDULA", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("NOMBRE", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("APELLIDO", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("DIRRECIÒN", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("EDAD", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("STATUS", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("FECHA NACIMIENTO", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("FECHA RESIDENCIA", fuente));
        tabla.addCell(celda);

    }

    private void escribirDatos(PdfPTable tabla) {

        for (DatosPersonales dato : lista) {

            tabla.addCell(String.valueOf(dato.getCedula()));
            tabla.addCell(dato.getNombre());
            tabla.addCell(dato.getApellido());
            tabla.addCell(dato.getDireccion());
            tabla.addCell(String.valueOf(dato.getEdad()));
            tabla.addCell(dato.getStatus());
            tabla.addCell(dato.getfNacimiento());
            tabla.addCell(dato.getfResidencia());

        }

    }

    public void exportar(HttpServletResponse response) throws IOException, DocumentException {

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Reporte Datos Personales", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(8);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidthPercentage(110);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatos(tabla);

        documento.add(tabla);
        documento.close();

    }

}
