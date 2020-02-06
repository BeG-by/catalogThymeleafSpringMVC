package by.beg.catalog.controller;

import by.beg.catalog.entity.User;
import by.beg.catalog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    private UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAuthorization(ModelAndView modelAndView) {
        modelAndView.setViewName("authorization");
        modelAndView.addObject("authUser", new User());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postAuthorization(@ModelAttribute("authUser") User user, ModelAndView modelAndView, HttpSession session) {

        User originalUser = userService.tryAuthorization(user);

        if (originalUser != null) {
            session.setAttribute("currentUser", originalUser);
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.addObject("isFail",true);
            modelAndView.setViewName("authorization");
        }

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET , path = "/out")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session){
        session.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
