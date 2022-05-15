package nl.inholland.apiguitarshop.controller;

import nl.inholland.apiguitarshop.model.dto.LoginDTO;
import nl.inholland.apiguitarshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String login(@RequestBody LoginDTO login) {
        return userService.login(login.getUsername(), login.getPassword());
    }
}
