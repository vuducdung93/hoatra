/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.entity;

import java.io.Serializable;
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
@Table(name = "Product")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "price")
    private int price;
    
    @Column(name = "images")
    private String images;
    
    @ManyToOne
    @JoinColumn(name = "Categories_id")
    private Categories categories;
    
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<ToppingProduct> ToppingProducts;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<CartItem> cartItems;
    public Product() {
    }

    public Product(String name, int price, String images, Categories categories, List<ToppingProduct> ToppingProducts, List<CartItem> cartItems) {
        this.name = name;
        this.price = price;
        this.images = images;
        this.categories = categories;
        this.ToppingProducts = ToppingProducts;
        this.cartItems = cartItems;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ToppingProduct> getToppingProducts() {
        return ToppingProducts;
    }

    public void setToppingProducts(List<ToppingProduct> ToppingProducts) {
        this.ToppingProducts = ToppingProducts;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    
    
    
}
