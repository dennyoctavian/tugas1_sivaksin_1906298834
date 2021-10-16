package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long> {
    Optional<PasienModel> findByIdPasien(Long idPasien);
}

