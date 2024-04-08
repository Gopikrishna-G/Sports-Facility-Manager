package com.Sports.demo.Controller;

import com.Sports.demo.Repo.Facilityrepo;
import com.Sports.demo.Repo.UserRepo;
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
public class UserController {
    @Autowired
    UserRepo userrepo;
    @Autowired
    Facilityrepo facrepo;

    @GetMapping("/")
    public String getHomePage() {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "Home";
    }
@PostMapping("/index")
public String dummy()
{
    return "redirect:/login";
}
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        User existingUser = userrepo.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // Successful login
            return "redirect:/display"; // Redirect to dashboard or any other page after successful login
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password");
            return "login"; // Return to the login page with an error message
        }
    }

    @GetMapping("/login")
    public String getRegPage(@ModelAttribute User user) {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "login";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        // Check if the email is already in use
        User existingUser = userrepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            // Email is already taken
            model.addAttribute("error", "Email is already taken. Please choose a different email.");
            return "login"; // Return to the registration page with an error message
        } else {
            // Save the user if email is not taken
            userrepo.save(user);
            model.addAttribute("message", "Registration successful. You will be redirected to the login page.");
            return "redirect:/display";
        }
    }

    @GetMapping("/display")
    public String showSportsList(Model model) {
        List<SportsFacility> sports = facrepo.findAll();
        List<String> uniqueLocations = facrepo.findDistinctLocations();
        model.addAttribute("locations", uniqueLocations);
        model.addAttribute("sports", sports);
        return "display";
    }


    @PostMapping("/filtered")
    public String showSportsList(Model model, @RequestParam(required = false) String location) {
        List<SportsFacility> sports;
        List<String> uniqueLocations = facrepo.findDistinctLocations();
        if (location != null && !location.isEmpty()) {
            sports = facrepo.findByLocation(location);
        } else {
            sports = facrepo.findAll();
        }
        model.addAttribute("locations", uniqueLocations);
        model.addAttribute("selectedLocation", location); // Add selected location for highlighting in dropdown
        model.addAttribute("sports", sports);
        return "display";
    }

    @PostMapping("/sorted")
    public String sortSportsListByCapacity(Model model) {
        List<SportsFacility> sports = facrepo.findAll();

        // Sort the sports facilities by capacity
        sports.sort(Comparator.comparingInt(SportsFacility::getCapacity).reversed());

        List<String> uniqueLocations = facrepo.findDistinctLocations();
        model.addAttribute("locations", uniqueLocations);
        model.addAttribute("selectedLocation", null); // Reset selected location
        model.addAttribute("sports", sports);
        return "display";
    }

}
