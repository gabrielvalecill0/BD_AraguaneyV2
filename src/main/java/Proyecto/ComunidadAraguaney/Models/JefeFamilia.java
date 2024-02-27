package Proyecto.ComunidadAraguaney.Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name ="JefeFamilia")
public class JefeFamilia {
    
    @Id
    int cedulaJefe;
    
    boolean recibesBombonas,recibesClap;
    int nroDiscapacitados,cargaFamiliar;
    
    @OneToOne
    @JoinColumn(name = "cedula", referencedColumnName ="cedula")
    DatosPersonales datosPersonales;
    
    @OneToMany
    List<Integrantes> integrantes;

    @OneToMany
    List<Bombonas> bombonas;
    
    @OneToMany
    List<Bolsas> Bolsas;
    
    public JefeFamilia() {
    }

    public JefeFamilia(int cedulaJefe, boolean recibesBombonas, boolean recibesClap, int nroDiscapacitados, int cargaFamiliar, DatosPersonales datosPersonales, List<Integrantes> integrantes, List<Bombonas> bombonas, List<Bolsas> Bolsas) {
        this.cedulaJefe = cedulaJefe;
        this.recibesBombonas = recibesBombonas;
        this.recibesClap = recibesClap;
        this.nroDiscapacitados = nroDiscapacitados;
        this.cargaFamiliar = cargaFamiliar;
        this.datosPersonales = datosPersonales;
        this.integrantes = integrantes;
        this.bombonas = bombonas;
        this.Bolsas = Bolsas;
    }

    public int getCedulaJefe() {
        return cedulaJefe;
    }

    public void setCedulaJefe(int cedulaJefe) {
        this.cedulaJefe = cedulaJefe;
    }

    public boolean isRecibesBombonas() {
        return recibesBombonas;
    }

    public void setRecibesBombonas(boolean recibesBombonas) {
        this.recibesBombonas = recibesBombonas;
    }

    public boolean isRecibesClap() {
        return recibesClap;
    }

    public void setRecibesClap(boolean recibesClap) {
        this.recibesClap = recibesClap;
    }

    public int getNroDiscapacitados() {
        return nroDiscapacitados;
    }

    public void setNroDiscapacitados(int nroDiscapacitados) {
        this.nroDiscapacitados = nroDiscapacitados;
    }

    public int getCargaFamiliar() {
        return cargaFamiliar;
    }

    public void setCargaFamiliar(int cargaFamiliar) {
        this.cargaFamiliar = cargaFamiliar;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public List<Integrantes> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrantes> integrantes) {
        this.integrantes = integrantes;
    }

    public List<Bombonas> getBombonas() {
        return bombonas;
    }

    public void setBombonas(List<Bombonas> bombonas) {
        this.bombonas = bombonas;
    }

    public List<Bolsas> getBolsas() {
        return Bolsas;
    }

    public void setBolsas(List<Bolsas> Bolsas) {
        this.Bolsas = Bolsas;
    }

    
    
}
