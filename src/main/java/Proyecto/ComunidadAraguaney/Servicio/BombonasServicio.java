package Proyecto.ComunidadAraguaney.Servicio;

import Proyecto.ComunidadAraguaney.IServicio.IBombonasServicio;
import Proyecto.ComunidadAraguaney.IServicio.IDatosServicio;
import Proyecto.ComunidadAraguaney.Models.Bombonas;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Repository.IBombonas;
import Proyecto.ComunidadAraguaney.Repository.IDatosPersonales;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BombonasServicio implements IBombonasServicio {

    @Autowired
    IBombonas servicio;

    @Override
    public List<Bombonas> listar() {

        return (List<Bombonas>) servicio.findAll();
    }

    @Override
    public Optional<Bombonas> listarPorCedula(int cedula) {
        return servicio.findById(cedula);
    }

    @Override
    public int guardar(Bombonas p) {

        int res = 0;
        Bombonas Bombonas = servicio.save(p);
        if (!Bombonas.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrar(int cedula) {
        servicio.deleteById(cedula);
    }

    public int totalBombonas (){
        int resultado = servicio.contadorBombonas();
        return resultado;
    }
    
    public int montoTotal (){
        int resultado = servicio.MontoTotal();
        return resultado;
    }

    public int Bombonas10kg (){
        int resultado = servicio.contador10kg();
        return resultado;
    }

    public int Bombonas18kg (){
        int resultado = servicio.contador18kg();
        return resultado;
    }

    public int Bombonas43kg (){
        int resultado = servicio.contador43kg();
        return resultado;
    }

}
