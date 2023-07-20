package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;

import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

//    @GetMapping() //метод предыдущей задачи
//    public String userInfo(Principal principal, Model model) {
//        model.addAttribute("user", userService.getByName(principal.getName()));
//        return "show2";
//    }
//    @GetMapping()
//    public List<User> showUsers() {
//        List<User> allUsers = userService.getAll();
//        return allUsers;
//    }

    @GetMapping
    public ResponseEntity<User> getUserInfo(Principal principal) {
        User user = userService.getByName(principal.getName());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

//     @GetMapping("/{id}")
//    public String show(@PathVariable("id")Long id, Model model) {
//        model.addAttribute("user", userService.getById(id));
//
//        return "show";
//    }


}
