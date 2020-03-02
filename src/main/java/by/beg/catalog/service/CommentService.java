package by.beg.catalog.service;

import by.beg.catalog.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment , String name);

    List<Comment> getAllComments();
}
