package com.example.gamewebshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PlacedOrder {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private String infix;
    @Column(nullable = true)
    private String last_name;
    @Column(nullable = true)
    private String zipcode;
    @Column(nullable = true)
    private int houseNumber;
    @Column(nullable = true)
    private String notes;
    @Column(nullable = true)
    private int totalProducts;
    @Column(nullable = true)
    private LocalDateTime orderDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private CustomUser user;

    @ManyToMany
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    private Double totalAmount;
    private Double paidAmountByGiftCard;
    @Transient
    private List<Long> giftCardIds = new ArrayList<>();

    public PlacedOrder() {
    }

    public PlacedOrder(String name, String infix, String last_name, String zipcode, int houseNumber, String notes, CustomUser user, Set<Product> products) {
        this.name = name;
        this.infix = infix;
        this.last_name = last_name;
        this.zipcode = zipcode;
        this.houseNumber = houseNumber;
        this.notes = notes;
        this.user = user;
        this.products = products;
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

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


    public Number getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public List<Long> getGiftCardIds() {
        return giftCardIds;
    }

    public void setGiftCardIds(List<Long> giftCardIds) {
        this.giftCardIds = giftCardIds;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPaidAmountByGiftCard() {
        return paidAmountByGiftCard;
    }

    public void setPaidAmountByGiftCard(Double paidAmountByGiftCard) {
        this.paidAmountByGiftCard = paidAmountByGiftCard;
    }

    @Override
    public String toString() {
        return "PlacedOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infix='" + infix + '\'' +
                ", last_name='" + last_name + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", houseNumber=" + houseNumber +
                ", notes='" + notes + '\'' +
                ", totalProducts=" + totalProducts +
                ", orderDate=" + orderDate +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}