package apap.tugas.sivaksin.repository;
import apap.tugas.sivaksin.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
    
}
