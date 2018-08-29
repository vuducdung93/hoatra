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
public class cartItemInfo {
    private int id;
    private int quantity;
    private boolean size;
    private int mucduong;
    private String listTopping;
    //properties product
    private int idP;
    private String nameP;
    private int priceP;
    private String imagesP;
    
    List<ToppingInfo> list;
    private int total;

    public cartItemInfo(int id, int quantity, boolean size, int mucduong,String listTopping, int idP, String nameP, int priceP, String imagesP) {
        this.id = id;
        this.quantity = quantity;
        this.size = size;
        this.mucduong = mucduong;
        this.listTopping = listTopping;
        this.idP = idP;
        this.nameP = nameP;
        this.priceP = priceP;
        this.imagesP = imagesP;
        
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

    public String getListTopping() {
        return listTopping;
    }

    public void setListTopping(String listTopping) {
        this.listTopping = listTopping;
    }
    
    public void setMucduong(int mucduong) {
        this.mucduong = mucduong;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public int getPriceP() {
        return priceP;
    }

    public void setPriceP(int priceP) {
        this.priceP = priceP;
    }

    public String getImagesP() {
        return imagesP;
    }

    public void setImagesP(String imagesP) {
        this.imagesP = imagesP;
    }

    public List<ToppingInfo> getList() {
        return list;
    }

    public void setList(List<ToppingInfo> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }  
    
}
