package holidays.holidays.presentacion;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import holidays.holidays.core.entities.DTOs.FestivoDto;
import holidays.holidays.core.interfaces.services.IFestivoServices;

@RestController
@RequestMapping("/festivo")
public class FestivoController {
    
    private IFestivoServices services;

    public FestivoController(IFestivoServices services) {
        this.services = services;
    }

    @RequestMapping(value = "/obtenerfestivos/{año}", method = RequestMethod.GET)
    public List<FestivoDto> obtenerFestivos(@PathVariable int año){
        return services.obtenerFestivos(año);
    }

    @RequestMapping(value = "/validarfestivo/{dia}-{mes}-{año}", method = RequestMethod.GET)
    public String verifyFestivos(@PathVariable int dia, @PathVariable int mes, @PathVariable int año) throws ParseException{
        if (services.dateValida(String.valueOf(año)+"-"+String.valueOf(mes)+"-"+String.valueOf(dia))) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.set(dia, mes - 1, año);
            Date verificarFecha = calendar.getTime();
            return services.diaFestivo(verificarFecha) ? "Es festivo" : "No es festivo";
        }
        return "Fecha no válida";
    }

}
