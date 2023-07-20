package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/moderator")
public class ModeratorsController {

    private final UserService userService;
    @Autowired
    public ModeratorsController(UserService userService) {
        this.userService = userService;
    }
    public ResponseEntity<?> getAllUsersRolesModerator() {
        try{
            List<User> list = userService.getAll();
            ResponseEntity<?> response = new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
