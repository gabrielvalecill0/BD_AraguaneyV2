package Proyecto.ComunidadAraguaney.IServicio;

import Proyecto.ComunidadAraguaney.Models.Discapacitados;
import java.util.List;
import java.util.Optional;

public interface IDiscapacitadosServicio {
    
    public List<Discapacitados> listar();
    public Optional<Discapacitados> listarPorCedula(int cedula);
    public int guardar(Discapacitados p);
    public void borrar(int cedula);
    
}
