package nl.inholland.apiguitarshop.controller;

import lombok.extern.slf4j.Slf4j;
import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.dto.GuitarDTO;
import nl.inholland.apiguitarshop.service.GuitarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/guitars", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    public ResponseEntity<List<Guitar>> getAllGuitars() {
        UserDetails auth = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(auth.getUsername());
        try {
            return ResponseEntity.ok().body(guitarService.getAllGuitars());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createGuitar(@RequestBody GuitarDTO guitar) {
        try {
            Guitar newGuitar = guitarService.createGuitar(guitar);
            return ResponseEntity.status(201).body(newGuitar);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getCause().getMessage());
        }
    }

    @GetMapping(value = "/test", params = {"param1", "param2"})
    public ResponseEntity<Object> getTwoParams(@RequestParam(required = true) String param1
            , @RequestParam(required = true) String param2) {
        return ResponseEntity.ok(Collections.singletonMap("params", 2));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/test", params = {"param1"})
    public ResponseEntity<Object> getOnlyOneParam(@RequestParam(required = true) String param1) {
        return ResponseEntity.ok(Collections.singletonMap("params", 1));
    }
}
