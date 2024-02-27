package Proyecto.ComunidadAraguaney.Servicio;

import Proyecto.ComunidadAraguaney.IServicio.IJefeServicio;
import Proyecto.ComunidadAraguaney.Models.JefeFamilia;
import Proyecto.ComunidadAraguaney.Repository.IJefeFamilia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JefeFamiliaServicio implements IJefeServicio {

    @Autowired
    IJefeFamilia servicio;

    @Override
    public List<JefeFamilia> listar() {

        return (List<JefeFamilia>) servicio.findAll();
    }

    @Override
    public Optional<JefeFamilia> listarPorCedula(int cedula) {
        return servicio.findById(cedula);
    }

    @Override
    public int guardar(JefeFamilia p) {

        int res = 0;
        JefeFamilia JefeFamilia = servicio.save(p);
        if (!JefeFamilia.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrar(int cedula) {
        servicio.deleteById(cedula);
    }
    
    public int nroDiscapacitados(){
        int Discapacitados = servicio.NroDiscapacitados();
        return Discapacitados;
    }

    public int recibesBombonasF(){
        int resultado = servicio.recibesBombonasF();
        return resultado;
    }
    
    public int recibesBombonasV(){
        int resultado = servicio.recibesBombonasV();
        return resultado;
    }
    
    public int recibesClapF(){
        int resultado = servicio.recibesClapF();
        return resultado;
    }
    
    public int recibesClapV(){
        int resultado = servicio.recibesClapV();
        return resultado;
    }
    
    public int nroJefes(){
        int resultado = servicio.NroJefes();
        return resultado;
    }
    
}
