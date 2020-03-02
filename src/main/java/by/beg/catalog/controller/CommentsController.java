package by.beg.catalog.controller;

import by.beg.catalog.entity.Comment;
import by.beg.catalog.entity.User;
import by.beg.catalog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/comment")
public class CommentsController {

    private CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ModelAndView getComments(ModelAndView modelAndView) {
        modelAndView.addObject("comment", new Comment());
        modelAndView.setViewName("comments");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createComment(ModelAndView modelAndView, HttpSession session,
                                      @Valid @ModelAttribute Comment comment,
                                      BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("comments");
            return modelAndView;
        }

        User currentUser = (User) session.getAttribute("currentUser");
        commentService.addComment(comment, currentUser.getName());

        modelAndView.setViewName("redirect:/comment");
        return modelAndView;
    }

    @ModelAttribute
    public void addCommentsList(ModelAndView modelAndView) {
        modelAndView.addObject("commentsList", commentService.getAllComments());
    }

}
