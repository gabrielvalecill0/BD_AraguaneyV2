package Proyecto.ComunidadAraguaney.Models;

import jakarta.persistence.*;

@Entity
@Table(name ="Discapacitados")
public class Discapacitados {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idDiscapacidad;
    String discapacidad,observacion;
    
    @OneToOne
    @JoinColumn(name = "cedula", referencedColumnName ="cedula")
    DatosPersonales datosPersonales;

    public Discapacitados() {
    }

    public Discapacitados(int idDiscapacidad, String discapacidad, String observacion, DatosPersonales datosPersonales) {
        this.idDiscapacidad = idDiscapacidad;
        this.discapacidad = discapacidad;
        this.observacion = observacion;
        this.datosPersonales = datosPersonales;
    }

    public int getIdDiscapacidad() {
        return idDiscapacidad;
    }

    public void setIdDiscapacidad(int idDiscapacidad) {
        this.idDiscapacidad = idDiscapacidad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }
    
}
