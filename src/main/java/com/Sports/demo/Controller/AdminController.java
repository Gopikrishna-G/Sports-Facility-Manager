package com.Sports.demo.Controller;

import com.Sports.demo.Repo.AdminRepo;
import com.Sports.demo.Repo.Facilityrepo;
import com.Sports.demo.Repo.UserRepo;
import com.Sports.demo.models.Admin;
import com.Sports.demo.Repo.FacilityOwnerRepo;
import com.Sports.demo.models.SportsFacility;
import com.Sports.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRepo userrepo;

    @Autowired
    FacilityOwnerRepo facownrepo;

    @Autowired
    Facilityrepo facrepo;

    @GetMapping("/adminlogin")
    public String getHomePage() {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "adminlogin";
    }
    @PostMapping("/adminindex")
    public String dummy()
    {
        return "redirect:/adminlogin";
    }
    @PostMapping("/adminlogin")
    public String login(@ModelAttribute("admin") Admin admin, Model model) {
        Admin existingAdmin = adminRepo.findByEmail(admin.getEmail());
        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            // Successful login
            return "redirect:/admin"; // Redirect to dashboard or any other page after successful login
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password");
            return "adminlogin"; // Return to the login page with an error message
        }
    }

    @GetMapping("/admin")
    public String getRegPage(@ModelAttribute Admin admin) {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "admin";
    }

    @PostMapping("/admin")
    public String displayUsers(Model model) {
        List<User> users = userrepo.findAll();
        model.addAttribute("users", users);
        return "admin";
    }
}
