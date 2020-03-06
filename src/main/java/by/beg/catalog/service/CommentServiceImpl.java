package by.beg.catalog.service;

import by.beg.catalog.dao.CommentDAO;
import by.beg.catalog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public void addComment(Comment comment, String name) {
        comment.setDate(new Date());
        comment.setName(name);
        commentDAO.addComment(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }
}
