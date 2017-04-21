package com.emusicstore.controller.admin;

import com.emusicstore.model.Product;
import com.emusicstore.service.ProductService;
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

/**
 * Created by CK on 18-04-2017.
 */

@Controller
@RequestMapping("/admin")
public class AdminProduct {

   private Path path;

   @Autowired
    ProductService productService;

   @RequestMapping("/product/addproduct")
    public String addProduct(Model model){
       Product product = new Product();
       product.setProductCategory("instrument");
       product.setProductCondition("new");
       product.setProductStatus("active");

       model.addAttribute("product",product);

       return "addproduct";
   }

   @RequestMapping("/product/editproduct/{id}")
   public String editProduct(@PathVariable("id") int id, Model model){
         Product product=productService.getProductById(id);
         model.addAttribute("product",product);

         return "editproduct";
   }



   @RequestMapping(value="/product/addproduct", method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                 HttpServletRequest request){
        if(result.hasErrors()){
            return "addproduct";
        }
        productService.addProduct(product);

       MultipartFile productImage=product.getProductImage();
       String rootDirectory=request.getSession().getServletContext().getRealPath("/");
       path= Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\"+product.getProductId() + ".png");

       if(productImage !=null && !productImage.isEmpty()){
           try{
               productImage.transferTo(new File(path.toString()));
           }catch(Exception e){
               e.printStackTrace();
               throw new RuntimeException("Product image saving failed",e);
           }

       }
       return "redirect:/admin/productinventory";
   }

   @RequestMapping(value="/product/editproduct", method=RequestMethod.POST)
    public String editProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                  HttpServletRequest request){

        if(result.hasErrors()){
            return "editproduct";
        }

        MultipartFile productImage=product.getProductImage();
        String rootDirectory=request.getSession().getServletContext().getRealPath("/");
        path=Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId() + ".png");
        if(productImage != null && !productImage.isEmpty()){
            try{
                productImage.transferTo(new File(path.toString()));
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed");
            }
        }
        productService.editProduct(product);

        return "redirect:/admin/productinventory";
   }

   @RequestMapping("/product/deleteproduct/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model,HttpServletRequest request){

        String rootDirectory=request.getSession().getServletContext().getRealPath("/");
        path=Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + id + ".png");

        if(Files.exists(path)){
            try{
                Files.delete(path);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        Product product=productService.getProductById(id);
        productService.deleteProduct(product);

        return "redirect:/admin/productinventory";
   }

}