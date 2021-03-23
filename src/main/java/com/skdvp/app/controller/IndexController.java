package com.skdvp.app.controller;

import com.skdvp.app.service.RoleServiceImpl;
import com.skdvp.app.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public IndexController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/index")
    public String index(Model model, Principal principal) {
        model.addAttribute("autUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "index";
    }

    @GetMapping
    public String redirectToIndexPage() {
        return "redirect:/index";
    }
}
