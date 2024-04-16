package com.Sports.demo.Controller;

import com.Sports.demo.Repo.Facilityrepo;
import com.Sports.demo.Repo.FacilityOwnerRepo;
import com.Sports.demo.Repo.ownerfacilityrepo;
import com.Sports.demo.models.OwnerFeatures;
import com.Sports.demo.models.SportsFacility;
import com.Sports.demo.models.FacilityOwner;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
//
@Controller
@SessionAttributes("loggedInOwner")
public class OwnerController {
    @Autowired
    FacilityOwnerRepo userrepo;
    @Autowired
    Facilityrepo facrepo;
    @Autowired
    ownerfacilityrepo f1repo;
    @PostMapping("/Ownerlogin")
    public String login(@ModelAttribute("facilityOwner") FacilityOwner facilityOwner, Model model) {
        FacilityOwner existingUser = userrepo.findByEmail(facilityOwner.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(facilityOwner.getPassword())) {
            // Successful login
            model.addAttribute("loggedInOwner", existingUser);
            return "redirect:/OwnerHome"; // Redirect to OwnerDisplay upon successful login
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
        return "/OwnerHome";
    }
    @GetMapping("/OwnerDisplay")
    public String getDisplayPage(@ModelAttribute("facilityOwner") FacilityOwner facilityOwner, Model model){
        return "Owner_Home/index";
    }
//    @GetMapping("/ShowGrounds")
//    public String showfac(@ModelAttribute("ownerFeatures") OwnerFeatures ownerFeatures, Model model){
//        return "";
//    }
//    @PostMapping("/addGround")
//    public String addGround(@ModelAttribute("ownerFeatures") OwnerFeatures ownerFeatures, Model model) {
//        // Save the ground information to the database
//        // Here you can use your Facilityrepo to save the ground information
//        // For example:
//        f1repo.save(ownerFeatures);
//        model.addAttribute("message","Registration successful \n You will be redirected to login page");
//        // After saving, you can redirect to the home page or any other page as needed
//        return "redirect:/OwnerDisplay";
//    }
////
    @PostMapping("/addGround")
    public String addGround(@ModelAttribute("ownerFeatures") OwnerFeatures ownerFeatures,
                            @ModelAttribute("loggedInOwner") FacilityOwner loggedInOwner,
                            Model model){
        if(loggedInOwner != null){
            ownerFeatures.setOwner(loggedInOwner);
            f1repo.save(ownerFeatures);
            model.addAttribute("message","Registration successful \n You will be redirected to login page");
//        // After saving, you can redirect to the home page or any other page as needed
            return "redirect:/OwnerDisplay";
        }
        else{

            return "redirect:/OwnerDisplay";
        }
    }
    @GetMapping("/OwnerHome")
    public String getHomePage(@ModelAttribute("facilityOwner") FacilityOwner facilityOwner, Model model){
        return "Owner_Home/home";
    }
//    @Autowired
//    private ownerfacilityrepo ownerfacilityRepo;

    @GetMapping("/showGround")
    public String showGround(@ModelAttribute("loggedInOwner") FacilityOwner loggedInOwner, Model model) {
        // Fetch ground information by owner ID
        List<OwnerFeatures> grounds = f1repo.findAllByOwnerId(loggedInOwner.getId());

        // Add ground information to the model
        model.addAttribute("grounds", grounds);

        // Return the view name where you want to display the data (e.g., "showGround.html")
        return "Owner_Home/ground_info";
    }
    @GetMapping("/OwnerProfile")
    public String profile(@ModelAttribute("loggedInOwner") FacilityOwner loggedInOwner, Model model){
        if(loggedInOwner!=null)
        {
            FacilityOwner owner=userrepo.findByEmail(loggedInOwner.getEmail());
            model.addAttribute("loggedInOwner", loggedInOwner);
//            System.out.print("")
            return "Owner_Home/profile";
        }
        else{
            return "Owner_Home/home";
        }
    }

//
//
}
