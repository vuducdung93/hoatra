/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.model;

import com.hoatra.entity.Categories;
import java.util.List;

/**
 *
 * @author vudung
 */
public class ProductInfo {
    private int id;
    private String name;
    private int price;
    private String images;
    private int cId;
    private String cNa;

    public ProductInfo() {
    }

    public ProductInfo(int id, String name, int price, String images,int cId,String cNa) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.cId=cId;
        this.cNa=cNa;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcNa() {
        return cNa;
    }

    public void setcNa(String cNa) {
        this.cNa = cNa;
    }

    

    
        
}
