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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by CK on 07-04-2017.
 */
@Controller
public class AdminController {
    private Path path;

    @Autowired
    private ProductDao productDao;

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
    public String addProductPost(@Valid @ModelAttribute("product") Product product,
                                 BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            return "addproduct";
        }

        productDao.addProduct(product);

        MultipartFile productImage=product.getProductImage();
        String rootDirectory=request.getSession().getServletContext().getRealPath("/");
        path= Paths.get(rootDirectory+"\\WEB-INF\\resources\\images\\"+product.getProductId()+".png");

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
    public String deleteProduct(@PathVariable String productId, Model model, HttpServletRequest request){

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

    @RequestMapping("/admin/productinventory/editproduct/{productId}")
    public String editProduct(@PathVariable("productId") String id,Model model){
        Product product=productDao.getProductById(id);
        model.addAttribute(product);

        return "editproduct";
    }

    @RequestMapping(value="/admin/productinventory/editproduct",method=RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("product") Product product,BindingResult result,
                              Model model, HttpServletRequest request){

        if(result.hasErrors()){
            return "editproduct";
        }
        MultipartFile productImage=product.getProductImage();

        String rootDirectory=request.getSession().getServletContext().getRealPath("/");

        path=Paths.get(rootDirectory+"\\WEB-INF\\resources\\images\\" + product.getProductId() + ".png");

        if(productImage!=null && !productImage.isEmpty()){
            try{
                product.getProductImage().transferTo(new File(path.toString()));
            }catch(Exception e){
                throw new RuntimeException("product image saving failed", e);
            }

        }

        productDao.editProduct(product);

        return "redirect:/admin/productinventory";
    }
}
