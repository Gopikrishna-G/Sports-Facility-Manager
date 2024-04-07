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
    public String saveUser(@ModelAttribute("user") User user, Model model)
    {
        userrepo.save(user);
        model.addAttribute("message","Registration successful \n You will be redirected to login page");
        return "redirect:/display";
    }

    @GetMapping("/display")
    public String showRecipesList(Model model)
    {
        List<SportsFacility> sports=facrepo.findAll();
        model.addAttribute("sports",sports);
        return "display";
    }

}
