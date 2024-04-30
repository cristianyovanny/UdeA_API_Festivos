package holidays.holidays.core.interfaces.services;

import java.util.Date;
import java.util.List;

import holidays.holidays.core.entities.Festivo;


public interface IFestivoServices {
    
    public List<Festivo> obtenerFestivos(Integer año);
    
    public boolean diaFestivo(Date Fecha);

    public boolean fechaValida(String verificarFecha);
}
