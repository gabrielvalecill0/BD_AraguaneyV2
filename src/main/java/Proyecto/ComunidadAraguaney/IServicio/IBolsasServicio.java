package Proyecto.ComunidadAraguaney.IServicio;

import Proyecto.ComunidadAraguaney.Models.Bolsas;
import java.util.List;
import java.util.Optional;


public interface IBolsasServicio {
    
    public List<Bolsas> listar();
    public Optional<Bolsas> listarPorCedula(int cedula);
    public int guardar(Bolsas p);
    public void borrar(int cedula);
    
}
