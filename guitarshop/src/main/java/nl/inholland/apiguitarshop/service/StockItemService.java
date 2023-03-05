package nl.inholland.apiguitarshop.service;

import nl.inholland.apiguitarshop.model.StockItem;
import nl.inholland.apiguitarshop.repository.StockItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class StockItemService {

    private final StockItemRepository repository;

    public StockItemService(StockItemRepository repository) {
        this.repository = repository;
    }

    public Iterable<StockItem> getItems() {
        return repository.findAll();
    }

    public Iterable<StockItem> getItemsByQuantity(int num) {
        return repository.findStockItemByQuantityLessThanEqual(num);
    }

    public double getStockValueByGuitarId(long id) {
        try {
            return repository.getStockValueByGuitarId(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Guitar not found");
        }
    }
}
