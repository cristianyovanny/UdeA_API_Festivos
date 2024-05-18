package holidays.holidays.aplicacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import holidays.holidays.core.entities.Festivo;
import holidays.holidays.core.entities.DTOs.FestivoDto;
import holidays.holidays.core.interfaces.repositories.IFestivoRepositories;
import holidays.holidays.core.interfaces.services.IFestivoServices;

@Service
public class FestivoServices implements IFestivoServices{

    private IFestivoRepositories repositories;

    public FestivoServices(IFestivoRepositories repositories) {
        this.repositories = repositories;
    }

    private Date getDomingoPascua(int año) {
        int dia, mes, a, b, c, d;
        a = año % 19;
        b = año % 4;
        c = año % 7;
        d = (19*a+24) % 30;
        int dias = d + (2*b + 4*c + 6*d + 5) % 7;

        if (dias > 9) {
            dia = dias - 9;
            mes = 4;
        }
        else {
            dia = dias + 22;
            mes = 3;
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(dia, mes - 1, año);
        return calendar.getTime();
    }

    private Date addDias (Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(fecha);
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();
    }  

    private Date nextLunes(Date fecha) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(fecha);
        int diaDeLaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        if (diaDeLaSemana == Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 7);
        } else {
            int daySiguienteLunes = (Calendar.MONDAY - diaDeLaSemana + 7) % 7;
            calendar.add(Calendar.DATE, daySiguienteLunes);
        }
        return calendar.getTime();
    }
    
    private List<Festivo> calculateFestivos(List<Festivo> festivos, int año){
        if (festivos != null) {
            Date pascua = getDomingoPascua(año);
            int i = 0;
            for(Festivo festivo : festivos) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                switch (festivo.getTipo().getId()) {
                    case 1:
                        calendar.set(año, festivo.getMes()-1, festivo.getDia());
                        festivo.setFecha(calendar.getTime());
                        break;
                    case 2:
                        calendar.set(año, festivo.getMes()-1, festivo.getDia());
                        festivo.setFecha(nextLunes(calendar.getTime()));
                        break;
                    case 3:
                        festivo.setFecha(addDias(pascua, festivo.getDiasPascua()));
                        break;
                    case 4:
                        festivo.setFecha(nextLunes(addDias(pascua, festivo.getDiasPascua())));
                        break;
                }
                festivos.set(i, festivo);
                i++;
            }
        }
        return festivos;
    }

    
    private boolean equalFecha(Date fecha1, Date fecha2) {
        DateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha1String = formatFecha.format(fecha1);
        String fecha2String = formatFecha.format(fecha2);
        return fecha1String.equals(fecha2String);
    }


    private boolean validateDiaFestivo(List<Festivo> festivos, Date Fecha) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(Fecha);
        if (festivos != null) {
            festivos = calculateFestivos(festivos, calendar.get(Calendar.YEAR));
            for (Festivo festivo : festivos) {
                if (equalFecha(festivo.getFecha(), Fecha)) {
                    return true;
                }
            }
            
        }
        return false;
    }

    @Override
    public List<FestivoDto> obtenerFestivos(int año) {
        List<Festivo> calculateFestivos = calculateFestivos(repositories.findAll(), año);
        List<FestivoDto> fechas = new ArrayList<FestivoDto>();
        for (Festivo festivo : calculateFestivos) {
            fechas.add(new FestivoDto(festivo.getNombre(), festivo.getFecha()));
        }
        return fechas;
    }

    @Override
    public boolean diaFestivo(Date Fecha) {
        List<Festivo> festivos = repositories.findAll();
        return validateDiaFestivo(festivos, Fecha);
    }


    @Override
    public boolean dateValida(String verificarFecha) {
        try {
            DateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatFecha.setLenient(false);
            formatFecha.parse(verificarFecha);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
