package com.jasper.pigrakker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="packets")
public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Pakketnaam mag niet leeg zijn.")
    @Column(nullable = false, unique = true)
    private String packetname;

    @Lob
    @Column(nullable = false)
    private String contains;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int totalKG;
    private int sold;

    public long getId() {
        return id;
    }

    public int getTotalKG() {
        return totalKG;
    }

    public void setTotalKG(int totalKG) {
        this.totalKG = totalKG;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPacketname() {
        return packetname;
    }

    public void setPacketname(String packetname) {
        this.packetname = packetname;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}