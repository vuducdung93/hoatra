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
        arr.add(new Product(1,"Trà số 1",15000,"1.jpg",1));
        arr.add(new Product(2,"Trà số 2",16000,"1.jpg",1));
        arr.add(new Product(3,"Trà số 3",17000,"1.jpg",1));
        arr.add(new Product(4,"Trà số 4",18000,"1.jpg",1));
        arr.add(new Product(5,"Trà số 5",19000,"1.jpg",1));
        
        arr.add(new Product(6,"Trà số 6",15000,"1.jpg",2));
        arr.add(new Product(7,"Trà số 7",16000,"1.jpg",2));
        arr.add(new Product(8,"Trà số 8",17000,"1.jpg",2));
        arr.add(new Product(9,"Trà số 9",18000,"1.jpg",2));
        arr.add(new Product(10,"Trà số 10",19000,"1.jpg",2));
        
        arr.add(new Product(11,"Trà số 6",15000,"1.jpg",3));
        arr.add(new Product(12,"Trà số 7",16000,"1.jpg",3));
        arr.add(new Product(13,"Trà số 8",17000,"1.jpg",3));
        arr.add(new Product(14,"Trà số 9",18000,"1.jpg",3));
        arr.add(new Product(15,"Trà số 10",19000,"1.jpg",3));
        
        arr.add(new Product(16,"Trà số 6",15000,"1.jpg",4));
        arr.add(new Product(17,"Trà số 7",16000,"1.jpg",4));
        arr.add(new Product(18,"Trà số 8",17000,"1.jpg",4));
        arr.add(new Product(19,"Trà số 9",18000,"1.jpg",4));
        arr.add(new Product(20,"Trà số 10",19000,"1.jpg",4));
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
