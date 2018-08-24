/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.model;

/**
 *
 * @author vudung
 */
public class ToppingInfo {
    private int id;
    private String name;
    private int price;

    public ToppingInfo() {
    }

    public ToppingInfo(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
