package Proyecto.ComunidadAraguaney.Repository;

import Proyecto.ComunidadAraguaney.Models.Enfermos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnfermos extends CrudRepository<Enfermos,Integer>{
    
}
