package com.Sports.demo.Controller;

import com.Sports.demo.Repo.*;
import com.Sports.demo.models.*;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//
@Controller
@SessionAttributes("loggedInOwner")
public class OwnerController {
    @Autowired
    UserRepo playerrepo;
    @Autowired
    FacilityOwnerRepo userrepo;
    @Autowired
    Facilityrepo facrepo;
    @Autowired
    ownerfacilityrepo f1repo;
    @Autowired
    Requestrepo rq1repo;
    @Autowired
    Facilityrepo sp;
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
        return "Owner_Home/home";
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
                            @ModelAttribute("sports") SportsFacility sports,
                            Model model){
        if(loggedInOwner != null){
            ownerFeatures.setOwner(loggedInOwner);
            f1repo.save(ownerFeatures);
            model.addAttribute("message","Registration successful \n You will be redirected to login page");
            sports.setId(ownerFeatures.getId());
            sports.setLocation(ownerFeatures.getLocation());
            sports.setCapacity(ownerFeatures.getCost());
            sports.setName(ownerFeatures.getGame());
            sp.save(sports);
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
//            FacilityOwner owner=userrepo.findByEmail(loggedInOwner.getEmail());
            model.addAttribute("loggedInOwner", loggedInOwner);
//            System.out.print("")
            return "Owner_Home/profile";
        }
        else{
            return "Owner_Home/home";
        }
    }
    @GetMapping("/showRequests")
    public String showRequests(@ModelAttribute("loggedInOwner") FacilityOwner loggedInOwner, Model model) {
        if (loggedInOwner != null) {
            // Find the owner_id associated with the logged-in owner
            int ownerId = loggedInOwner.getId();

            // Fetch ownerFeatures associated with the owner_id
            List<OwnerFeatures> ownerFeatures = f1repo.findByOwnerId(ownerId);

            // Create a list to hold the facility_ids belonging to the owner's facilities
            List<Integer> facilityIds = new ArrayList<>();

            // Extract facility_ids from ownerFeatures
            for (OwnerFeatures feature : ownerFeatures) {
                facilityIds.add(feature.getId());
            }

            // Fetch requests from the request table where facility_id is in the list of facilityIds
            List<Request> requests = rq1repo.findByFacilityIdIn(facilityIds);

            // Add requests to the model
            model.addAttribute("requests", requests);

            // Return the view where you want to display the requests
            return "Owner_Home/showreq";
        } else {
            // If the user is not logged in, redirect to the home page
            return "Owner_Home/home";
        }
    }
//
//
}
