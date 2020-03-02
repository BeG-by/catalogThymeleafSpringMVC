package by.beg.catalog.controller;

import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.ProductTypeEnum;
import by.beg.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ModelAndView getProduct(ModelAndView modelAndView) {
        modelAndView.addObject("product", new Product());
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addProduct(ModelAndView modelAndView, @Valid @ModelAttribute Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("create");
        } else {
            productService.createProduct(product);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getForEditProduct(ModelAndView modelAndView, @PathVariable int id) {
        modelAndView.addObject("product", productService.getProductById(id));
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView postEdit(ModelAndView modelAndView, @Valid @ModelAttribute Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("edit");
        } else {
            productService.updateProduct(product);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }


    @GetMapping("/remove/{id}")
    public ModelAndView removeProduct(ModelAndView modelAndView, @PathVariable int id) {
        productService.removeProduct(id);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @ModelAttribute("types")
    public ProductTypeEnum[] addAttributes() {
        return ProductTypeEnum.values();
    }

}
