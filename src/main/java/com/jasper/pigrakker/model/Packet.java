package com.jasper.pigrakker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

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
    private Double totalKG;
    private int sold;

    @Column(nullable = true, columnDefinition = "Integer default 1")
    private Integer imageID;

    @Column(nullable = true, columnDefinition = "Boolean default true")
    private Boolean active;

    @OneToMany(mappedBy = "packet")
    private Set<Order> orders = new HashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public Double getTotalKG() {
        return totalKG;
    }

    public void setTotalKG(Double totalKG) {
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