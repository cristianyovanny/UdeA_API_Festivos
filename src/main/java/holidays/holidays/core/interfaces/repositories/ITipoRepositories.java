package holidays.holidays.core.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import holidays.holidays.core.entities.Tipo;

@Repository
public interface ITipoRepositories extends JpaRepository<Tipo, Integer>{
    
}
