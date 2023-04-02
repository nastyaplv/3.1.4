package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.reposotiries.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcomePage() {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String pageForAuthenticatedUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(user);
        if (user.getRoles().contains((Role) roleRepository.getById(2))) {
            return "user_admin";
        } else {
            return "user";
        }
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model, Principal principal, @ModelAttribute("updateduser") User updateduser) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("principalUser", user);
        model.addAttribute("users", userService.getAllUsers());
        Collection<Role> roles = (Collection<Role>)roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "users";
    }

    @GetMapping("/admin/new")
    public String newPerson(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("principalUser", user);
        model.addAttribute("user", new User());
        Collection<Role> roles = (Collection<Role>)roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("admin/{id}")
    public String update(@ModelAttribute("updateduser") User updateduser, @PathVariable("id") int id) {
        userService.update(id, updateduser);
        return "redirect:/admin";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}