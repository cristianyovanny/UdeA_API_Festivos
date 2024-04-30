package holidays.holidays.presentacion;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import holidays.holidays.core.entities.Festivo;
import holidays.holidays.core.interfaces.services.IFestivoServices;

@RestController
@RequestMapping("/festivo")
public class FestivoController {
    
    private IFestivoServices services;

    public FestivoController(IFestivoServices services) {
        this.services = services;
    }

    @RequestMapping(value = "/obtenerFestivos/{año}", method = RequestMethod.GET)
    public List<Festivo> obtenerFestivos(@PathVariable Integer año){
        return services.obtenerFestivos(año);
    }

    @RequestMapping(value = "/diaFestivo/{Festivo}", method = RequestMethod.POST)
    public boolean diaFestivo(@RequestBody Date Festivo) {
        return services.diaFestivo(Festivo);
    }

    @RequestMapping(value = "/fechaValida/{verificarFecha}", method = RequestMethod.POST)
    public boolean fechaValida(@RequestBody String verificarFecha){
        return services.fechaValida(verificarFecha);
    }
}
