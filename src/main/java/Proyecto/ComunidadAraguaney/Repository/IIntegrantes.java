package Proyecto.ComunidadAraguaney.Repository;

import Proyecto.ComunidadAraguaney.Models.Integrantes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIntegrantes extends CrudRepository<Integrantes,Integer>{

}
