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
public class BasketController {

    private BasketOrderService basketOrderService;

    @Autowired
    public BasketController(BasketOrderService basketOrderService) {
        this.basketOrderService = basketOrderService;
    }

    @GetMapping
    public ModelAndView getBasket(ModelAndView modelAndView) {
        modelAndView.setViewName("basket");
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addBasket(ModelAndView modelAndView, @PathVariable int id) {
        int userId = (int) modelAndView.getModelMap().getAttribute("userId");
        basketOrderService.addProduct(userId, id);

        modelAndView.addObject("added", true);
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

    @ModelAttribute
    public void addProductBasket(ModelAndView modelAndView, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        modelAndView.addObject("productBasket", basketOrderService.getProductList((int) currentUser.getId()));
        modelAndView.addObject("userId", (int) currentUser.getId());
    }


//
//    @GetMapping("/order")
//    public ModelAndView addBasket(ModelAndView modelAndView, HttpSession session) {
//
//        if (productBasket.isEmpty()) {
//            modelAndView.setViewName("basket");
//            return modelAndView;
//        }
//
//        User currentUser = (User) session.getAttribute("currentUser");
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Дата:' dd.MM.yyyy 'Время:' HH:mm:ss");
//        String time = simpleDateFormat.format(new Date());
//        orderList.add(new Order(time, currentUser, new ArrayList<>(productBasket)));
//        productBasket.clear();
//
//        modelAndView.addObject("isOrdered", true);
//        modelAndView.setViewName("forward:/");
//        return modelAndView;
//    }


}
