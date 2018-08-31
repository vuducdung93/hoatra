/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vudung
 */
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "fullname")
    private String fullname;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "notes")
    private String notes;
    
    @ManyToOne
    @JoinColumn(name = "User_UserId")
    private User user;
    
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    List<CartItem> cartItems;

    public Cart(int id, String fullname, String phone, String address, String notes, User user, List<CartItem> cartItems) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.notes = notes;
        this.user = user;
        this.cartItems = cartItems;
    }
    public Cart() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
}
