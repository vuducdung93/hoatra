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
    List<Topping> listTopping;
    levelSugar sugar;
    int pricecart;

    public cartItem() {  
    }

    public cartItem(Product product,int quantity, boolean size, List<Topping> listTopping, levelSugar sugar) {
        this.product=product;
        this.quantity = quantity;
        this.size = size;
        this.listTopping = listTopping;
        this.sugar = sugar;
        if(size==true){
            this.pricecart=product.getPrice()+8000;
            listTopping.forEach((t) -> {
                this.pricecart+=t.getPrice();
            });
            
        }else{
            this.pricecart=product.getPrice();
            listTopping.forEach((t) -> {
                this.pricecart+=t.getPrice();
            });
        }
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

    public List<Topping> getListTopping() {
        return listTopping;
    }

    public void setListTopping(List<Topping> listTopping) {
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

    public int getPricecart() {
        return pricecart;
    }

    public void setPricecart(int pricecart) {
        this.pricecart = pricecart;
    }
    
    
}
