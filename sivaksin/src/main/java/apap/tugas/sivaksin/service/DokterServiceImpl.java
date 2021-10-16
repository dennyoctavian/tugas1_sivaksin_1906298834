package apap.tugas.sivaksin.service;
import apap.tugas.sivaksin.model.DokterModel;
import apap.tugas.sivaksin.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DokterServiceImpl implements DokterService {
    @Autowired
    DokterDb dokterDb;
    
    @Override
    public List<DokterModel> getDokterList() {
        return dokterDb.findAll();
    }
}
