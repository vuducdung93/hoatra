/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vudung
 */
@Entity
@Table(name = "Delivery")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "userID")
    private String userID;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "listitem")
    private String listitem;

    public Order(int id, String userID, String name, String listitem) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.listitem = listitem;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListitem() {
        return listitem;
    }

    public void setListitem(String listitem) {
        this.listitem = listitem;
    }
    
    
}
