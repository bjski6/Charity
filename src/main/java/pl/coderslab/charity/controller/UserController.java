package pl.coderslab.charity.controller;


import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, DonationRepository donationRepository, InstitutionRepository institutionRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/user/userPage")
    public String userPage(Model model) {
        List<Institution> institutions = institutionRepository.findAll();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Long id = (userRepository.findByUsername(name)).getId();
        Long countUserDonation = donationRepository.sumUserQuantity(id);
        Long sumUserQuantity = donationRepository.countUserDonation(id);
        model.addAttribute("institutions", institutions);
        model.addAttribute("username", name);
        model.addAttribute("countUserDonation", countUserDonation);
        model.addAttribute("sumUserQuantity", sumUserQuantity);
        return "user/userPage";
    }


    @GetMapping("/admin/adminPage")
    public String adminPage(Model model) {
        return "admin/adminPage";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (!(user.getPassword().equals(user.getPasswordCheck()))) {
            return "/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordCheck(passwordEncoder.encode(user.getPasswordCheck()));
        user.setRole(roleRepository.findById(1L).get());
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/check")
    public String check() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String userRole = userRepository.findByUsername(name).getRole().getName();
        if (userRole.equals("ROLE_ADMIN")) {
            return "redirect:admin/adminPage";
        }
        return "redirect:user/userPage";
    }


    @GetMapping("admin/users/userList")
    public String userList(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "admin/users/userList";
    }


    @GetMapping("admin/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("not found user id= " + id);
        }
        model.addAttribute("user", user);
        return "admin/users/edit";

    }

    //utworzyc encje role
    @PostMapping("admin/users/edit")
    public String editUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordCheck(user.getPassword());
        userRepository.save(user);
        return "redirect:userList";
    }

    @GetMapping("admin/users/upgrade/{id}")
    public String upgradeUser(@PathVariable Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("not found user id= " + id);
        }
        Long idRole= user.get().getRole().getId();
        Optional<Role> role = roleRepository.findById(idRole+1);
        user.get().setRole(role.get());
        userRepository.save(user.get());
        return "redirect:/admin/users/userList";
    }

    @GetMapping("admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("not found user id= " + id);
        }
        userRepository.delete(user.get());

        return "redirect:/admin/users/userList";
    }


    @GetMapping("admin/users/add")
    public String addUserForm(Model model) {
        User user = new User();
        List<Role> roles=roleRepository.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("user", user);
        return "admin/users/add";
    }

    @PostMapping("admin/users/add")
    public String addUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordCheck(user.getPassword());
        userRepository.save(user);
        return "redirect:userList";
    }


}


