package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.VaksinModel;
import java.util.List;

public interface VaksinService {
    VaksinModel getVaksinByIdVaksin(Long idVaksin);
    VaksinModel getVaksinBynamaVaksin(String jenisVaksin);
    List<VaksinModel> getVaksinList();
}
