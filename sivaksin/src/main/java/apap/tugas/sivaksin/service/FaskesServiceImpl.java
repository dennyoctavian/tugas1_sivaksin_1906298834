package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.repository.FaskesDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


@Service
@Transactional
public class FaskesServiceImpl implements FaskesService {
    @Autowired
    FaskesDb faskesDb;
    
    @Override
    public List<FaskesModel> getFaskesList() {
        return faskesDb.findAll();
    }

    @Override
    public void addFaskes(FaskesModel faskes) {
        faskesDb.save(faskes);
    }

    @Override
    public FaskesModel getFaskesByIdFaskes(Long idFaskes){
        Optional<FaskesModel> faskes = faskesDb.findByIdFaskes(idFaskes);
        if (faskes.isPresent()) {
            return faskes.get();
        }
        return null;
    }

    @Override
    public List<FaskesModel> getFaskesByNamaVaksin(Long idVaksin){
        List<FaskesModel> faskes = faskesDb.findAll();
        List<FaskesModel> new_faskes = new ArrayList<FaskesModel>();
        
        for (FaskesModel x : faskes) {
            if (x.getVaksinId().getIdVaksin() == idVaksin) {
                new_faskes.add(x);
            }
        }
        return new_faskes;
    }

    @Override
    public void updateFaskes(FaskesModel faskes) {
        faskesDb.save(faskes);
    }

    @Override
    public void removeFaskes(FaskesModel faskes) {
        faskesDb.delete(faskes);
    }
    
}