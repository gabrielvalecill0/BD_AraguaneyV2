package Proyecto.ComunidadAraguaney.Repository;

import Proyecto.ComunidadAraguaney.Models.JefeFamilia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJefeFamilia extends CrudRepository<JefeFamilia,Integer>{
    
    @Query(value = "SELECT COALESCE(SUM(nro_discapacitados), 0) FROM jefe_familia;",nativeQuery= true)
    int NroDiscapacitados();
    
    @Query(value = "SELECT COUNT(*) FROM jefe_familia WHERE recibes_bombonas = TRUE;",nativeQuery= true)
    int recibesBombonasV();
    
    @Query(value = "SELECT COUNT(*) FROM jefe_familia WHERE recibes_bombonas = FALSE;",nativeQuery= true)
    int recibesBombonasF();
    
    @Query(value = "SELECT COUNT(*) FROM jefe_familia WHERE recibes_clap = TRUE;",nativeQuery= true)
    int recibesClapV();
    
    @Query(value = "SELECT COUNT(*) FROM jefe_familia WHERE recibes_clap = FALSE;",nativeQuery= true)
    int recibesClapF();
    
    @Query(value = "SELECT COUNT(*) FROM jefe_familia;",nativeQuery= true)
    int NroJefes();
}
