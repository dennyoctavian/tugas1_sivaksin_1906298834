package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.VaksinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaksinDb extends JpaRepository<VaksinModel, Long> {
    Optional<VaksinModel> findByIdVaksin(Long idVaksin);
    Optional<VaksinModel> findByJenisVaksin(String jenisVaksin);
}
