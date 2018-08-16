/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.model;

import com.hoatra.bean.Product;
import com.hoatra.bean.Topping;
import com.hoatra.bean.levelSugar;
import java.util.ArrayList;

/**
 *
 * @author vudung
 */
public class DAO {
    public ArrayList<Product> getListProduct(){
        ArrayList<Product> arr=new ArrayList<>();
        arr.add(new Product(1,"Trà số 1",15000,"1.jpg"));
        arr.add(new Product(2,"Trà số 2",16000,"1.jpg"));
        arr.add(new Product(3,"Trà số 3",17000,"1.jpg"));
        arr.add(new Product(4,"Trà số 4",18000,"1.jpg"));
        arr.add(new Product(5,"Trà số 5",19000,"1.jpg"));
        return arr;
    }
    
    public ArrayList<Topping> getListTopping(){
        ArrayList<Topping> arr=new ArrayList<>();
        arr.add(new Topping(1,"Kem cheese",10000));
        arr.add(new Topping(2,"Trân châu trắng khựa",8000));
        arr.add(new Topping(3,"Trân châu đường đen (Milk & Milk Tea)",8000));
        arr.add(new Topping(4,"Trân châu bạch kim",5000));
        arr.add(new Topping(5,"Thạch nha đam",5000));
        return arr;
    }
    public ArrayList<levelSugar> getListLevelSugar(){
        ArrayList<levelSugar> arr=new ArrayList<>();
        arr.add(new levelSugar(1,"100% đường"));
        arr.add(new levelSugar(2,"70% đường"));
        arr.add(new levelSugar(3,"30% đường"));
        arr.add(new levelSugar(4,"0% đường"));
        return arr;
    }
}
