/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.DAO;

import com.hoatra.entity.Cart;
import com.hoatra.entity.User;
import com.hoatra.model.CartInfo;
import com.hoatra.model.UserInfo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author vudung
 */
@Repository
@Transactional
public class userDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public userDAO() {
    }
    
    public CartInfo checkContain(User user){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            User u= session.get(User.class, user.getUserId());
            if(u==null){
                session.save(user);
                session.flush();
            }

            String sql="select new "+CartInfo.class.getName()+"(c.id,c.notes,c.user.name,(select sum(quantity) from c.cartItems)) from "+Cart.class.getName()+" c where c.user.UserId='"+user.getUserId()+"'";
            List<CartInfo> carts=session.createQuery(sql,CartInfo.class).getResultList();
            if(carts.size()==0){
                Cart cart=new Cart();
                cart.setUser(user);
                session.save(cart);
                session.flush();
                session.getTransaction().commit();
                return new CartInfo(cart.getId(),"",user.getName(),0);
            }else{
                session.getTransaction().commit();
                return carts.get(0);
            }        
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
    public CartInfo getCart(String id){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            String sql="select new "+CartInfo.class.getName()+"(c.id,c.fullname,c.phone,c.address,c.notes) from "+Cart.class.getName()+" c where c.user.UserId='"+id+"'";
            List<CartInfo> carts=session.createQuery(sql,CartInfo.class).getResultList();
            session.getTransaction().commit();
            return carts.get(0);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
    public Cart updateCart(int id,String fullname,String phone,String address,String notes){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            Cart c=session.get(Cart.class, id);
            c.setFullname(fullname);
            c.setAddress(address);
            c.setNotes(notes);
            c.setPhone(phone);
            session.update(c);
            session.flush();
            session.getTransaction().commit();
            return c;
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
    
}
