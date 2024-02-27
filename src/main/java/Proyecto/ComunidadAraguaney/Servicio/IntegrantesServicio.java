package Proyecto.ComunidadAraguaney.Servicio;

import Proyecto.ComunidadAraguaney.IServicio.IIntegrantesServicio;
import Proyecto.ComunidadAraguaney.Models.Integrantes;
import Proyecto.ComunidadAraguaney.Repository.IIntegrantes;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegrantesServicio implements IIntegrantesServicio {

    @Autowired
    IIntegrantes servicio;

    @Override
    public List<Integrantes> listar() {

        return (List<Integrantes>) servicio.findAll();
    }

    @Override
    public Optional<Integrantes> listarPorCedula(int cedula) {
        return servicio.findById(cedula);
    }

    @Override
    public int guardar(Integrantes p) {

        int res = 0;
        Integrantes Integrantes = servicio.save(p);
        if (!Integrantes.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrar(int cedula) {
        servicio.deleteById(cedula);
    }

}
