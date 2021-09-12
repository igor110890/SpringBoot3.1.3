package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import com.example.demo.services.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private final UserService userServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping()
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", user);
        model.addAttribute("users", userServiceImpl.findAll());
        return "all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        model.addAttribute("users", userServiceImpl.findById(id));

        return "user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", userServiceImpl.roles());
        return "newUsers";
    }

    @PostMapping()
    public String create(@RequestParam(value = "box", defaultValue = "ROLE_USER") String[] list, @ModelAttribute User user) {
        Set<Role> setRoleController = new HashSet<>();
        for (String role : list) {
            setRoleController.add(userServiceImpl.findByRole(role));
        }
        user.setRoles(setRoleController);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}/update")
    public String update(@RequestParam(value = "box", defaultValue = "ROLE_USER") List<String> list, @ModelAttribute User user) {
        Set<Role> setRoleController = new HashSet<>();
        for (String role : list) {
            setRoleController.add(userServiceImpl.findByRole(role));
        }
        user.setRoles(setRoleController);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }
}