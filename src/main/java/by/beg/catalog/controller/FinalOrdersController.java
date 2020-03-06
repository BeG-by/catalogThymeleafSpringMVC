package by.beg.catalog.controller;

import by.beg.catalog.service.FinalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/finalOrder")
public class FinalOrdersController {

    private FinalOrderService finalOrderService;

    @Autowired
    public FinalOrdersController(FinalOrderService finalOrderService) {
        this.finalOrderService = finalOrderService;
    }

    @GetMapping
    public ModelAndView getOrder(ModelAndView modelAndView) {
        modelAndView.addObject("finalOrderList" , finalOrderService.getListFinalOrder());
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public ModelAndView removeOrder(ModelAndView modelAndView, @PathVariable int id) {
        finalOrderService.removeOrder(id);
        modelAndView.setViewName("redirect:/finalOrder");
        return modelAndView;
    }

}
