/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.hoatra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoatra.bean.Product;
import com.hoatra.bean.Topping;
import com.hoatra.bean.cartItem;
import com.hoatra.bean.itemProduct;
import com.hoatra.bean.levelSugar;
import com.hoatra.model.DAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    List<Product> listProuct= new DAO().getListProduct();
    List<Topping> listTopping= new DAO().getListTopping();
    List<levelSugar> listLevelSugar=new DAO().getListLevelSugar();
    @RequestMapping(value= "/",method=RequestMethod.GET)
    public String checkscreen() {
        
            return "checkscreen";
            
    }
    @RequestMapping(value= "/", method=RequestMethod.POST)
    public String welcome(HttpServletRequest request) {
            int width=Integer.parseInt(request.getParameter("width"));
            int height=Integer.parseInt(request.getParameter("height"));
            if(width<=480)
                return "welcomeMobile";
            else
                return "welcomeDesktop";
            
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
    public String postcheckout(@ModelAttribute(value = "itemcartArr") String itemcartArr,Model model) throws IOException{
        List<itemProduct> listItemProduct=new ObjectMapper().readValue(itemcartArr, new TypeReference<List<itemProduct>>(){});
        List<cartItem> listcart=new ArrayList<cartItem>();
        
        listItemProduct.forEach((t) -> {
            Product p=listProuct.get(t.getIdProduct()-1);
            List<Topping> listopping=new ArrayList<Topping>();
            int[] a=t.getTopping();
            for(int j=0;j<a.length;j++){
                Topping f=listTopping.get(a[j]-1);
                listopping.add(f);
            }
            levelSugar l=listLevelSugar.get(t.getMucduong()-1);
            listcart.add(new cartItem(p,t.getQuantity(),t.isSize(),listopping,l));
        });
        model.addAttribute("listcart", listcart);
        String s=new ObjectMapper().writeValueAsString(listcart);
        System.out.println(s);

        return "checkout2";
    }
    
}
