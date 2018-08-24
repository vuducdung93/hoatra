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
    private String username;

    public UserInfo(int UserId, String username) {
        this.UserId = UserId;
        this.username = username;
    }

    public UserInfo() {
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
