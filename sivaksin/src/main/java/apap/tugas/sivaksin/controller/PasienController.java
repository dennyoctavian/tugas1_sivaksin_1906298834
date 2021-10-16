package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.model.SuntikModel;
import apap.tugas.sivaksin.model.DokterModel;
import apap.tugas.sivaksin.service.PasienService;
import apap.tugas.sivaksin.service.VaksinService;
import apap.tugas.sivaksin.service.FaskesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.ArrayList;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @GetMapping("/pasien")
    public String listPasien(Model model){
        List<PasienModel> listPasien = pasienService.getPasienList();
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        model.addAttribute("listPasien", listPasien);
        model.addAttribute("faskess", listFaskes);
        model.addAttribute("vaksins", vaksins);
        return "pasien";
    }

    @GetMapping("/pasien/tambah")
    public String addPasienForm(Model model){
        model.addAttribute("pasien", new PasienModel());
        return "form-add-pasien";
    }

    @PostMapping("/pasien/tambah")
    public String addPasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ) {
        pasienService.addPasien(pasien);
        model.addAttribute("noPasien", pasien.getIdPasien());
        return "add-pasien";
    }

    @GetMapping("/pasien/{pasienId}")
    public String detailPasien(
            @PathVariable Long pasienId,
            Model model
    ) {
        PasienModel pasien = pasienService.getPasienByIdPasien(pasienId);
        List<SuntikModel> listSuntik = pasien.getSuntik();
        //nama faskes
        List<String> listFaskes = new ArrayList<>();
        //nama vaksin
        List<String> listVaksins = new ArrayList<>();
        //nama dokter
        List<String> listNamaDokter = new ArrayList<>();
        //nip dokter
        List<String> listNipDokter = new ArrayList<>();
        // no telepon dokter
        List<String> listNoTelDokter = new ArrayList<>();

        for (SuntikModel suntik : listSuntik) {
            FaskesModel faskesvaksin = faskesService.getFaskesByIdFaskes(suntik.getIdFaskes());
            listFaskes.add(faskesvaksin.getNamaFaskes());
            listVaksins.add(faskesvaksin.getVaksinId().getJenisVaksin());
            listNamaDokter.add(suntik.getDokter().getNama_dokter());
            listNipDokter.add(suntik.getDokter().getNip());
            listNoTelDokter.add(suntik.getDokter().getNoTelepon());
        }
        model.addAttribute("pasien", pasien);
        model.addAttribute("listSuntik", listSuntik);
        model.addAttribute("listFaskes", listFaskes);
        model.addAttribute("listVaksins", listVaksins);
        model.addAttribute("listNamaDokter", listNamaDokter);
        model.addAttribute("listNipDokter", listNipDokter);
        model.addAttribute("listNoTelDokter", listNoTelDokter);
        return "detail-pasien";
    }

    @GetMapping("/pasien/ubah/{pasienId}")
    public String updatePasienForm(
            @PathVariable Long pasienId,
            Model model
    ) {
        PasienModel pasien = pasienService.getPasienByIdPasien(pasienId);
        model.addAttribute("pasien", pasien);
        return "form-update-pasien";
    }

    @PostMapping("/pasien/ubah")
    public String updatePasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ) {
        pasienService.updatePasien(pasien);
        model.addAttribute("idPasien", pasien.getIdPasien());
        return "update-pasien";
    }

    @GetMapping("/pasien/hapus/{pasienId}")
    public String deletePasienForm(@PathVariable Long pasienId, Model model) {
        PasienModel pasien = pasienService.getPasienByIdPasien(pasienId);
        pasienService.removePasien(pasien);
        return "status-remove-pasien";
    }

    @RequestMapping("/cari/faskeskedua")
    public String findFaskes(
        Model model
    ) {
        List<PasienModel> listPasien = pasienService.getPasienList();
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        model.addAttribute("listPasien", listPasien);
        model.addAttribute("faskess", listFaskes);
        model.addAttribute("vaksins", vaksins);
        return "pasien-search";
    }

    @RequestMapping("/cari/pasien")
    public String findPasien(
        @RequestParam(value="jenisVaksin", required = true) String jenisVaksin,
        @RequestParam(value="namaFaskes", required = true) String namaFaskes,
        Model model
    ) {
        List<PasienModel> listPasien = new ArrayList<PasienModel>();
        
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        List<VaksinModel> vaksins = vaksinService.getVaksinList();
        VaksinModel vaksinNow = vaksinService.getVaksinBynamaVaksin(jenisVaksin);
        List<FaskesModel> listFaskesWithVaksin = faskesService.getFaskesByNamaVaksin(vaksinNow.getIdVaksin());
        for (FaskesModel faskesModel : listFaskesWithVaksin){
            if(faskesModel.getNamaFaskes().equals(namaFaskes)){
                listPasien = faskesModel.getListPasien();
            }
        }
        model.addAttribute("listPasien", listPasien);
        model.addAttribute("faskess", listFaskes);
        model.addAttribute("vaksins", vaksins);
        return "pasien";
    }
}
