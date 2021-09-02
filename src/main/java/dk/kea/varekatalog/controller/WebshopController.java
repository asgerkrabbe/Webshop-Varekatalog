package dk.kea.varekatalog.controller;

import dk.kea.varekatalog.data_access.mappers.ProductMapper;
import dk.kea.varekatalog.domain.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class WebshopController {
    Product product;
    ProductMapper productMapper;



    @GetMapping("/")
    public String startpage() {
        return "index.html";
    }

    @GetMapping("/product")
    public String editProduct(@RequestParam("id") int id){

        return "product.html";
    }

    @GetMapping("/add-product")
    public String addProduct(WebRequest request) throws SQLIntegrityConstraintViolationException {
        String price = request.getParameter("product-price");
        String name = request.getParameter("product-name");

        productMapper.create(new Product(name, Integer.parseInt(price)));

        return "addeproduct.html";
    }
}
