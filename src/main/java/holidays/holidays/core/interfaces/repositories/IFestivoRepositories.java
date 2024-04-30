package holidays.holidays.core.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import holidays.holidays.core.entities.Festivo;

@Repository
public interface IFestivoRepositories extends JpaRepository<Festivo, Integer>{
    
}
