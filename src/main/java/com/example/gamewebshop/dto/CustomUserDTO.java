package com.example.gamewebshop.dto;

import com.example.gamewebshop.models.PlacedOrder;

import java.util.Set;

public class CustomUserDTO {
    private Long id;

    private String name;
    private String infix;
    private String lastName;
    private String email;
    private String password;
    private Set<PlacedOrderDTO> placedOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PlacedOrderDTO> getPlacedOrders() {
        return placedOrders;
    }

    public void setPlacedOrders(Set<PlacedOrderDTO> placedOrders) {
        this.placedOrders = placedOrders;
    }
}
