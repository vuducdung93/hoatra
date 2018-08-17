/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.bean;



/**
 *
 * @author vudung
 */

public class Product {
    int id;
    String name;
    int price;
    String image;
    int catogeryId;
    public Product(int id,String name, int price, String image,int catogeryId) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.image=image;
        this.catogeryId=catogeryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }

    

    
    public void setImage(String image) {
        this.image = image;
    }

    public Product() {
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

    public int getCatogeryId() {
        return catogeryId;
    }

    public void setCatogeryId(int catogeryId) {
        this.catogeryId = catogeryId;
    }
    
}
