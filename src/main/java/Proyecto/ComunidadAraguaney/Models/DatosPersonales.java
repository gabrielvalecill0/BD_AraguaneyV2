package Proyecto.ComunidadAraguaney.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Logger;

@Entity
@Table(name ="datosPersonales")
public class DatosPersonales {
    
    @Id
    int cedula;
    String apellido,direccion,fNacimiento,fResidencia,nombre,status;
    int edad;

    @PostLoad
    public void calcularEdad() {
    LocalDate fechaNacimiento = LocalDate.parse(fNacimiento);
    edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
    
    }
    
    @OneToOne
    private Enfermos enfermos;
    
    @OneToOne
    private Discapacitados discapacitados;
    
    @OneToOne
    private JefeFamilia JefeFamilia;

    @OneToOne
    private Integrantes integrantes;
    
    public DatosPersonales() {
    }

    public DatosPersonales(int cedula, String apellido, String direccion, String fNacimiento, String fResidencia, String nombre, String status, int edad, Enfermos enfermos, Discapacitados discapacitados, JefeFamilia JefeFamilia, Integrantes integrantes) {
        this.cedula = cedula;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fNacimiento = fNacimiento;
        this.fResidencia = fResidencia;
        this.nombre = nombre;
        this.status = status;
        this.edad = edad;
        this.enfermos = enfermos;
        this.discapacitados = discapacitados;
        this.JefeFamilia = JefeFamilia;
        this.integrantes = integrantes;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getfResidencia() {
        return fResidencia;
    }

    public void setfResidencia(String fResidencia) {
        this.fResidencia = fResidencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Enfermos getEnfermos() {
        return enfermos;
    }

    public void setEnfermos(Enfermos enfermos) {
        this.enfermos = enfermos;
    }

    public Discapacitados getDiscapacitados() {
        return discapacitados;
    }

    public void setDiscapacitados(Discapacitados discapacitados) {
        this.discapacitados = discapacitados;
    }

    public JefeFamilia getJefeFamilia() {
        return JefeFamilia;
    }

    public void setJefeFamilia(JefeFamilia JefeFamilia) {
        this.JefeFamilia = JefeFamilia;
    }

    public Integrantes getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(Integrantes integrantes) {
        this.integrantes = integrantes;
    }
    
}
