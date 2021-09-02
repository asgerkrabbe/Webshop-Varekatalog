package dk.kea.varekatalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebshopController {

    @PostMapping("/startpage")
    public String startpage() {

        return "startpage.html";
    }

    @PostMapping("/product")
    public String editProduct(@RequestParam("id") int id){



        return "product.html";
    }


}
