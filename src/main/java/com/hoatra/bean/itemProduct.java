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
 * 
 * read value JSon list Product
 */
public class itemProduct {
   int idProduct;   
   int quantity;
   boolean size;
   int[] topping;
   int mucduong;

    public itemProduct(int idProduct, int quantity, boolean size, int[] topping, int mucduong) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.size = size;
        this.topping = topping;
        this.mucduong = mucduong;
    }

    public itemProduct() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public int[] getTopping() {
        return topping;
    }

    public void setTopping(int[] topping) {
        this.topping = topping;
    }

    public int getMucduong() {
        return mucduong;
    }

    public void setMucduong(int mucduong) {
        this.mucduong = mucduong;
    }

    
   

  
}
