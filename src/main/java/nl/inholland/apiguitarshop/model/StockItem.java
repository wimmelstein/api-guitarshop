package nl.inholland.apiguitarshop.model;

import javax.persistence.*;
import java.util.Random;

@Entity
@SequenceGenerator(name = "stock_seq", initialValue = 5_000_001)
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
    private long id;

    @OneToOne
    private Guitar guitar;

    private int quantity;

    public StockItem(Guitar guitar) {
        this.guitar = guitar;
        this.quantity = new Random().nextInt(50);
    }

    public StockItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Guitar getGuitar() {
        return guitar;
    }

    public void setGuitar(Guitar guitar) {
        this.guitar = guitar;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockItem{");
        sb.append("id=").append(id);
        sb.append(", guitar=").append(guitar);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
