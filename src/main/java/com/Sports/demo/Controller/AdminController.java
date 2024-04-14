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
public class AdminController {
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRepo userrepo;

    @Autowired
    FacilityOwnerRepo facownrepo;

    @Autowired
    Facilityrepo facrepo;

    @Autowired
    StaffRepo staffrepo;

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
    public String getRegPage(Model model) {
        List<Admin> admins = adminRepo.findAll();
        model.addAttribute("admins", admins);

        List<User> users = userrepo.findAll();
        model.addAttribute("users", users);

        List<Staff> staffs = staffrepo.findAll();
        model.addAttribute("staffs", staffs);

        List<FacilityOwner> owners = facownrepo.findAll();
        model.addAttribute("owners", owners);

        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userrepo.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int userId) {
        userrepo.deleteById(userId);
        return "redirect:/admin";
    }

    @PostMapping("/modifyUser")
    public String modifyUser(@ModelAttribute("user") User user) {
        User existingUser = userrepo.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoneNo(user.getPhoneNo());
            existingUser.setCity(user.getCity());
            userrepo.save(existingUser);
        }
        return "redirect:/admin";
    }

    @PostMapping("/addOwner")
    public String addOwner(@ModelAttribute("owner") FacilityOwner owner) {
        facownrepo.save(owner);
        return "redirect:/admin";
    }

    @PostMapping("/deleteOwner")
    public String deleteOwner(@RequestParam("ownerId") int ownerId) {
        facownrepo.deleteById(ownerId);
        return "redirect:/admin";
    }

    @PostMapping("/modifyOwner")
    public String modifyOwner(@ModelAttribute("owner") FacilityOwner owner) {
        FacilityOwner existingOwner = facownrepo.findById(owner.getId()).orElse(null);
        if (existingOwner != null) {
            existingOwner.setUsername(owner.getUsername());
            existingOwner.setFullname(owner.getFullname());
            existingOwner.setEmail(owner.getEmail());
            existingOwner.setPassword(owner.getPassword());
            existingOwner.setPhoneno(owner.getPhoneno());
            existingOwner.setCity(owner.getCity());
            existingOwner.setNumoffacility(owner.getNumoffacility());
            facownrepo.save(existingOwner);
        }
        return "redirect:/admin";
    }

    @PostMapping("/addStaff")
    public String addStaff(@ModelAttribute("staff") Staff staff) {
        staffrepo.save(staff);
        return "redirect:/admin";
    }

    @PostMapping("/deleteStaff")
    public String deleteStaff(@RequestParam("staffId") int staffId) {
        staffrepo.deleteById(staffId);
        return "redirect:/admin";
    }

    @PostMapping("/modifyStaff")
    public String modifyStaff(@ModelAttribute("staff") Staff staff) {
        Staff existingStaff = staffrepo.findById(staff.getId()).orElse(null);
        if (existingStaff != null) {
            existingStaff.setUsername(staff.getUsername());
            existingStaff.setFullname(staff.getFullname());
            existingStaff.setEmail(staff.getEmail());
            existingStaff.setPassword(staff.getPassword());
            existingStaff.setPhoneno(staff.getPhoneno());
            existingStaff.setCity(staff.getCity());
            existingStaff.setFacilityname(staff.getFacilityname());
            staffrepo.save(existingStaff);
        }
        return "redirect:/admin";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(@ModelAttribute("admin") Admin admin) {
        adminRepo.save(admin);
        return "redirect:/admin";
    }

    @PostMapping("/deleteAdmin")
    public String deleteAdmin(@RequestParam("adminId") int adminId) {
        adminRepo.deleteById(adminId);
        return "redirect:/admin";
    }

    @PostMapping("/modifyAdmin")
    public String modifyAdmin(@ModelAttribute("admin") Admin admin) {
        Admin existingAdmin = adminRepo.findById(admin.getId()).orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setUsername(admin.getUsername());
            existingAdmin.setFullname(admin.getFullname());
            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setPassword(admin.getPassword());
            existingAdmin.setPhoneno(admin.getPhoneno());
            adminRepo.save(existingAdmin);
        }
        return "redirect:/admin";
    }
}
