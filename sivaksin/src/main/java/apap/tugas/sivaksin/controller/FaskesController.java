package apap.tugas.sivaksin.controller;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.service.FaskesService;
import apap.tugas.sivaksin.service.VaksinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.ArrayList;

@Controller
public class FaskesController {
    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Qualifier("vaksinServiceImpl")
    @Autowired
    private VaksinService vaksinService;

    @GetMapping("/faskes")
    public String listFaskes(Model model){
        List<FaskesModel> listFaskes = faskesService.getFaskesList();
        model.addAttribute("listFaskes", listFaskes);
        return "faskes";
    }

    @GetMapping("/faskes/tambah")
    public String addCabangForm(Model model){
        model.addAttribute("faskes", new FaskesModel());
        return "form-add-faskes";
    }

    @PostMapping("/faskes/tambah")
    public String addCabangSubmit(
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
        String namaVaksin = faskes.getNamaVaksin().getJenisVaksin();
        System.out.println(namaVaksin);
        List<PasienModel> patients = faskes.getListPasien();
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
        model.addAttribute("faskes", faskes);
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
        
        return "status-remove-faskes";
    }
}
