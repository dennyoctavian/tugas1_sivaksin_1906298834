package apap.tugas.sivaksin.service;


import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.repository.VaksinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

@Service
@Transactional
public class VaksinServiceImpl implements VaksinService {
    @Autowired
    VaksinDb vaksinDb;

    @Override
    public VaksinModel getVaksinByIdVaksin(Long idVaksin){
        Optional<VaksinModel> vaksin =  vaksinDb.findByIdVaksin(idVaksin);
        if (vaksin.isPresent()) {
            return vaksin.get();
        }
        return null;
    }
}
