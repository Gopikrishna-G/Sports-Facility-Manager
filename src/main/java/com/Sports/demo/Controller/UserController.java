package com.Sports.demo.Controller;

import com.Sports.demo.Repo.UserRepo;
import com.Sports.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserRepo userrepo;
    @GetMapping("/")
    public String getHomePage() {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "Home";
    }
    @PostMapping("/login")
    public String handleSelection(@ModelAttribute("user") User user, Model model) {
        return "login";
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
        return "regis";
    }

}
