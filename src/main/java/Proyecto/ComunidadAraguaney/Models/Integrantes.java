package Proyecto.ComunidadAraguaney.Models;

import jakarta.persistence.*;

@Entity
@Table (name = "Integrantes")
public class Integrantes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idIntegrante;
    
    @OneToOne
    @JoinColumn(name = "cedula", referencedColumnName ="cedula")
    DatosPersonales datosPersonales;
    
    @ManyToOne
    @JoinColumn(name = "cedulaJefe", referencedColumnName ="cedulaJefe")
    JefeFamilia jefeFamilia;

    public Integrantes() {
    }

    public Integrantes(int idIntegrante, DatosPersonales datosPersonales, JefeFamilia jefeFamilia) {
        this.idIntegrante = idIntegrante;
        this.datosPersonales = datosPersonales;
        this.jefeFamilia = jefeFamilia;
    }

    public int getIdIntegrante() {
        return idIntegrante;
    }

    public void setIdIntegrante(int idIntegrante) {
        this.idIntegrante = idIntegrante;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public JefeFamilia getJefeFamilia() {
        return jefeFamilia;
    }

    public void setJefeFamilia(JefeFamilia jefeFamilia) {
        this.jefeFamilia = jefeFamilia;
    }
    
}
