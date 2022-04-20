package nl.inholland.apiguitarshop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Brand(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    private Set<Guitar> guitars = new HashSet<>();

    public Brand() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Guitar> getGuitars() {
        return guitars;
    }

    public void setGuitars(Set<Guitar> guitars) {
        this.guitars = guitars;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Brand{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", guitars=").append(guitars);
        sb.append('}');
        return sb.toString();
    }
}

