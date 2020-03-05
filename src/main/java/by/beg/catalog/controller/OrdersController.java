package by.beg.catalog.controller;

import by.beg.catalog.entity.FinalOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/order")
public class OrdersController {

    private ArrayList<FinalOrder> finalOrderList;

    public OrdersController(@Qualifier("orderList") ArrayList<FinalOrder> finalOrderList) {
        this.finalOrderList = finalOrderList;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getOrder(ModelAndView modelAndView) {
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/remove/{index}")
    public ModelAndView removeOrder(ModelAndView modelAndView, @PathVariable int index) {
        finalOrderList.remove(index);
        modelAndView.setViewName("orders");
        return modelAndView;
    }

}
