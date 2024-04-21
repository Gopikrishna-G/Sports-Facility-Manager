package com.Sports.demo.Controller;

import com.Sports.demo.Repo.*;
import com.Sports.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class StaffController {
    @Autowired
    UserRepo userrepo;

    @Autowired
    StaffRepo staffRepo;

    @Autowired
    FacilityOwnerRepo facownrepo;

    @Autowired
    Facilityrepo facrepo;

    @Autowired
    BookingRepo bookingRepo;

    @GetMapping("/stafflogin")
    public String getHomePage() {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "stafflogin";
    }
    @PostMapping("/staffindex")
    public String dummy()
    {
        return "redirect:/stafflogin";
    }
    @PostMapping("/stafflogin")
    public String login(@ModelAttribute("staff") Staff staff, Model model) {
        Staff existingStaff = staffRepo.findByEmail(staff.getEmail());
        if (existingStaff != null && existingStaff.getPassword().equals(staff.getPassword())) {
            // Successful login
            return "redirect:/staff"; // Redirect to dashboard or any other page after successful login
        } else {
            // Failed login
            model.addAttribute("error", "Invalid email or password");
            return "stafflogin"; // Return to the login page with an error message
        }
    }

    @GetMapping("/staff")
    public String getStaffPage(Model model) {
        List<User> users = userrepo.findAll();
        model.addAttribute("users", users);

        List<Booking> bookings = bookingRepo.findAll();
        model.addAttribute("bookings", bookings);

        List<SportsFacility> facilities = facrepo.findAll();
        model.addAttribute("facilities", facilities);

        return "staff";
    }
}
