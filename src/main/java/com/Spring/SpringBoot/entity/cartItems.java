package com.Spring.SpringBoot.entity;

import javax.persistence.*;

@Entity
@Table(name="cart_items")
public class cartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id",insertable = false,updatable = false)
    private long cart_id;

    @ManyToOne
    @JoinColumn(name="pr_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name="id")
    private User user;

    @Column(name="quantity")
    private int quantity;

    public long getCart_id() {
        return cart_id;
    }

    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
