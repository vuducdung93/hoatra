/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.bean;

import java.util.List;

/**
 *
 * @author vudung
 */
public class cartItem {
    Product product;
    int quantity;
    boolean size;
    List listTopping;
    levelSugar sugar;
   

    public cartItem() {  
    }

    public cartItem(Product product,int quantity, boolean size, List listTopping, levelSugar sugar) {
        this.product=product;
        this.quantity = quantity;
        this.size = size;
        this.listTopping = listTopping;
        this.sugar = sugar;
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

    public List getListTopping() {
        return listTopping;
    }

    public void setListTopping(List listTopping) {
        this.listTopping = listTopping;
    }

    public levelSugar getSugar() {
        return sugar;
    }

    public void setSugar(levelSugar sugar) {
        this.sugar = sugar;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
