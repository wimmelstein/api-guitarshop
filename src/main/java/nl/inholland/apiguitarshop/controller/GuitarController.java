package nl.inholland.apiguitarshop.controller;

import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.dto.GuitarDTO;
import nl.inholland.apiguitarshop.service.GuitarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/guitars", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    public ResponseEntity<List<Guitar>> getAllGuitars() {
        try {
            return ResponseEntity.ok().body(guitarService.getAllGuitars());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createGuitar(@RequestBody GuitarDTO guitar) {
        try {
            Guitar newGuitar = guitarService.createGuitar(guitar);
            return ResponseEntity.status(201).body(newGuitar);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }
}
