package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.CategoryDao;
import com.Spring.SpringBoot.Dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @GetMapping({"/","/dashboard"})
    public ModelAndView dashboard(ModelAndView model)
    {
       /* Pageable pageable= PageRequest.of(0,10);
        Page<Products> page= productDao.findAll(pageable);
        List<Products> productsList=page.getContent();
        model.addObject("title","dashboard");

        model.addObject("products",productsList);*/
        model.setViewName("dashboard");
        return model;
    }
    @GetMapping("/shop")
    public String getAllCategories(Model model)
    {
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("products",productDao.findAll());
        return "shop";
    }

    /*@GetMapping("/shopByCat")
    public String shopByCategory (@RequestParam("id")long Cid, Model model)
    {
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("products",productDao.);
        return "shop";
    }*/
    @GetMapping (value="/productview")
    public String viewProduct(ModelAndView model)
    {
        model.addObject("title", "Viewed Product");
        return "viewProduct";
    }


}
