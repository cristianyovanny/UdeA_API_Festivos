package holidays.holidays.core.interfaces.services;

import java.util.List;

import holidays.holidays.core.entities.Tipo;

public interface ITipoServices {
    
    public List<Tipo> obtenerTipos(int id);
}
