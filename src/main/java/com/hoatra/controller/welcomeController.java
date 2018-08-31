/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoatra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoatra.DAO.ToppingProductDAO;
import com.hoatra.DAO.addtocart;
import com.hoatra.DAO.categoriesDAO;
import com.hoatra.DAO.productDAO;
import com.hoatra.DAO.sendMail;
import com.hoatra.bean.Product;
import com.hoatra.bean.Topping;
import com.hoatra.bean.cartItem;
import com.hoatra.bean.itemProduct;
import com.hoatra.bean.levelSugar;
import com.hoatra.DAO.userDAO;
import com.hoatra.entity.Cart;
import com.hoatra.entity.Order;
import com.hoatra.entity.User;
import com.hoatra.model.CartInfo;
import com.hoatra.model.CategoriesInfo;
import com.hoatra.model.DAO;
import com.hoatra.model.ProductInfo;
import com.hoatra.model.ToppingInfo;
import com.hoatra.model.ToppingProductInfo;
import com.hoatra.model.UserInfo;
import com.hoatra.model.cartItemInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired(required=true)
    private userDAO userdao;
    
    @Autowired(required=true)
    private sendMail mail;
    
    @Autowired(required=true)
    private categoriesDAO categoriesdao;
    
    @Autowired(required=true)
    private productDAO productdao;
    
    @Autowired(required=true)
    private ToppingProductDAO ToppingProductdao;
    
    @Autowired(required=true)
    private addtocart addtocartdao;
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
    
    // tra ve listproduct khi load categories
    @RequestMapping("/ListProduct")
    @ResponseBody
    public String getListProduct() throws JsonProcessingException{
        String s=new ObjectMapper().writeValueAsString(productdao.getListProduct());
        return s;
    }
    
    // tra ve listcategories khi load full product
    @RequestMapping("/ListCategories")
    @ResponseBody
    public String getListCategories() throws JsonProcessingException{
        String s=new ObjectMapper().writeValueAsString(categoriesdao.getListCate());
        return s;
    }
    
    
    // tra ve details cua san pham khi click vao plus
    @RequestMapping("/Productdetails")
    @ResponseBody
    public String getProductdetails(HttpServletRequest request) throws JsonProcessingException{
        int id=Integer.parseInt(request.getParameter("id"));    
        String s=new ObjectMapper().writeValueAsString(ToppingProductdao.getList(id));
        
        return s;
    }     
    @RequestMapping("/ListLevelSugar")
    @ResponseBody
    public String getListLevelSugar() throws JsonProcessingException{
        String s=new ObjectMapper().writeValueAsString(listLevelSugar);
        return s;
    }
    
    @RequestMapping("/checkout")
    public String checkout(Model model,HttpSession session){
        User us=(User)session.getAttribute("USER");
        model.addAttribute("cartItems",addtocartdao.getListcartitem(us.getUserId()));
        model.addAttribute("cart",userdao.getCart(us.getUserId()));
        return "checkout2";
    }
    
    @RequestMapping(value="/order", method=RequestMethod.POST)//when user Order
    @ResponseBody
    public String order(HttpSession session,HttpServletRequest request) throws IOException, MessagingException{
        User u=(User)session.getAttribute("USER");
        
        int id=Integer.parseInt(request.getParameter("id"));
        String fullname=request.getParameter("fullname");
        String phone=request.getParameter("phone");
        String address=request.getParameter("address");
        String notes=request.getParameter("notes");
        System.out.println(id);
        Cart c=userdao.updateCart(id, fullname, phone, address, notes);
        Order or=addtocartdao.order(u.getUserId(), u.getName());
        mail.sendHTML(or,c);
        return "";
    }
    
    @RequestMapping(value="/addtocart", method=RequestMethod.POST)//add item to cart
    @ResponseBody
    public String addtocart(HttpServletRequest request){
        String idProduct=request.getParameter("idProduct");
        String idcart=request.getParameter("idcart");
        String quantity=request.getParameter("quantity");
        String size=request.getParameter("size");
        String topping=request.getParameter("topping");
        String mucduong=request.getParameter("mucduong");
        System.out.println(idProduct+" "+idcart+" "+quantity+" "+size+" "+topping+" "+mucduong);
        addtocartdao.add(idProduct, idcart, quantity, size, topping, mucduong);
        return "";
    }
    
    @RequestMapping(value="/removeitem", method=RequestMethod.POST)// remove item cart
    @ResponseBody
    public String removeitem(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("itemId").trim());
        boolean a=addtocartdao.removeItem(id);
        return a+"";
    }
    @RequestMapping(value="/updateItem", method=RequestMethod.POST)// remove item cart
    @ResponseBody
    public String updateItem(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("itemId").trim());
        int value=Integer.parseInt(request.getParameter("value"));
        boolean a=addtocartdao.removeItem(id);
        return a+"";
    }
    
    //when user login
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request,HttpSession session) throws JsonProcessingException{
        User user=new User(         request.getParameter("userID"),
                                    request.getParameter("name"),
                                    request.getParameter("email"),
                                    request.getParameter("picture"),
                                    request.getParameter("accessToken"));
        
        //String s=new ObjectMapper().writeValueAsString(user);
        System.out.println(new ObjectMapper().writeValueAsString(user));
        CartInfo cart=userdao.checkContain(user);  
        session.setAttribute("USER", user);
        return new ObjectMapper().writeValueAsString(cart);
    }
    // when user loginted get cart
    @RequestMapping("/getCart")
    @ResponseBody
    public String getCart(HttpSession session) throws JsonProcessingException{
        CartInfo cart=userdao.checkContain((User) session.getAttribute("USER"));
        return new ObjectMapper().writeValueAsString(cart);
    }
    
}   
    