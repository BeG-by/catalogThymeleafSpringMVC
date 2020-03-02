package by.beg.catalog.controller;


import by.beg.catalog.entity.Product;
import by.beg.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    private ProductService productService;

    @Autowired
    public MainController(ProductService productServiceImpl) {
        this.productService = productServiceImpl;
    }

    @GetMapping
    public ModelAndView getMain(ModelAndView modelAndView) {
        modelAndView.addObject("productList", productService.getAllProducts());
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @GetMapping("/{sort}")
    public ModelAndView getSort(ModelAndView modelAndView, @PathVariable String sort) {

        List<Product> productList = productService.getAllProducts();

        switch (sort.toLowerCase()) {
            case "id":
                productService.orderById(productList);
                break;
            case "name":
                productService.orderByName(productList);
                break;
            case "type":
                productService.orderByType(productList);
                break;
            case "price":
                productService.orderByPrice(productList);
        }

        modelAndView.addObject("productList", productList);

        modelAndView.setViewName("main");
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView postSearch(ModelAndView modelAndView, @RequestParam String string) {

        List<Product> filterProducts = productService.findProducts(string);

        if (filterProducts.isEmpty()) {
            modelAndView.addObject("filterIsEmpty", true);
        } else {
            modelAndView.addObject("productList", filterProducts);
        }

        modelAndView.setViewName("main");

        return modelAndView;

    }

}
