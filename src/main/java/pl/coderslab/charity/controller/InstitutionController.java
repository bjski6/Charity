package pl.coderslab.charity.controller;

import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("institutionList")
    public String institutionList(Model model) {
        List<Institution> institutions = institutionRepository.findAll();

        model.addAttribute("institutions", institutions);
        return "institutionList";
    }

    @GetMapping("/admin/institution/add")
    public String addForm(Model model) {
        Institution institution = new Institution();

        model.addAttribute("institution", institution);
        return "admin/institution/add";
    }

    @PostMapping("admin/institution/add")
    public String addInstitution(@ModelAttribute Institution institution) {
        institutionRepository.save(institution);
        return "redirect:/institutionList";
    }

    @GetMapping("admin/institution/edit/{id}")
    public String editInstitutionForm(@PathVariable Long id, Model model) throws NotFoundException {
        Optional<Institution> institution = institutionRepository.findById(id);
        if(!institution.isPresent()){
            throw new NotFoundException("not found user institution= " + id);
        }
        model.addAttribute("institution", institution);
        return "admin/institution/edit";
    }

    @PostMapping("admin/institution/edit")
    public String editInstitution(@ModelAttribute Institution institution) {
        institutionRepository.save(institution);
        return "redirect:/institutionList";
    }

    @GetMapping("admin/institution/delete/{id}")
    public String deleteInstitution(@PathVariable Long id) throws NotFoundException {
        Optional<Institution> institution = institutionRepository.findById(id);
        if(!institution.isPresent()){
            throw new NotFoundException("not found user id= " + id);
        }
        institutionRepository.deleteById(id);

        return "redirect:/institutionList";
    }

}
