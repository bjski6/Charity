package pl.coderslab.charity.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;

@Controller
public class DonationController {

    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;

    public DonationController(CategoryRepository categoryRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "user/addDonation")
    public String addDonationForm(Model model) {
        Donation donation = new Donation();
        List<Category> categories = categoryRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        model.addAttribute("donation", donation);

        return "user/addDonation";
    }

    @PostMapping("user/addDonation")
    public String addDonation(@ModelAttribute Donation donation) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name);
        donation.setUser(user);
        donationRepository.save(donation);
        return "redirect:addDonationConfirmation";
    }

    @GetMapping("user/addDonationConfirmation")
    public String addDonationConfirmation(Model model) {
        return "user/addDonationConfirmation";
    }


    @GetMapping("/donationList")
    public String donationList(Model model) {
        List<Donation> donations = donationRepository.findAll();
        model.addAttribute("donations", donations);
        return "donationList";
    }


}
