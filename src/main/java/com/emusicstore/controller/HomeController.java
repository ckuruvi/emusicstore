package com.emusicstore.controller;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping("/admin/productinventory")
    public String productInventory(Model model){

        List<Product> products=productDao.getAllProducts();
        model.addAttribute("products",products);

        return "productinventory";
    }

    @RequestMapping("/admin/productinventory/addproduct")
    public String addProduct(Model model){

        Product product=new Product();
        product.setProductCategory("instrument");
        product.setProductCondition("new");
        product.setProductStatus("active");
        model.addAttribute("product",product);

        return "addproduct";

    }

    @RequestMapping(value="/admin/productinventory/addproduct",method= RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request){
         productDao.addProduct(product);

        MultipartFile productImage=product.getProductImage();
        String rootDirectory=request.getSession().getServletContext().getRealPath("/");
        path=Paths.get(rootDirectory+"\\WEB-INF\\resources\\images\\"+product.getProductId()+".png");

        if(productImage != null && !productImage.isEmpty()){
            try{
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
               throw new RuntimeException("product image saving failed") ;
            }
        }
         return "redirect:/admin/productinventory";
    }


    @RequestMapping("/admin/productinventory/deleteproduct/{productId}")
    public String deleteProduct(@PathVariable String productId, Model model,HttpServletRequest request){

        String rootDirectory=request.getSession().getServletContext().getRealPath("/");
        path=Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + productId +".png");

        if(Files.exists(path)){

            try{
                Files.delete(path);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        productDao.deleteProduct(productId);
        return "redirect:/admin/productinventory";
    }
}
