package holidays.holidays.core.entities.DTOs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FestivoDto {
    
    private String festivo;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public FestivoDto() {
    }

    public FestivoDto(String festivo, java.util.Date date) {
        this.festivo = festivo;
        this.fecha = date;
    }

    public String getFestivo() {
        return festivo;
    }

    public void setFestivo(String festivo) {
        this.festivo = festivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
