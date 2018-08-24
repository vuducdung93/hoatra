/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.DAO;

import com.hoatra.entity.Product;
import com.hoatra.entity.ToppingProduct;
import com.hoatra.model.ProductInfo;
import com.hoatra.model.ToppingInfo;
import com.hoatra.model.ToppingProductInfo;

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
public class ToppingProductDAO {
    @Autowired
    private SessionFactory sessionFactory;
    public ToppingProductDAO(){
    }

    public ToppingProductInfo getList(int productID){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            String sql1="select New "+ToppingInfo.class.getName()+
                " (t.topping.id,t.topping.name,t.topping.price) "+
                " from "+ToppingProduct.class.getName()+" t where t.product.id="+productID;
            Query<ToppingInfo> query1=session.createQuery(sql1, ToppingInfo.class);
            
            String sql2="select New "+ToppingProductInfo.class.getName()+
                " (p.id,p.name,p.price,p.images) "+
                " from "+Product.class.getName()+" p where p.id="+productID;
            ToppingProductInfo t=session.createQuery(sql2, ToppingProductInfo.class).uniqueResult();
            t.setToppings(query1.getResultList());
            session.getTransaction().commit();
        return t;
        
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {

            session.close();
        }
        return null;
    }
}
