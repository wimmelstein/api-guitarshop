package nl.inholland.apiguitarshop.controller;

import nl.inholland.apiguitarshop.model.Guitar;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/other", produces = MediaType.APPLICATION_JSON_VALUE)
public class OtherController {

    private List<Guitar> guitars;

    public OtherController(List<Guitar> guitars) {
        this.guitars = guitars;
    }

    @GetMapping
    public ResponseEntity getAllGuitars() {
        return ResponseEntity.ok().body(guitars);
    }
}
