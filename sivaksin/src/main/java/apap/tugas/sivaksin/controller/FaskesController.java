package apap.tugas.sivaksin.controller;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.model.SuntikModel;
import apap.tugas.sivaksin.model.DokterModel;
import apap.tugas.sivaksin.service.FaskesService;
import apap.tugas.sivaksin.service.VaksinService;
import apap.tugas.sivaksin.service.PasienService;
import apap.tugas.sivaksin.service.DokterService;
import apap.tugas.sivaksin.service.SuntikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Controller
public class FaskesController {
    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("suntikServiceImpl")
    @Autowired
    private SuntikService suntikService;

    @GetMapping("/faskes")
    public String listFaskes(Model model){
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("vaksins", vaksins);
        return "faskes";
    }

    @GetMapping("/faskes/tambah")
    public String addFaskesForm(Model model){
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        model.addAttribute("faskes", new FaskesModel());
        model.addAttribute("vaksins", vaksins);
        return "form-add-faskes";
    }

    @PostMapping("/faskes/tambah")
    public String addFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ) {
        faskesService.addFaskes(faskes);
        model.addAttribute("nofaskes", faskes.getIdFaskes());
        return "add-faskes";
    }

    @GetMapping("/faskes/{faskesId}")
    public String detailFaskes(
            @PathVariable Long faskesId,
            Model model
    ) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(faskesId);
        String namaVaksin = faskes.getVaksinId().getJenisVaksin();
        List<PasienModel> patients = faskes.getListPasien();
        System.out.println(patients.size());
        model.addAttribute("faskes", faskes);
        model.addAttribute("vaksin", namaVaksin);
        model.addAttribute("patients", patients);
        return "detail-faskes";
    }

    @GetMapping("/faskes/ubah/{faskesId}")
    public String updateFaskesForm(
            @PathVariable Long faskesId,
            Model model
    ) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(faskesId);
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        VaksinModel vaksinNow = vaksinService.getVaksinByIdVaksin(faskes.getVaksinId().getIdVaksin());
        model.addAttribute("faskes", faskes);
        model.addAttribute("vaksins", vaksins);
        model.addAttribute("vaksinNow", vaksinNow);
        return "form-update-faskes";
    }

    @PostMapping("/faskes/ubah")
    public String updateFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ) {
        faskesService.updateFaskes(faskes);
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        return "update-faskes";
    }

    @GetMapping("/faskes/hapus/{faskesId}")
    public String deletePegawaiForm(@PathVariable Long faskesId, Model model) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(faskesId);
        List<PasienModel> listPasien = faskes.getListPasien();

        if (listPasien.size() != 0) {
            return "error-remove-pasien";
        } else {
            faskesService.removeFaskes(faskes);
        }
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        return "status-remove-faskes";
    }

    @RequestMapping("/cari/faskes")
    public String findFaskes(
        @RequestParam(value="jenisVaksin", required = true) String jenisVaksin,
        Model model
    ) {
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        VaksinModel vaksinNow = vaksinService.getVaksinBynamaVaksin(jenisVaksin);
        List<FaskesModel> listFaskes = faskesService.getFaskesByNamaVaksin(vaksinNow.getIdVaksin());
        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("vaksins", vaksins);
        return "faskes";
    }

    @GetMapping("/faskes/{id}/tambah/pasien")
    public String addPasienToFaskesForm(Model model, @PathVariable Long id){
        List<PasienModel> pasiens = pasienService.getPasienList();
        List<DokterModel> dokters = dokterService.getDokterList();
        model.addAttribute("suntik", new SuntikModel());
        model.addAttribute("dokters", dokters);
        model.addAttribute("pasiens", pasiens);
        model.addAttribute("id", id);
        return "form-add-pasien-faskes";
    }

    @PostMapping("/faskes/{id}/tambah/pasien")
    public String addPasienToFaskesSubmit(
            @PathVariable Long id,
            @ModelAttribute SuntikModel suntik,
            Model model
    ) {
        String gender = "";

        if (suntik.getPasien().getJenisKelamin() == 0){
            gender = "1";
        } else {
            gender = "2";
        }
        String kareakterAkhirNama = suntik.getPasien().getNamaPasien();
        String lastChar = kareakterAkhirNama.substring(kareakterAkhirNama.length() - 1).toUpperCase();
        String tempatLahir = suntik.getPasien().getTempatLahir();
        String tempatLahir2Char = tempatLahir.substring(0,2).toUpperCase();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMM");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        String tanggalLahir = suntik.getPasien().getTanggalLahir().format(formatDate);
        String tahun = suntik.getPasien().getTanggalLahir().format(year);
        int tahunLahir = (int) Math.floor(Integer.parseInt(tahun)/10);
        Random random = new Random();
        char randomizedCharacter1 = (char) (random.nextInt(26) + 'a');
        char randomizedCharacter2 = (char) (random.nextInt(26) + 'a');
        String randomChar = Character.toString(randomizedCharacter1 )+ Character.toString(randomizedCharacter2);
        String UpRandomCharacter = randomChar.toUpperCase();
        String BatchId = gender + lastChar + tempatLahir2Char + tanggalLahir + Integer.toString(tahunLahir) + UpRandomCharacter;
        
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(id);
    
        suntik.setBatchId(BatchId);
        suntik.setIdFaskes(id);
        suntikService.addSuntik(suntik);
        PasienModel pasien = pasienService.getPasienByIdPasien(suntik.getPasien().getIdPasien());
        pasienService.addPasien(pasien);
        faskesService.addFaskes(faskes);
        
        return "add-pasien-faskes";
    }
}
