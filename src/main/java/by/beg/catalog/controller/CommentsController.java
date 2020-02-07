package by.beg.catalog.controller;

import by.beg.catalog.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Controller
@RequestMapping("/comment")
public class CommentsController {

    private ArrayList<String> commentsList;

    public CommentsController(@Qualifier("commentsList") ArrayList<String> commentsList) {
        this.commentsList = commentsList;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getComments(ModelAndView modelAndView) {
        modelAndView.setViewName("comments");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addGenerallyComment(ModelAndView modelAndView, HttpSession session, @RequestParam String comment) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Дата:' dd.MM.yyyy 'Время:' HH:mm:ss");
        String time = simpleDateFormat.format(new Date());

        User currentUser = (User) session.getAttribute("currentUser");
        comment = currentUser.getName() + " " + time + "\n" + comment;
        commentsList.add(comment);

        modelAndView.setViewName("comments");
        return modelAndView;
    }


}
