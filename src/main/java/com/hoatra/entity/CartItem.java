/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vudung
 */
@Entity
@Table(name = "CartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "size")
    private boolean size;
    
    @Column(name = "mucduong")
    private int mucduong;
    
    @Column(name = "listTopping")
    private String listTopping;
    
    @ManyToOne
    @JoinColumn(name = "Cart_id")
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name = "Product_Id")
    private Product product;
    
    

    public CartItem() {
    }

    public CartItem(int id, int quantity, boolean size, int mucduong, Cart cart, Product product,String listTopping) {
        this.id = id;
        this.quantity = quantity;
        this.size = size;
        this.mucduong = mucduong;
        this.listTopping=listTopping;
        this.cart = cart;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSize() {
        return size;
    }

    public void setSize(boolean size) {
        this.size = size;
    }

    public int getMucduong() {
        return mucduong;
    }

    public void setMucduong(int mucduong) {
        this.mucduong = mucduong;
    }

    public String getListTopping() {
        return listTopping;
    }

    public void setListTopping(String listTopping) {
        this.listTopping = listTopping;
    }
    
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
