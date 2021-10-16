package apap.tugas.sivaksin.service;
import apap.tugas.sivaksin.model.PasienModel;
import java.util.List;

public interface PasienService {
    List<PasienModel> getPasienList();
    void addPasien(PasienModel pasien);
    PasienModel getPasienByIdPasien(Long idPasien);
    void updatePasien(PasienModel pasien);
    void removePasien(PasienModel pasien);
    // List<PasienModel> getPasienListByIdVaksinIdVaskes(Long idVaksin, Long idFaskes);
}
