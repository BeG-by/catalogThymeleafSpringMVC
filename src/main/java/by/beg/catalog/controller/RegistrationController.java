package by.beg.catalog.controller;

import by.beg.catalog.entity.User;
import by.beg.catalog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/reg")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("regUser", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postRegistration(@Valid @ModelAttribute("regUser") User user, BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");

        } else if (userService.tryRegistration(user)) {
            modelAndView.setViewName("redirect:/auth");
        } else {
            modelAndView.addObject("isExist", true);
            modelAndView.setViewName("registration");
        }

        System.out.println(userService.getUserList());

        return modelAndView;

    }


}
