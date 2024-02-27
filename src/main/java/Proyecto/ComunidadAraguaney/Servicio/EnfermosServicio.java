package Proyecto.ComunidadAraguaney.Servicio;

import Proyecto.ComunidadAraguaney.IServicio.IDatosServicio;
import Proyecto.ComunidadAraguaney.IServicio.IEnfermosServicio;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Models.Enfermos;
import Proyecto.ComunidadAraguaney.Repository.IDatosPersonales;
import Proyecto.ComunidadAraguaney.Repository.IEnfermos;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnfermosServicio implements IEnfermosServicio {

    @Autowired
    IEnfermos servicio;

    @Override
    public List<Enfermos> listar() {

        return (List<Enfermos>) servicio.findAll();
    }

    @Override
    public Optional<Enfermos> listarPorCedula(int cedula) {
        return servicio.findById(cedula);
    }

    @Override
    public int guardar(Enfermos p) {
        int res = 0;
        Enfermos Enfermos = servicio.save(p);
        if (!Enfermos.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrar(int cedula) {
        servicio.deleteById(cedula);
    }

}
