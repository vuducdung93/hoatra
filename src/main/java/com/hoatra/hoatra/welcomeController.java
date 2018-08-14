/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.hoatra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoatra.model.DAO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vudung
 */
@Controller
public class welcomeController {
    @RequestMapping("/")
    public String welcome() {	
            return "welcome";
    }
    
    @RequestMapping("/ListProduct")
    @ResponseBody
    public String getListProduct() throws JsonProcessingException{
        String s=new ObjectMapper().writeValueAsString(new DAO().getListProduct());
        
        return s;
    }
}
