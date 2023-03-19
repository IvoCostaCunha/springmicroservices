package com.course.client.controllers;

import com.course.client.beans.*;
import com.course.client.proxies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private MsProductProxy msProductProxy;
    private MsCartProxy msCartProxy;
    private MsOrderCartProxy msOrderCartProxy;

    @RequestMapping("/")

    public String index(Model model) {
        List<ProductBean> ps =  msProductProxy.list();
        model.addAttribute("products", ps);
        return "index-page";
    }

    @RequestMapping("/products/{id}")
    public String productsId(@PathVariable("id") long id, Model model) {
        Optional<ProductBean> p = msProductProxy.get(id);
        model.addAttribute("product", p.get());
        return "product-page";
    }

    @RequestMapping("/products")
    public String products(Model model) {
        List<ProductBean> ps =  msProductProxy.list();
        model.addAttribute("products", ps);
        return "index-page";
    }

    @RequestMapping("/cart/addItem/{id}")
    public String addItemToCart(@PathVariable("id") long id, Model model) {
        CartItemBean item = new CartItemBean();
        item.setQuantity(1);
        item.setProductId(id);
        msCartProxy.addProductToCart(1L, item);

        List<ProductBean> ps =  msProductProxy.list();
        model.addAttribute("products", ps);
        return "index-page";
    }

    @RequestMapping("/cart")
    public String getCart(Model model) {
        Optional<CartBean> c =  msCartProxy.getCart(1L);
        model.addAttribute("cart", c.get());
        return "cart-page";
    }

}
