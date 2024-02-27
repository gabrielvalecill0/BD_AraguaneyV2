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
import Proyecto.ComunidadAraguaney.IServicio.IBolsasServicio;
import Proyecto.ComunidadAraguaney.Models.Bolsas;
import Proyecto.ComunidadAraguaney.Repository.IBolsas;

@Service
public class BolsasServicio implements IBolsasServicio {

    @Autowired
    IBolsas servicio;

    @Override
    public List<Bolsas> listar() {

        return (List<Bolsas>) servicio.findAll();
    }

    @Override
    public Optional<Bolsas> listarPorCedula(int cedula) {
        return servicio.findById(cedula);
    }

    @Override
    public int guardar(Bolsas p) {

        int res = 0;
        Bolsas Bolsas = servicio.save(p);
        if (!Bolsas.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrar(int cedula) {
        servicio.deleteById(cedula);
    }

}
