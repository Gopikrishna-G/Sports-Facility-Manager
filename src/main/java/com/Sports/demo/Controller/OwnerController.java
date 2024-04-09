package com.Sports.demo.Controller;

import com.Sports.demo.Repo.Facilityrepo;
import com.Sports.demo.Repo.FacilityOwnerRepo;
import com.Sports.demo.models.SportsFacility;
import com.Sports.demo.models.FacilityOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
//
@Controller
public class OwnerController {
    @Autowired
    FacilityOwnerRepo userrepo;
    @Autowired
    Facilityrepo facrepo;

    @PostMapping("/Ownerlogin")
    public String login(@ModelAttribute("facilityOwner") FacilityOwner facilityOwner, Model model) {
        FacilityOwner existingUser = userrepo.findByEmail(facilityOwner.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(facilityOwner.getPassword())) {
            // Successful login
            return "/ownerdisplay"; // Redirect to dashboard or any other page after successful login
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password. Please try again."); // Adding a warning message
            return "ownerlogin"; // Return to the login page with an error message
        }
    }


    @GetMapping("/Ownerlogin")
    public String getRegPage(@ModelAttribute FacilityOwner facilityOwner) {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "ownerlogin";
    }
    @PostMapping("/OwnerRegistration")
    public String saveUser(@ModelAttribute("facilityOwner") FacilityOwner facilityOwner, Model model)
    {
        userrepo.save(facilityOwner);
        model.addAttribute("message","Registration successful \n You will be redirected to login page");
        return "ownerregistraion";
    }
////

//
}
