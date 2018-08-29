/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vudung
 */
@Entity
@Table(name = "User")
public class User implements Serializable{
    @Id
    @Column(name = "UserId",nullable = false)
    private String UserId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "picture")
    private String picture;
    
    @Column(name = "accessToken")
    private String accessToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Cart> Carts;

    public User(String name, String email, String picture, String accessToken, List<Cart> Carts) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.accessToken=accessToken;
        this.Carts = Carts;
    }

    public User(String UserId, String name, String email, String picture, String accessToken) {
        this.UserId = UserId;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.accessToken = accessToken;
    }
    
    public User(String name, String email, String picture, String accessToken) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.accessToken = accessToken;
    }
    
    public User() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<Cart> getCarts() {
        return Carts;
    }

    public void setCarts(List<Cart> Carts) {
        this.Carts = Carts;
    }

    
    
}
