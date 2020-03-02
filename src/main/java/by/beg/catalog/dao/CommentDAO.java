package by.beg.catalog.dao;

import by.beg.catalog.entity.Comment;

import java.util.List;

public interface CommentDAO {

    void addComment(Comment comment);

    List<Comment> getAllComments();

}
