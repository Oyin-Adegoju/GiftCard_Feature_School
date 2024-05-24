package com.example.gamewebshop.dto;

import com.example.gamewebshop.models.CustomUser;
import com.example.gamewebshop.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class PlacedOrderDTO {

    private long id;
    private String name;
    private String infix;
    private String last_name;
    private String zipcode;
    private int houseNumber;
    private String notes;
    private int totalProducts;
    private LocalDateTime orderDate;
    private CustomUserDTO user;

    private Set<ProductDTO> products = new HashSet<>();

    public PlacedOrderDTO() {
    }

    public PlacedOrderDTO(String name, String infix, String last_name, String zipcode, int houseNumber, String notes, CustomUserDTO user, Set<ProductDTO> products) {
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

    public CustomUserDTO getUser() {
        return user;
    }

    public void setUser(CustomUserDTO user) {
        this.user = user;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "PlacedOrderDTO{" +
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