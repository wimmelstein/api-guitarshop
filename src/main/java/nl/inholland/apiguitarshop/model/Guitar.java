package nl.inholland.apiguitarshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity

public class Guitar {

    @Id
    @SequenceGenerator(name = "guitar_seq", initialValue = 1_000_001)
    @GeneratedValue(generator = "guitar_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Brand brand;
    private String model;
    private double price;


    public Guitar() {
    }

    public Guitar(Brand brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Guitar{");
        sb.append("brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", price=").append(price);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
