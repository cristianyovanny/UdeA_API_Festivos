package holidays.holidays.aplicacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import holidays.holidays.core.entities.Festivo;
import holidays.holidays.core.interfaces.repositories.IFestivoRepositories;
import holidays.holidays.core.interfaces.services.IFestivoServices;

@Service
public class FestivoServices implements IFestivoServices{

    private IFestivoRepositories repositories;

    public FestivoServices(IFestivoRepositories repositories) {
        this.repositories = repositories;
    }
    
    @Override
    public List<Festivo> obtenerFestivos(Integer año) {
        Optional<Festivo> FestivoEncontrado = repositories.findById(año);
        return FestivoEncontrado.isEmpty() ? null :  (List<Festivo>) FestivoEncontrado.get();
    }

    @Override
    public boolean diaFestivo(Date Fecha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'diaFestivo'");
    }


    @Override
    public boolean fechaValida(String verificarFecha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fechaValida'");
    }
    
}
