package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.CategoryDao;
import com.Spring.SpringBoot.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private CategoryDao categoryDao;


    @GetMapping(value="/adminPanel")
    public String adminDashboard(Model model)
    {
        model.addAttribute("title","Admin Dashboard");
        return "AdminPanel";
    }
    @GetMapping(value="/categories")
    public String getcategories(Model model)
    {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title","List Categories");
        return "ViewCategories";
    }

    @GetMapping(value ="/addCategory")
    public String addCategories(Model model)
    {
        model.addAttribute("category", new Category());
        model.addAttribute("title","Add Categories");
        return "AddCategory";
    }

    @PostMapping(value="/addCategory")
    public String getAllCat(@ModelAttribute("category") Category category)
    {
        categoryDao.save(category);
        return "redirect:/categories";
    }


}
