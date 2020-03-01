package by.beg.catalog.controller;


import by.beg.catalog.entity.Product;
import by.beg.catalog.service.ProductServiceImpl;
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

//    private ProductServiceImpl productServiceImpl;
//    private Product editProduct;
//
//    public EditProductController(ProductServiceImpl productServiceImpl) {
//        this.productServiceImpl = productServiceImpl;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
//    public ModelAndView getEdit(ModelAndView modelAndView, @PathVariable int id) {
//        editProduct = productServiceImpl.searchProductById(id);
//        modelAndView.addObject("product", editProduct);
//        modelAndView.setViewName("edit");
//        return modelAndView;
//    }
//
//    @RequestMapping(method = RequestMethod.POST, path = "/edit")
//    public ModelAndView postEdit(ModelAndView modelAndView, @Valid @ModelAttribute Product product, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("edit");
//        } else {
//            productServiceImpl.editProduct(editProduct, product);
//            modelAndView.setViewName("redirect:/");
//        }
//
//        return modelAndView;
//    }
//
//
//    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
//    public ModelAndView removeProduct(ModelAndView modelAndView, @PathVariable int id) {
//        productServiceImpl.removeProduct(id);
//        modelAndView.setViewName("redirect:/");
//        return modelAndView;
//    }

}
