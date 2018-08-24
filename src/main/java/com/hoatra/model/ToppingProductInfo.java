/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.model;

import java.util.List;

/**
 *
 * @author vudung
 */
public class ToppingProductInfo {
    private int productId;
    private String name;
    private int price;
    private String images;
    private List<ToppingInfo> toppings;

    public ToppingProductInfo() {
    }

    public ToppingProductInfo(int productId, String name, int price, String images, List<ToppingInfo> toppings) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.images = images;
        this.toppings = toppings;
    }
    public ToppingProductInfo(int productId, String name, int price, String images) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.images = images;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ToppingInfo> getToppings() {
        return toppings;
    }

    public void setToppings(List<ToppingInfo> toppings) {
        this.toppings = toppings;
    }
    
    
    
    
    
    
}
