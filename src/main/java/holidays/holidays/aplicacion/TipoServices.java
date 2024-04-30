package holidays.holidays.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import holidays.holidays.core.entities.Tipo;
import holidays.holidays.core.interfaces.repositories.ITipoRepositories;
import holidays.holidays.core.interfaces.services.ITipoServices;

@Service
public class TipoServices implements ITipoServices{

    private ITipoRepositories repositories;

    public TipoServices(ITipoRepositories repositories) {
        this.repositories = repositories;
    }

    @Override
    public List<Tipo> obtenerTipos(int id) {
        return repositories.findAll();
    }
    
}
