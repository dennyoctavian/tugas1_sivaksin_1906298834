package apap.tugas.sivaksin.service;
import apap.tugas.sivaksin.model.SuntikModel;

public interface SuntikService {
    void addSuntik(SuntikModel suntik);
    SuntikModel getSuntikByBatchId(String batchId);
    String getStringBatchId(SuntikModel suntik);
}
