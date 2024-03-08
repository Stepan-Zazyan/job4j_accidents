package ru.job4j.accidents.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepository;
import ru.job4j.accidents.service.UserService;

@Controller
public class RegController {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final AuthorityRepository authorities;

    public RegController(PasswordEncoder encoder, UserService userService, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.userService = userService;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        userService.existsByUsername(user.getUsername());
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "/reg";
    }
}
