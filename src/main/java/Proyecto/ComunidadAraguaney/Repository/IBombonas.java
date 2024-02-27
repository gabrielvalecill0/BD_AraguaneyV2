package Proyecto.ComunidadAraguaney.Repository;

import Proyecto.ComunidadAraguaney.Models.Bombonas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBombonas extends CrudRepository<Bombonas,Integer>{
 
       
    @Query(value = "SELECT COALESCE(SUM(bombonas_comprar), 0) FROM Bombonas;",nativeQuery= true)
    int contadorBombonas();
   
    @Query(value = "SELECT COALESCE(SUM(kg10), 0) FROM Bombonas;",nativeQuery= true)
    int contador10kg();
    
    @Query(value = "SELECT COALESCE(SUM(kg18), 0) FROM Bombonas;",nativeQuery= true)
    int contador18kg();

    @Query(value = "SELECT COALESCE(SUM(kg43), 0) FROM Bombonas;",nativeQuery= true)
    int contador43kg();

    @Query(value = "SELECT COALESCE(SUM(monto), 0) FROM Bombonas;",nativeQuery= true)
    int MontoTotal();
    
}
