package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import java.util.List;

public interface FaskesService {
    List<FaskesModel> getFaskesList();
    void addFaskes(FaskesModel faskes);
    FaskesModel getFaskesByIdFaskes(Long idFaskes);
    List<FaskesModel> getFaskesByNamaVaksin(Long idVaksin);
    void updateFaskes(FaskesModel faskes);
    void removeFaskes(FaskesModel faskes);
}
