package by.beg.catalog.controller;


import by.beg.catalog.entity.Product;
import by.beg.catalog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class MainController {

    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getMain(ModelAndView modelAndView) {
        productService.orderById();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{sort}")
    public ModelAndView getSort(ModelAndView modelAndView, @PathVariable String sort) {

        switch (sort.toLowerCase()) {
            case "id":
                productService.orderById();
                break;
            case "name":
                productService.orderByName();
                break;
            case "type":
                productService.orderByType();
                break;
            case "price":
                productService.orderByPrice();
        }

        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/search")
    public ModelAndView postSearch(ModelAndView modelAndView, @RequestParam String string) {

        ArrayList<Product> filterProducts = productService.filterProducts(string);

        if (filterProducts.isEmpty()) {
            modelAndView.addObject("filterIsEmpty", true);
        } else {
            modelAndView.addObject("productList", filterProducts);
        }

        modelAndView.setViewName("main");

        return modelAndView;

    }


    @ModelAttribute()
    public void addAttributes(ModelAndView modelAndView) {
        modelAndView.addObject("productList", productService.getProductList());
    }


}
