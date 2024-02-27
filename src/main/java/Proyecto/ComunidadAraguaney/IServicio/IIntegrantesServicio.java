package Proyecto.ComunidadAraguaney.IServicio;

import Proyecto.ComunidadAraguaney.Models.Integrantes;
import java.util.List;
import java.util.Optional;


public interface IIntegrantesServicio {
    
    public List<Integrantes> listar();
    public Optional<Integrantes> listarPorCedula(int cedula);
    public int guardar(Integrantes p);
    public void borrar(int cedula);
    
}
