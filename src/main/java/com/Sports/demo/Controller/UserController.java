package com.Sports.demo.Controller;

import com.Sports.demo.Repo.Facilityrepo;
import com.Sports.demo.Repo.Requestrepo;
import com.Sports.demo.Repo.UserRepo;
import com.Sports.demo.models.Request;
import com.Sports.demo.models.SportsFacility;
import com.Sports.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("loggedInUser")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Facilityrepo facrepo;

    @Autowired
    private Requestrepo reqrepo;

    @GetMapping("/")
    public String getHomePage() {
        return "Home";
    }

    @PostMapping("/index")
    public String dummy() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getPassword() != null && existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("loggedInUser", existingUser);
            return "redirect:/display"; // Redirect to dashboard or any other page after successful login
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password");
            return "login"; // Return to the login page with an error message
        }
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        // Check if the email is already in use
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            // Email is already taken
            model.addAttribute("error", "Email is already taken. Please choose a different email.");
            return "login"; // Return to the registration page with an error message
        } else {
            // Save the user if email is not taken
            userRepo.save(user);
            model.addAttribute("message", "Registration successful. You will be redirected to the login page.");
            return "redirect:/login";
        }
    }

    @GetMapping("/display")
    public String showSportsList(Model model, @ModelAttribute("user") User user) {
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

    @PostMapping("/book")
    public String bookFacility(@RequestParam("id") Integer id, @ModelAttribute("user") User user, Model model) {
        Optional<SportsFacility> facilityOptional = facrepo.findById(id);
        if (facilityOptional.isPresent()) {
            SportsFacility facility = facilityOptional.get();
            model.addAttribute("facility", facility);
            model.addAttribute("user", user); // Pass the user object to the book page
            return "book"; // Return the name of the HTML page to display booking details
        } else {
            // Facility not found
            return "error"; // Redirect to an error page or handle the error accordingly
        }
    }

    @PostMapping("/requestSlot")
    public String requestSlot(@RequestParam("facilityId") Integer facilityId,
                              @RequestParam("slot") String slot,
                              @ModelAttribute("loggedInUser") User loggedInUser,
                              Model model) {
        // Retrieve facility object from the repository using the facility ID
        SportsFacility facility = facrepo.findById(facilityId).orElse(null);

        // Check if facility exists
        if (facility != null && loggedInUser != null) {
            // Create a new request
            Request request = new Request();

            // Set facility, user, slot, and status for the request
            request.setFacility(facility);
            request.setUser(loggedInUser);
            request.setSlot(slot);
            request.setStatus("pending");

            // Save the request
            reqrepo.save(request);

            // Redirect to some confirmation page or any other appropriate page
            return "redirect:/confirmation";
        } else {
            // Facility not found, handle accordingly
            return "error";
        }
    }

    @GetMapping("/confirmation")
    public String showBookingPage(Model model) {
        return "confirmation"; // Assuming you have a book.html template for displaying booking details
    }
}
