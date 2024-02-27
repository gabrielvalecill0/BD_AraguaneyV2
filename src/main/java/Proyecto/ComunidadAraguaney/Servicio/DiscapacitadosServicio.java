package Proyecto.ComunidadAraguaney.Servicio;

import Proyecto.ComunidadAraguaney.IServicio.IDatosServicio;
import Proyecto.ComunidadAraguaney.IServicio.IDiscapacitadosServicio;
import Proyecto.ComunidadAraguaney.IServicio.IEnfermosServicio;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Models.Discapacitados;
import Proyecto.ComunidadAraguaney.Models.Enfermos;
import Proyecto.ComunidadAraguaney.Repository.IDatosPersonales;
import Proyecto.ComunidadAraguaney.Repository.IDiscapacitados;
import Proyecto.ComunidadAraguaney.Repository.IEnfermos;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscapacitadosServicio implements IDiscapacitadosServicio {

    @Autowired
    IDiscapacitados servicio;

    @Override
    public List<Discapacitados> listar() {

        return (List<Discapacitados>) servicio.findAll();
    }

    @Override
    public Optional<Discapacitados> listarPorCedula(int cedula) {
        return servicio.findById(cedula);
    }

    @Override
    public int guardar(Discapacitados p) {
        int res = 0;
        Discapacitados Discapacitados = servicio.save(p);
        if (!Discapacitados.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrar(int cedula) {
        servicio.deleteById(cedula);
    }

}
