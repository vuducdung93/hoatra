/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoatra.entity.Cart;
import com.hoatra.entity.Order;
import com.hoatra.model.ToppingInfo;
import com.hoatra.model.cartItemInfo;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author vudung
 */
@Repository
@Transactional
public class sendMail {
    @Autowired
    public JavaMailSender emailSender;
 
    public void sendHTML(Order or,Cart cart) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        List<cartItemInfo> l=new ObjectMapper().readValue(or.getListitem(), new TypeReference<List<cartItemInfo>>(){});
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
         System.out.println(cart.getFullname());
        String html = "";
                html+= "<h3>Order from Hoa Trà</h3>";
                html+="<h4>UserID: "+or.getUserID()+"</h4>";
                html+="<h4>Name: "+or.getName()+"</h4>";
                html+="<table style=\"border: 1px solid black\">"+
                        "<tr><th style=\"border: 1px solid black\">Tên sản phẩm</th>"+
                        "<th style=\"border: 1px solid black\">Mức đường</th>"+
                        "<th style=\"border: 1px solid black\">size</th>"+
                        "<th style=\"border: 1px solid black\">Topping</th></tr>";
                
                for(cartItemInfo c:l){
                    html+="<tr>"+
                            "<td style=\"border: 1px solid black\">"+c.getNameP()+"</td>";
                    int d=c.getMucduong();
                    switch(d){
                        case 1 :
                            html+="<td style=\"border: 1px solid black\">100% đường</td>";
                            break;
                        case 2 :
                            html+="<td style=\"border: 1px solid black\">70% đường</td>";
                            break;
                        case 3 :
                            html+="<td style=\"border: 1px solid black\">30% đường</td>";
                            break;
                        case 4 :
                            html+="<td style=\"border: 1px solid black\">0% đường</td>";
                            break;
                    }
                    if(c.isSize())
                        html+="<td style=\"border: 1px solid black\">Size lớn</td>";
                    else
                        html+="<td style=\"border: 1px solid black\">Size nhỏ</td>";
                    html+="<td style=\"border: 1px solid black\">";
                    for(ToppingInfo t:c.getList()){
                        html+="<p>"+t.getName()+"</p>";  
                    }
                          html+= "</td></tr>";
                }
                html+="</table>";
                html+="<h3>Recipient's name: "+cart.getFullname()+"</h3>"+
                        "<h3>Phone number: "+cart.getPhone()+"</h3>"+
                        "<h3>Address: "+cart.getAddress()+"</h3>"+
                        "<h3>Notes: "+cart.getNotes()+"</h3>";
                
                html+="<a href=\"http://trasua.ga\">Block tai khoan nay vi da vi pham</a>"; 
        message.setContent(html, "text/html ; charset=UTF-8"); 
        helper.setTo("trantan0207@gmail.com"); 
        helper.setSubject("Don dat hang số "+or.getId());
         
     
        this.emailSender.send(message);

    }
}
