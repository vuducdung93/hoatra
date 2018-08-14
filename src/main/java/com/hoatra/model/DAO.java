/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.model;

import com.hoatra.bean.Product;
import com.hoatra.bean.Topping;
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
        arr.add(new Topping("Trân châu đen",5000));
        arr.add(new Topping("Trân châu trắng",5000));
        arr.add(new Topping("Thạch nha đam",5000));
        arr.add(new Topping("Kem",5000));
        arr.add(new Topping("Trân châu giòn",5000));
        return arr;
    }
}
