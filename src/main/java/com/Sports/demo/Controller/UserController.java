package com.Sports.demo.Controller;

import com.Sports.demo.Repo.*;
import com.Sports.demo.models.*;
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

    @Autowired
    private bookrepo bookingRepo;

    @Autowired
    private reviewrepo reviewRepo;

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

            // Check if the user has any pending requests
            List<Request> pendingRequests = reqrepo.findByUserIdAndStatus(existingUser.getId(), "pending");
            if (!pendingRequests.isEmpty()) {
                return "redirect:/confirmation"; // Redirect to confirmation page
            }

            // Check if the user has any accepted requests
            List<Request> acceptedRequests = reqrepo.findByUserIdAndStatus(existingUser.getId(), "accepted");
            if (!acceptedRequests.isEmpty()) {
                return "redirect:/accepted"; // Redirect to accepted page
            }

            List<Request> rejectedRequests = reqrepo.findByUserIdAndStatus(existingUser.getId(), "rejected");
            if (!rejectedRequests.isEmpty()) {
                return "redirect:/rejected"; // Redirect to accepted page
            }

            // No requests found, display the default page
            return "redirect:/display"; // Redirect to display page
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password \n register if you don't have an account");
            return "login"; // Return to the login page with an error message
        }
    }

    @GetMapping("/rejected")
    public String handleRejection(Model model)
    {
        return "rejected";
    }

    @PostMapping("/reject")
    public String handle(Model model) {
        return "redirect:/display";
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
        if ( loggedInUser != null) {
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

    @GetMapping("/accepted")
    public String showAcceptPage(Model model){
        return "accepted";
    }

    @PostMapping("/reviewed")
    public String acceptRequest(@RequestParam("rating") int rating,
                                @RequestParam("comment") String comment,
                                @ModelAttribute("loggedInUser") User loggedInUser,
                                Model model) {
        // Retrieve the booking associated with the user
        Booking booking = bookingRepo.findByUserId(loggedInUser.getId());
//        System.out.println(booking.getUser().getId());
        // Check if booking exists and user is logged in
        if (booking != null && loggedInUser != null) {
            // Create a new review
            Review review = new Review();

            // Set the review details
            review.setBooking(booking);
            review.setRating(rating);
            review.setComment(comment);

            // Save the review
            // Assuming you have a repository for Review named "reviewRepo"
            reviewRepo.save(review);
//            model.addAttribute("booking",booking);

            // Redirect to some confirmation page or any other appropriate page
            return "redirect:/done";
        } else {
            // Booking not found or user not logged in, handle accordingly
            return "error";
        }
    }
    @GetMapping("/done")
    public String showDonePage(Model model){
        return "done";
    }

}
