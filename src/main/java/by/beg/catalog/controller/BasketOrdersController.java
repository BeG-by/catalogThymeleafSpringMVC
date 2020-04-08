package by.beg.catalog.controller;

import by.beg.catalog.entity.User;
import by.beg.catalog.service.BasketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/basket")
public class BasketOrdersController {

    private BasketOrderService basketOrderService;

    @Autowired
    public BasketOrdersController(BasketOrderService basketOrderService) {
        this.basketOrderService = basketOrderService;
    }

    @GetMapping
    public ModelAndView getProductBasket(ModelAndView modelAndView) {
        modelAndView.setViewName("basket");
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addProductBasket(ModelAndView modelAndView, @PathVariable int id) {
        int userId = (int) modelAndView.getModelMap().getAttribute("userId");

        if (basketOrderService.addProduct(userId, id)) {
            modelAndView.addObject("isAdded", 1);
        } else {
            modelAndView.addObject("isAdded", 0);
        }

        modelAndView.setViewName("forward:/");
        return modelAndView;

    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeBasketProduct(ModelAndView modelAndView, @PathVariable int id) {
        int userId = (int) modelAndView.getModelMap().getAttribute("userId");
        basketOrderService.removeProduct(userId, id);

        modelAndView.setViewName("redirect:/basket");
        return modelAndView;
    }


    @GetMapping("/order")
    public ModelAndView doOrder(ModelAndView modelAndView, HttpSession session) {

        User currentUser = (User) session.getAttribute("currentUser");

        if (basketOrderService.doOrder(currentUser)) {
            modelAndView.addObject("isOrdered", 1);
        } else {
            modelAndView.addObject("isOrdered", 0);
        }

        modelAndView.setViewName("forward:/");
        return modelAndView;
    }

    @ModelAttribute
    public void addProductBasket(ModelAndView modelAndView, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        modelAndView.addObject("productBasket", basketOrderService.getProductList((int) currentUser.getId()));
        modelAndView.addObject("userId", (int) currentUser.getId());
    }


}
