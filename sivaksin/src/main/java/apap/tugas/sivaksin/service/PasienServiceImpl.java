package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasienServiceImpl implements PasienService {
    @Autowired
    PasienDb pasienDb;
    
    @Override
    public List<PasienModel> getPasienList() {
        return pasienDb.findAll();
    }

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public PasienModel getPasienByIdPasien(Long idPasien){
        Optional<PasienModel> pasien = pasienDb.findByIdPasien(idPasien);
        if (pasien.isPresent()) {
            return pasien.get();
        }
        return null;
    }

    // @Override
    // public List<PasienModel> getPasienListByIdVaksinIdVaskes(Long idVaksin, Long idFaskes){
    //     List<PasienModel> pasiens = pasienDb.findAll();
    //     for (PasienModel x : pasiens){
    //         if ()
    //     }
    // }

    @Override
    public void updatePasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public void removePasien(PasienModel pasien) {
        pasienDb.delete(pasien);
    }
}
