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
import java.util.ArrayList;

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
        model.addAttribute("namafaskes", faskes.getNamaFaskes());
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
        model.addAttribute("faskes", faskes);
        model.addAttribute("vaksin", namaVaksin);
        model.addAttribute("efikasi", faskes.getVaksinId().getEfikasi());
        model.addAttribute("negara", faskes.getVaksinId().getAsalNegara());
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
            return "error-remove-faskes";
        } else {
            faskesService.removeFaskes(faskes);
        }
        model.addAttribute("idFaskes", faskes.getIdFaskes());
        return "status-remove-faskes";
    }

    @RequestMapping("/cari")
    public String cariFeature() {
        return "cari";
    }

    @RequestMapping("/cari/faskespertama")
    public String findFaskes(
        Model model
    ) {
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("vaksins", vaksins);
        return "faskes-search";
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
        return "faskes-search";
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
    
        String batchId = suntikService.getStringBatchId(suntik);
        SuntikModel suntikModel = suntikService.getSuntikByBatchId(batchId);
        boolean status = true;
        while (status) {
            if (suntikModel != null) {
                batchId = suntikService.getStringBatchId(suntik);
            } else {
                status = false;
            }
        }
        
        suntik.setBatchId(batchId);
        suntik.setIdFaskes(id);
        suntikService.addSuntik(suntik);
        
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(id);
        PasienModel pasien = pasienService.getPasienByIdPasien(suntik.getPasien().getIdPasien());
        List<PasienModel> pasiens = faskes.getListPasien();
        List<FaskesModel> faskess = pasien.getListFaskes();
        pasiens.add(pasien);
        faskess.add(faskes);
        pasienService.addPasien(pasien);
        faskesService.addFaskes(faskes);
        pasien.setListFaskes(faskess);
        faskes.setListPasien(pasiens);

        return "add-pasien-faskes";
    }

    @GetMapping("/cari/jumlah-pasien/bulan-ini")
    public String CariJumlahPasienFaskes(Model model){
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        List<Integer> jumlahPasien = new ArrayList<Integer>();
        for (FaskesModel f : listFaskes){
            jumlahPasien.add(f.getListPasien().size());
        }
        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("jumlahPasien", jumlahPasien);
        return "jumlah-pasien-faskes";
    }
}
