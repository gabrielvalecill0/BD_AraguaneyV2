package Proyecto.ComunidadAraguaney.Models;

import jakarta.persistence.*;

@Entity
@Table (name = "Bolsas")
public class Bolsas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idBolsas;
    
    int bolsasComprar,monto,referencia;
    boolean efectivo;
    String fecha;
    
    @ManyToOne
    @JoinColumn(name = "cedulaJefe", referencedColumnName ="cedulaJefe")
    JefeFamilia jefeFamilia;

    public Bolsas() {
    }

    public Bolsas(int idBolsas, int bolsasComprar, int monto, int referencia, boolean efectivo, String fecha, JefeFamilia jefeFamilia) {
        this.idBolsas = idBolsas;
        this.bolsasComprar = bolsasComprar;
        this.monto = monto;
        this.referencia = referencia;
        this.efectivo = efectivo;
        this.fecha = fecha;
        this.jefeFamilia = jefeFamilia;
    }

    public int getIdBolsas() {
        return idBolsas;
    }

    public void setIdBolsas(int idBolsas) {
        this.idBolsas = idBolsas;
    }

    public int getBolsasComprar() {
        return bolsasComprar;
    }

    public void setBolsasComprar(int bolsasComprar) {
        this.bolsasComprar = bolsasComprar;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public boolean isEfectivo() {
        return efectivo;
    }

    public void setEfectivo(boolean efectivo) {
        this.efectivo = efectivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public JefeFamilia getJefeFamilia() {
        return jefeFamilia;
    }

    public void setJefeFamilia(JefeFamilia jefeFamilia) {
        this.jefeFamilia = jefeFamilia;
    }
    
  
}
