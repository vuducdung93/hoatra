/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.DAO;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoatra.entity.Cart;
import com.hoatra.entity.CartItem;
import com.hoatra.entity.Order;
import com.hoatra.entity.Product;
import com.hoatra.entity.Topping;

import com.hoatra.model.ToppingInfo;
import com.hoatra.model.cartItemInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vudung
 */
@Repository
@Transactional
public class addtocart {
    @Autowired
    private SessionFactory sessionFactory;
    public addtocart(){}
    
    public void add(String idProduct,String idcart,String quantity,String size,String topping,String mucduong){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            Product p=(Product)session.get(Product.class,Integer.parseInt(idProduct));
            Cart c=(Cart)session.get(Cart.class,Integer.parseInt(idcart));
            CartItem i=new CartItem();
            i.setProduct(p);
            i.setCart(c);
            i.setMucduong(Integer.parseInt(mucduong));
            if(size.equals("true"))
                i.setSize(true);
            else
                i.setSize(false);
            i.setQuantity(Integer.parseInt(quantity));
            i.setListTopping(topping);
            
            session.save(i);
            session.flush();
            session.getTransaction().commit();
            
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    
    public List<cartItemInfo> getListcartitem(String id){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            String sql1="Select new "+cartItemInfo.class.getName()+
                    " (c.id,c.quantity,c.size,c.mucduong,c.listTopping,c.product.id,c.product.name,c.product.price,c.product.images) from "+
                    CartItem.class.getName()+" c where c.cart.user.UserId='"+id+"'";
            Query<cartItemInfo> query1=session.createQuery(sql1, cartItemInfo.class);
            List<cartItemInfo> l=query1.getResultList();
            l.forEach((t) -> {
                int total=t.getPriceP();
                if(t.getListTopping().equals("")==false){
                    String[] arStr = t.getListTopping().split("\\,");
                    
                    List<Integer> lt = new ArrayList<>();
                    for(String s:arStr){
                        lt.add(Integer.parseInt(s));
                    }

                    String sql2="Select new "+ToppingInfo.class.getName()+
                                " (t.id,t.name,t.price) from "+Topping.class.getName()+
                                " t where t.id IN (:ids)";
                    Query<ToppingInfo> query2=session.createQuery(sql2, ToppingInfo.class);
                    query2.setParameterList("ids", lt);
                    List<ToppingInfo> ec2=query2.getResultList();
                    t.setList(ec2);
                    
                    if(t.isSize()==true){
                       total+=8000;
                    }
                    for(ToppingInfo aws:ec2){
                        total+=aws.getPrice();
                    }
                    
                }
                t.setTotal(total);
            });
            session.getTransaction().commit();
            return l;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
    public boolean removeItem(int id){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            CartItem c=new CartItem();
            c.setId(id);
            session.delete(c);
            session.flush();
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
    public boolean updateItem(int id,int value){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            CartItem c=session.get(CartItem.class, id);
            c.setQuantity(value);
            session.update(c);
            session.flush();
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
   
    public Order order(String id,String name){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            String sql1="Select new "+cartItemInfo.class.getName()+
                    " (c.id,c.quantity,c.size,c.mucduong,c.listTopping,c.product.id,c.product.name,c.product.price,c.product.images) from "+
                    CartItem.class.getName()+" c where c.cart.user.UserId='"+id+"'";
            Query<cartItemInfo> query1=session.createQuery(sql1, cartItemInfo.class);
            List<cartItemInfo> l=query1.getResultList();
            l.forEach((t) -> {
                int total=t.getPriceP();
                if(t.getListTopping().equals("")==false){
                    String[] arStr = t.getListTopping().split("\\,");
                    
                    List<Integer> lt = new ArrayList<>();
                    for(String s:arStr){
                        lt.add(Integer.parseInt(s));
                    }

                    String sql2="Select new "+ToppingInfo.class.getName()+
                                " (t.id,t.name,t.price) from "+Topping.class.getName()+
                                " t where t.id IN (:ids)";
                    Query<ToppingInfo> query2=session.createQuery(sql2, ToppingInfo.class);
                    query2.setParameterList("ids", lt);
                    List<ToppingInfo> ec2=query2.getResultList();
                    t.setList(ec2);
                    
                    if(t.isSize()==true){
                       total+=8000;
                    }
                    for(ToppingInfo aws:ec2){
                        total+=aws.getPrice();
                    }
                    
                }
                t.setTotal(total);
            });
            String s=new ObjectMapper().writeValueAsString(l);
            System.out.println(s);
            System.out.println(s.length());
            Order k=new Order();
            k.setUserID(id);
            k.setName(name);
            k.setListitem(s);
            session.save(k);
            session.flush();
            session.getTransaction().commit();
            return k;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
        return null;
    }
}
