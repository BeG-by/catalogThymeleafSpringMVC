package by.beg.catalog.controller;

import by.beg.catalog.entity.Order;
import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;
import by.beg.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private ArrayList<Product> productBasket;
    private ProductService productService;
    private ArrayList<Order> orderList;

    @Autowired
    public BasketController(@Qualifier("productBasket") ArrayList<Product> productBasket,
                            @Qualifier("orderList") ArrayList<Order> orderList,
                            ProductService productService) {
        this.productBasket = productBasket;
        this.productService = productService;
        this.orderList = orderList;
    }


    @GetMapping
    public ModelAndView getBasket(ModelAndView modelAndView) {
        modelAndView.setViewName("basket");
        return modelAndView;
    }

    @GetMapping("/remove/{index}")
    public ModelAndView removeBasketProduct(ModelAndView modelAndView, @PathVariable int index) {
        productBasket.remove(index);
        modelAndView.setViewName("basket");
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addBasket(ModelAndView modelAndView, @PathVariable int id) {
        Product product = productService.getProductById(id);
        productBasket.add(product);
        modelAndView.addObject("added", true);
        modelAndView.setViewName("forward:/");
        return modelAndView;
    }

    @GetMapping("/order")
    public ModelAndView addBasket(ModelAndView modelAndView, HttpSession session) {

        if (productBasket.isEmpty()) {
            modelAndView.setViewName("basket");
            return modelAndView;
        }

        User currentUser = (User) session.getAttribute("currentUser");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Дата:' dd.MM.yyyy 'Время:' HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        orderList.add(new Order(time, currentUser, new ArrayList<>(productBasket)));
        productBasket.clear();

        modelAndView.addObject("isOrdered", true);
        modelAndView.setViewName("forward:/");
        return modelAndView;
    }


}
