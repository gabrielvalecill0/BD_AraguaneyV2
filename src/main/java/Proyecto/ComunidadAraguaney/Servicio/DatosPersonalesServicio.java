package Proyecto.ComunidadAraguaney.Servicio;

import Proyecto.ComunidadAraguaney.IServicio.IDatosServicio;
import Proyecto.ComunidadAraguaney.Models.DatosPersonales;
import Proyecto.ComunidadAraguaney.Repository.IDatosPersonales;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosPersonalesServicio implements IDatosServicio {

    @Autowired
    IDatosPersonales servicio;

    @Override
    public List<DatosPersonales> listar() {

        return (List<DatosPersonales>) servicio.findAll();
    }

    @Override
    public Optional<DatosPersonales> listarPorCedula(int cedula) {
        return servicio.findById(cedula);
    }

    @Override
    public int guardar(DatosPersonales p) {

        int res = 0;
        DatosPersonales datosPersonales = servicio.save(p);
        if (!datosPersonales.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrar(int cedula) {
        servicio.deleteById(cedula);
    }

    public int calcularNinos() {
        int ninos = servicio.contadorNinos();
        return ninos;
    }

    public int calcularMayores() {
        int mayores = servicio.contadorMayores();
        return mayores;
    }
    
    public int personas() {
        int resultado = servicio.Personas();
        return resultado;
    }

    public int contadorAdultos() {
        int resultado = servicio.contadorAdultos();
        return resultado;
    }
    
}
