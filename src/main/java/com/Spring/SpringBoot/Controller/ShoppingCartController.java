package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.entity.User;
import com.Spring.SpringBoot.entity.cartItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private com.Spring.SpringBoot.Dao.cartItemsDao cartItemsDao;

    private User user;
    @GetMapping("/cart")
    public  String showShoppingCart (Model model)
    {
        List<cartItems> cartItems=cartItemsDao.findByUser(user);
        model.addAttribute("cartItems",cartItems);
        model.addAttribute("title","shopping cart");
        return "Shopping_cart";
    }
}
