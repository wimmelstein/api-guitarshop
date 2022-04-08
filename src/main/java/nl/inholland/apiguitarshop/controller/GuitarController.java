package nl.inholland.apiguitarshop.controller;

import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.service.GuitarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/guitars", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuitarController {

    private GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    public ResponseEntity<List<Guitar>> getAllGuitars(@RequestParam(required = false) String brand,
                                                      @RequestParam(required = false) String model) {
        try {
            return ResponseEntity.ok().body(guitarService.getAllGuitars(brand, model));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addGuitar(@RequestBody Guitar guitar) {
        try {
            guitarService.addGuitar(guitar);
            return ResponseEntity.status(HttpStatus.CREATED).body(guitar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
