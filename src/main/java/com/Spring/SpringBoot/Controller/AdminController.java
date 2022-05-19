package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.CategoryDao;
import com.Spring.SpringBoot.Dao.ProductDao;
import com.Spring.SpringBoot.entity.Category;
import com.Spring.SpringBoot.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AdminController {

    public static String uploadDir=System.getProperty("user.dir")+"src/main/resources/static/productImages";


    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;


    @GetMapping(value="/adminPanel")
    public String adminDashboard(Model model)
    {
        model.addAttribute("title","Admin Dashboard");
        return "AdminPanel";
    }
    @GetMapping(value="/categories")
    public String getcategories(Model model)
    {
        Pageable pageable= PageRequest.of(0,10);
        Page<Category> page= categoryDao.findAll(pageable);
        List<Category> CategoryList=page.getContent();
        model.addAttribute("categories",CategoryList);
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
    public String addCategoryPost(@ModelAttribute("category") Category category)
    {
        categoryDao.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/deleteCategory")
    public String deleteCat(@RequestParam("id") long Cid)
    {
        categoryDao.deleteById(Cid);
        return "redirect:/categories";
    }

    @GetMapping("/updateCategory")
    public String updateCat(@RequestParam("id") long Cid, Model model)
    {
        Optional<Category> category= categoryDao.findById(Cid);
        if(category.isPresent())
        {
            model.addAttribute("category", category.get());
            return "AddCategory";
        }
        else{
            return "404";
        }
    }

    //------------------Product API section------------------------------------------

    @GetMapping(value="/products")
    public String getProducts(Model model)
    {
        Pageable pageable= PageRequest.of(0,10);
        Page<Products> page= productDao.findAll(pageable);
        List<Products> ProductsList=page.getContent();
        model.addAttribute("products",ProductsList);
        model.addAttribute("title","List Products");
        return "ViewProducts";
    }

    @GetMapping(value ="/addProduct")
    public String addProduct(Model model, Products products)
    {
        model.addAttribute("product", products);
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("title","Add Categories");
        return "AddProduct";
    }

    @RequestMapping(value="/addProduct", method = POST)
    public String addProductPost(Products products, @RequestParam("image")MultipartFile file) throws IOException
    {
        String imageUUID ;
        byte[] data=file.getBytes();
        if(!file.isEmpty())
        {
            imageUUID= file.getOriginalFilename();
            Path imgPathAndName = Paths.get(uploadDir,imageUUID);
            Files.write(imgPathAndName,data);
        }else{
            imageUUID = products.getPimgName();
        }
        products.setPimgName(imageUUID);
        products.setPimg(data);
        //System.out.println(products);
        productDao.save(products);
        return "redirect:/products";
    }

}

