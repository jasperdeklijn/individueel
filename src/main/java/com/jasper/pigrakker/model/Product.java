package com.jasper.pigrakker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Productnaam mag niet leeg zijn.")
    @Column(nullable = false, unique = true)
    private String productname;

    @Lob
    private String description;

    private int totalKG;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalKG() {
        return totalKG;
    }

    public void setTotalKG(int totalKG) {
        this.totalKG = totalKG;
    }
}
