package nl.inholland.apiguitarshop.repository;

import nl.inholland.apiguitarshop.model.StockItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends CrudRepository<StockItem, Long> {

    Iterable<StockItem> findStockItemByQuantityLessThanEqual(int num);

    @Query("select s.quantity * g.price from StockItem s, Guitar g where s.guitar.id = g.id and s.guitar.id = :id")
    double getStockValueByGuitarId(long id);
    }
