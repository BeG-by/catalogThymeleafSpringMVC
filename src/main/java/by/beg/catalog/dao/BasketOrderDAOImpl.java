package by.beg.catalog.dao;

import by.beg.catalog.entity.BasketOrder;
import by.beg.catalog.entity.FinalOrder;
import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BasketOrderDAOImpl implements BasketOrderDAO {

    private static Logger logger = LoggerFactory.getLogger(BasketOrderDAO.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public BasketOrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addProduct(int userId, int productId) {
        User user = sessionFactory.getCurrentSession().load(User.class, (long) userId);
        Product product = sessionFactory.getCurrentSession().load(Product.class, (long) productId);

        Query<BasketOrderDAO> query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE user = :user AND product = :product");
        query.setParameter("user", user);
        query.setParameter("product", product);

        if (query.getResultList().isEmpty()) {
            sessionFactory.getCurrentSession().persist(new BasketOrder(user, product));
            logger.info("BasketOrder was added: user_id(" + userId + "), product_id(" + productId + ")");
            return true;
        }

        return false;

    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeProduct(int userId, int productId) {
        User user = sessionFactory.getCurrentSession().load(User.class, (long) userId);
        Product product = sessionFactory.getCurrentSession().load(Product.class, (long) productId);

        Query<BasketOrder> query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE user = :user AND product = :product");
        query.setParameter("user", user);
        query.setParameter("product", product);
        List<BasketOrder> resultList = query.getResultList();
        sessionFactory.getCurrentSession().delete(resultList.get(0));

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsByUserId(int userId) {
        User user = sessionFactory.getCurrentSession().load(User.class, (long) userId);
        Query<BasketOrder> query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE user = :user");
        query.setParameter("user", user);

        List<BasketOrder> resultList = query.getResultList();
        List<Product> productList = new ArrayList<>();


        for (BasketOrder currentObject : resultList) {
            productList.add(currentObject.getProduct());
        }

        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean doOrder(User user) {

        Query<FinalOrder> checkOrder = sessionFactory.getCurrentSession().createQuery("FROM FinalOrder WHERE user = :user").setParameter("user", user);

        if (!checkOrder.getResultList().isEmpty()) {
            return false;
        }


        Query<BasketOrder> query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE user = :user").setParameter("user", user);
        List<BasketOrder> resultList = query.getResultList();
        Date date = new Date();

        List<Product> productList = new ArrayList<>();

        for (BasketOrder currentObject : resultList) {
            productList.add(currentObject.getProduct());
        }

        sessionFactory.getCurrentSession().save(new FinalOrder(date, user, productList));
        sessionFactory.getCurrentSession().createQuery("DELETE FROM BasketOrder WHERE user = :user").setParameter("user", user).executeUpdate();

        return true;
    }
}
