/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.hoatra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoatra.model.DAO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vudung
 */
@Controller
public class welcomeController {
    List listProuct= new DAO().getListProduct();
    List listTopping= new DAO().getListTopping();
    List listLevelSugar=new DAO().getListLevelSugar();
    @RequestMapping("/")
    public String welcome() {	
            return "welcome";
    }
    
    @RequestMapping("/ListProduct")
    @ResponseBody
    public String getListProduct() throws JsonProcessingException{
        String s=new ObjectMapper().writeValueAsString(listProuct);
        return s;
    }
    @RequestMapping("/ListTopping")
    @ResponseBody
    public String getListTopping() throws JsonProcessingException{
        String s=new ObjectMapper().writeValueAsString(listTopping);
        return s;
    }
    @RequestMapping("/ListLevelSugar")
    @ResponseBody
    public String getListLevelSugar() throws JsonProcessingException{
        String s=new ObjectMapper().writeValueAsString(listLevelSugar);
        return s;
    }
    
    @RequestMapping(value="/checkout", method = RequestMethod.POST)
    public String postcheckout(@ModelAttribute(value = "itemcartArr") String itemcartArr){
        
        System.out.println(itemcartArr);
        return "checkout";
    }
    
}
