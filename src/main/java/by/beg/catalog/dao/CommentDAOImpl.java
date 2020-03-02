package by.beg.catalog.dao;

import by.beg.catalog.entity.Comment;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private static Logger logger = LoggerFactory.getLogger(CommentDAOImpl.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public CommentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().persist(comment);
        logger.info("Comment was added: " + comment);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> getAllComments() {
        return sessionFactory.getCurrentSession().createQuery("FROM Comment").list();
    }
}
