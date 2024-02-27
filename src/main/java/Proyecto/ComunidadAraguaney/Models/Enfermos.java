package Proyecto.ComunidadAraguaney.Models;

import jakarta.persistence.*;

@Entity
@Table(name ="Enfermos")
public class Enfermos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idEnfermos;
    String enfermedad,observacion;
 
    @OneToOne
    @JoinColumn(name = "cedula", referencedColumnName ="cedula")
    DatosPersonales datosPersonales;

    public Enfermos() {
    }

    public Enfermos(int idEnfermos, String enfermedad, String observacion, DatosPersonales datosPersonales) {
        this.idEnfermos = idEnfermos;
        this.enfermedad = enfermedad;
        this.observacion = observacion;
        this.datosPersonales = datosPersonales;
    }

    public int getIdEnfermos() {
        return idEnfermos;
    }

    public void setIdEnfermos(int idEnfermos) {
        this.idEnfermos = idEnfermos;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
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
