package by.beg.catalog.controller;

import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;
import by.beg.catalog.service.OrderService;
import by.beg.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private ArrayList<Product> productBasket;
    private ProductService productService;
    private OrderService orderService;

    @Autowired
    public BasketController(@Qualifier("productBasket") ArrayList<Product> productBasket, ProductService productService, OrderService orderService) {
        this.productBasket = productBasket;
        this.productService = productService;
        this.orderService = orderService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getBasket(ModelAndView modelAndView) {
        modelAndView.setViewName("basket");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/remove/{index}")
    public ModelAndView removeBasketProduct(ModelAndView modelAndView, @PathVariable int index) {
        productBasket.remove(index);
        modelAndView.setViewName("basket");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add/{id}")
    public ModelAndView addBasket(ModelAndView modelAndView, @PathVariable int id) {
        Product product = productService.searchProductById(id);
        productBasket.add(product);
        modelAndView.addObject("added", true);
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/order")
    public ModelAndView addBasket(ModelAndView modelAndView, HttpSession session) {

        if (productBasket.isEmpty()) {
            modelAndView.setViewName("basket");
            return modelAndView;
        }

        User currentUser = (User) session.getAttribute("currentUser");
        orderService.makeOder(currentUser, new ArrayList<>(productBasket));
        productBasket.clear();
        modelAndView.addObject("isOrdered", true);
        modelAndView.setViewName("main");
        return modelAndView;
    }


}
