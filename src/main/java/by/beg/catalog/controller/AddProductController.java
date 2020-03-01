package by.beg.catalog.controller;

import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.ProductTypeEnum;
import by.beg.catalog.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/add")
public class AddProductController {

//    private ProductServiceImpl productServiceImpl;
//
//    public AddProductController(ProductServiceImpl productServiceImpl) {
//        this.productServiceImpl = productServiceImpl;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView createProduct(ModelAndView modelAndView) {
//        modelAndView.addObject("product", new Product());
//        modelAndView.setViewName("create");
//        return modelAndView;
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ModelAndView addProduct(ModelAndView modelAndView, @Valid @ModelAttribute Product product, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("create");
//        } else {
//            productServiceImpl.addProduct(product);
//            modelAndView.setViewName("redirect:/");
//        }
//
//        return modelAndView;
//    }
//
//    @ModelAttribute("types")
//    public ProductTypeEnum[] addAttributes(){
//        return ProductTypeEnum.values();
//    }

}
