package by.beg.catalog.controller;

import by.beg.catalog.entity.User;
import by.beg.catalog.service.UserService;
import by.beg.catalog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userServiceImpl;

    @Autowired
    public UserController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/reg")
    public ModelAndView getRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("regUser", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/reg")
    public ModelAndView postRegistration(@Valid @ModelAttribute("regUser") User user, BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");

        } else if (userServiceImpl.registration(user)) {
            modelAndView.setViewName("redirect:/auth");
        } else {
            modelAndView.addObject("isExist", true);
            modelAndView.setViewName("registration");
        }

        return modelAndView;

    }


    @GetMapping("/auth")
    public ModelAndView getAuthorization(ModelAndView modelAndView) {
        modelAndView.setViewName("authorization");
        modelAndView.addObject("authUser", new User());
        return modelAndView;
    }

    @PostMapping("/auth")
    public ModelAndView postAuthorization(@ModelAttribute("authUser") User user, ModelAndView modelAndView, HttpSession session) {

        User originalUser = userServiceImpl.authorization(user);

        if (originalUser != null) {
            session.setAttribute("currentUser", originalUser);
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.addObject("isFail", true);
            modelAndView.setViewName("authorization");
        }

        return modelAndView;
    }

    @GetMapping("/out")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        session.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
