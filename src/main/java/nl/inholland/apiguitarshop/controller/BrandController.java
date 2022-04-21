package nl.inholland.apiguitarshop.controller;

import nl.inholland.apiguitarshop.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brands")
public class BrandController {


    private BrandService service;


    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAllBrands() {
        return ResponseEntity.ok().body(service.getAllBrands());
    }
}
