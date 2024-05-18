package holidays.holidays.core.interfaces.services;

import java.util.Date;
import java.util.List;

import holidays.holidays.core.entities.DTOs.FestivoDto;


public interface IFestivoServices {
    
    public List<FestivoDto> obtenerFestivos(int año);
    
    public boolean diaFestivo(Date Fecha);

    public boolean dateValida(String verificarFecha);

}
