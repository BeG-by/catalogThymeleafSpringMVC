package by.beg.catalog.controller;


import by.beg.catalog.entity.Product;
import by.beg.catalog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EditProductController {

    private ProductService productService;
    private Product editProduct;

    public EditProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    public ModelAndView getEdit(ModelAndView modelAndView, @PathVariable int id) {
        editProduct = productService.searchProductById(id);
        modelAndView.addObject("product", editProduct);
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/edit")
    public ModelAndView postEdit(ModelAndView modelAndView, @Valid @ModelAttribute Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("edit");
        } else {
            productService.editProduct(editProduct, product);
            modelAndView.setViewName("main");
        }

        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public ModelAndView removeProduct(ModelAndView modelAndView, @PathVariable int id) {
        productService.removeProduct(id);
        modelAndView.setViewName("main");
        return modelAndView;
    }

}
