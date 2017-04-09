package com.emusicstore.controller;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by CK on 05-04-2017.
 */

@Controller
public class HomeController {

    private Path path;

   @Autowired
   private ProductDao productDao;


    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/productlist")
    public String getProducts(Model model){

        List<Product> products=productDao.getAllProducts();

        model.addAttribute("products",products);
        return "productlist";
    }

    @RequestMapping("/productlist/viewproduct/{productId}")
    public String viewProduct(@PathVariable String productId, Model model) throws IOException {
        Product product=productDao.getProductById(productId);
        model.addAttribute(product);
        return "viewproduct";
    }



}
