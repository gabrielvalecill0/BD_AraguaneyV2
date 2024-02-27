package Proyecto.ComunidadAraguaney.Models;

import jakarta.persistence.*;

@Entity
@Table (name = "Bombonas")
public class Bombonas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idBombonas;
    
    int bombonasComprar,kg10,kg18,kg43,monto,referencia;
    boolean efectivo;
    String fecha;
    
    @ManyToOne
    @JoinColumn(name = "cedulaJefe", referencedColumnName ="cedulaJefe")
    JefeFamilia jefeFamilia;

    public Bombonas() {
    }

    public Bombonas(int idBombonas, int bombonasComprar, int kg10, int kg18, int kg43, int monto, int referencia, boolean efectivo, String fecha, JefeFamilia jefeFamilia) {
        this.idBombonas = idBombonas;
        this.bombonasComprar = bombonasComprar;
        this.kg10 = kg10;
        this.kg18 = kg18;
        this.kg43 = kg43;
        this.monto = monto;
        this.referencia = referencia;
        this.efectivo = efectivo;
        this.fecha = fecha;
        this.jefeFamilia = jefeFamilia;
    }

    public int getIdBombonas() {
        return idBombonas;
    }

    public void setIdBombonas(int idBombonas) {
        this.idBombonas = idBombonas;
    }

    public int getBombonasComprar() {
        return bombonasComprar;
    }

    public void setBombonasComprar(int bombonasComprar) {
        this.bombonasComprar = bombonasComprar;
    }

    public int getKg10() {
        return kg10;
    }

    public void setKg10(int kg10) {
        this.kg10 = kg10;
    }

    public int getKg18() {
        return kg18;
    }

    public void setKg18(int kg18) {
        this.kg18 = kg18;
    }

    public int getKg43() {
        return kg43;
    }

    public void setKg43(int kg43) {
        this.kg43 = kg43;
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
