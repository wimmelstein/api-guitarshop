package nl.inholland.apiguitarshop.controller;

import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.service.GuitarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/guitars", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Guitar>> getAllGuitars() {
        try {
            return ResponseEntity.ok().body(guitarService.getAllGuitars());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity addGuitar(@RequestBody Guitar guitar) {
//        try {
//            guitarService.addGuitar(guitar);
//            return ResponseEntity.status(HttpStatus.CREATED).body(guitar);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
//        }
//    }
}
