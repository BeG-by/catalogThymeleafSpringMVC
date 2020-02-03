package by.beg.catalog.controller;


import by.beg.catalog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
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


}
