package by.beg.catalog.controller;

import by.beg.catalog.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getOrder(ModelAndView modelAndView) {
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/remove/{index}")
    public ModelAndView removeOrder(ModelAndView modelAndView, @PathVariable int index) {
        orderService.getOrderList().remove(index);
        modelAndView.setViewName("orders");
        return modelAndView;
    }

}
