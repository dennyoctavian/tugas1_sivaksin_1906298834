package apap.tugas.sivaksin.service;
import apap.tugas.sivaksin.model.SuntikModel;
import apap.tugas.sivaksin.repository.SuntikDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SuntikServiceImpl implements SuntikService {
    @Autowired
    SuntikDb suntikDb;

    @Override
    public void addSuntik(SuntikModel suntik) {
        suntikDb.save(suntik);
    }

    // @Override
    // public SuntikModel getSuntikByBatchId(String BatchId){
    //     Optional<SuntikModel> suntik = suntikDb.findByBatchId(BatchId);
    //     if (suntik.isPresent()) {
    //         return suntik.get();
    //     }
    //     return null;
    // }

    // @Override
    // public String getStringBatchId(SuntikModel suntik){
    //     String gender = "";

    //     if (suntik.getPasien().getJenisKelamin() == 0){
    //         gender = "1";
    //     } else {
    //         gender = "2";
    //     }
    //     String kareakterAkhirNama = suntik.getPasien().getNamaPasien();
    //     String lastChar = kareakterAkhirNama.substring(kareakterAkhirNama.length() - 1).toUpperCase();
    //     String tempatLahir = suntik.getPasien().getTempatLahir();
    //     String tempatLahir2Char = tempatLahir.substring(0,2).toUpperCase();
    //     DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMM");
    //     DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
    //     String tanggalLahir = suntik.getPasien().getTanggalLahir().format(formatDate);
    //     String tahun = suntik.getPasien().getTanggalLahir().format(year);
    //     int tahunLahir = (int) Math.floor(Integer.parseInt(tahun)/10);
    //     Random random = new Random();
    //     char randomizedCharacter1 = (char) (random.nextInt(26) + 'a');
    //     char randomizedCharacter2 = (char) (random.nextInt(26) + 'a');
    //     String randomChar = Character.toString(randomizedCharacter1 )+ Character.toString(randomizedCharacter2);
    //     String UpRandomCharacter = randomChar.toUpperCase();
    //     String BatchId = gender + lastChar + tempatLahir2Char + tanggalLahir + Integer.toString(tahunLahir) + UpRandomCharacter;
    //     return BatchId;
    // }
}
