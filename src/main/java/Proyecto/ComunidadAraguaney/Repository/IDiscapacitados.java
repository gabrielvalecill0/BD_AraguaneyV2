package Proyecto.ComunidadAraguaney.Repository;

import Proyecto.ComunidadAraguaney.Models.Discapacitados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiscapacitados extends CrudRepository<Discapacitados,Integer>{
    
}
