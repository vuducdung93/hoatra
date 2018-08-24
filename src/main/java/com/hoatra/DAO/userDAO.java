/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.DAO;

import com.hoatra.entity.User;
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
    
    public List<UserInfo> listUser(){
        String sql="select New "+UserInfo.class.getName()+
                " (e.UserId,e.username) "+
                " from "+User.class.getName()+" e";
        Session session = this.sessionFactory.getCurrentSession();
        Query<UserInfo> query=session.createQuery(sql, UserInfo.class);
        return query.getResultList();
    }
    
}
