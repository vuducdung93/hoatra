/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.DAO;

import com.hoatra.entity.Product;
import com.hoatra.model.ProductInfo;
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
public class productDAO {
    @Autowired
    private SessionFactory sessionFactory;
    public productDAO(){
    }
    
    public List<ProductInfo> getListProduct(){
        Session session = this.sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            String sql="select New "+ProductInfo.class.getName()+
                " (p.id,p.name,p.price,p.images,p.categories.id,p.categories.name) "+
                " from "+Product.class.getName()+" p";
            Query<ProductInfo> query=session.createQuery(sql, ProductInfo.class);
           
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println(ex);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
}
