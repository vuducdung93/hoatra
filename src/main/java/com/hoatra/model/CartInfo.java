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
public class CartInfo {
    private int id;
    private String fullname;
    private String phone;
     private String address;
    private String notes;
    private String name; 
    private long count;

    public CartInfo(int id, String fullname, String phone, String address, String notes) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.notes = notes;
    }

    public CartInfo(int id, String notes, String name, long count) {
        this.id = id;
        this.notes = notes;
        this.name = name;
        this.count = count;
    }
    
    public CartInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
    
}
