package nl.inholland.apiguitarshop.controller;

import nl.inholland.apiguitarshop.service.StockItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/stocks")
public class StockItemController {

    private final StockItemService service;

    public StockItemController(StockItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity allStocks() {
        return ResponseEntity.ok().body(service.getItems());
    }

    @GetMapping(value = "", params = { "quantity"})
    public ResponseEntity stocksByQuantity(@RequestParam int quantity) {
        return ResponseEntity.ok(service.getItemsByQuantity(quantity));
    }

    @GetMapping(value = "{id}/value")
    public ResponseEntity getStockValueByGuitarId(@PathVariable long id) {
        try {
            return ResponseEntity
                    .ok(Collections.singletonMap("value", service.getStockValueByGuitarId(id)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(404)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}

