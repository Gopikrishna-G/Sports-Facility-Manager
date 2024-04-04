package com.Sports.demo.Controller;

import com.Sports.demo.Repo.UserRepo;
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
    @GetMapping("/")
    public String getRegPage(@ModelAttribute User user) {
//        List<User> users= userrepo.findAll();
//        model.addAttribute("user", users);
        return "index";
    }
    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") User user, Model model)
    {
        userrepo.save(user);
        model.addAttribute("message","Registration successful");
        return "regis";
    }

}
