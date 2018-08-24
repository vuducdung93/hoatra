/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.DAO;

import com.hoatra.entity.Product;
import com.hoatra.entity.Categories;
import com.hoatra.model.CategoriesInfo;
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
public class categoriesDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
    public categoriesDAO(){       
    }
    
    public List<CategoriesInfo> getListCate(){
        Session session = this.sessionFactory.openSession();
        try {
        session.getTransaction().begin();
        String sql="select New "+CategoriesInfo.class.getName()+
                " (c.id,c.name) "+
                " from "+Categories.class.getName()+" c";
        
        Query<CategoriesInfo> query=session.createQuery(sql, CategoriesInfo.class);
        return query.getResultList();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {

            session.close();
        }
        return null;
    }
    /*String sql="select New "+CategoriesInfo.class.getName()+
                " (c.id,c.name,(select New com.hoatra.model.ProductInfo(p.id,p.name,p.price,p.images) from c.products p) "+
                " from "+Categories.class.getName()+" c";*/
}
