package pl.coderslab.charity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions = institutionRepository.findAll();
        Integer sumQuantity = donationRepository.sumQuantity();
        Integer countDonation = donationRepository.countDonation();
        model.addAttribute("institutions", institutions);
        model.addAttribute("sumQuantity", sumQuantity);
        model.addAttribute("countDonation", countDonation);
        /*if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return "redirect:/check";
        }*/
        return "index";
    }
}
