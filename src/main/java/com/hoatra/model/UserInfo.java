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
public class UserInfo {
    private int UserId;
    private String idface;
    private String name;
    private String email;
    private String picture;
    private String accessToken;

    public UserInfo() {
    }

    public UserInfo(int UserId, String idface, String name, String email, String picture, String accessToken) {
        this.UserId = UserId;
        this.idface = idface;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.accessToken = accessToken;
    }

    public UserInfo(String idface, String name, String email, String picture, String accessToken) {
        this.idface = idface;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.accessToken = accessToken;
    }
    
    
    
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getIdface() {
        return idface;
    }

    public void setIdface(String idface) {
        this.idface = idface;
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
    
    
}
