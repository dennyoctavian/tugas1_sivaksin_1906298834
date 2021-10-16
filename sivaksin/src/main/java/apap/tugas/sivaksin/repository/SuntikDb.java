package apap.tugas.sivaksin.repository;
import apap.tugas.sivaksin.model.SuntikModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuntikDb extends JpaRepository<SuntikModel, Long>{
    // Optional<SuntikModel> findByBatchId(String batchId);
}
