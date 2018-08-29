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
    private String notes;
    private String name; 
    private long count;

    public CartInfo(int id, String notes,String name,long count) {
        this.id = id;
        this.notes = notes;
        this.name=name;
        this.count=count;
    }

    public CartInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
