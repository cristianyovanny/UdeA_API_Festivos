package holidays.holidays.presentacion;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import holidays.holidays.core.entities.Tipo;
import holidays.holidays.core.interfaces.services.ITipoServices;

@RestController
@RequestMapping("/tipo")
public class TipoController {
    
    private ITipoServices services;

    public TipoController(ITipoServices services) {
        this.services = services;
    }

    @RequestMapping(value = "/obtenerTipos/{Tipo}", method = RequestMethod.GET)
    public List<Tipo> obtenerTipos(@PathVariable int id){
        return services.obtenerTipos(id);
    }
}
