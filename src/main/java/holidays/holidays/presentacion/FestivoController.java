package holidays.holidays.presentacion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import holidays.holidays.aplicacion.FestivoServices;
import holidays.holidays.core.entities.DTOs.FestivoDto;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/festivo")
public class FestivoController {
    
    private FestivoServices services;

    public FestivoController(FestivoServices services) {
        this.services = services;
    }

    @RequestMapping(value="/obtenerfestivos/{año}", method=RequestMethod.GET)
    public List<FestivoDto> obtenerFestivos(@PathVariable int año){
        return services.obtenerFestivos(año);
    }

    @GetMapping("/validarfestivo/{año}/{mes}/{dia}")
    public String verifyFestivos(@PathVariable int año, @PathVariable int mes, @PathVariable int dia){
        if (services.dateValida(String.valueOf(año)+"-"+String.valueOf(mes)+"-"+String.valueOf(dia))) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.set(año, mes - 1, dia);
            Date verificarFecha = calendar.getTime();
            return services.diaFestivo(verificarFecha) ? "Es festivo" : "No es festivo";
        }
        else {
            return "Fecha no válida";
        }
    }

}
