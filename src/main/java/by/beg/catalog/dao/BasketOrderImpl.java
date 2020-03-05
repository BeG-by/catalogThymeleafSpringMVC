package by.beg.catalog.dao;

import by.beg.catalog.entity.BasketOrder;
import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BasketOrderImpl implements BasketOrderDAO {

    private static Logger logger = LoggerFactory.getLogger(BasketOrderDAO.class.getSimpleName());
    private SessionFactory sessionFactory;

    @Autowired
    public BasketOrderImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(int userId, int productId) {
        User user = sessionFactory.getCurrentSession().load(User.class, (long) userId);
        Product product = sessionFactory.getCurrentSession().load(Product.class, productId);
        sessionFactory.getCurrentSession().persist(new BasketOrder(user, product));
        logger.info("BasketOrder was added: user_id(" + userId + "), product_id(" + productId + ")");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeProduct(int userId, int productId) { Query query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE user_id = :user_id AND product_id = :product_id");
        query.setParameter("user_id", userId);
        query.setParameter("product_id", productId);
        List<BasketOrder> resultList = query.getResultList();
        sessionFactory.getCurrentSession().delete(resultList.get(0));

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsByUserId(int userId) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE user_id = :user_id");
        query.setParameter("user_id", userId);

        List<BasketOrder> resultList = query.getResultList();
        List<Product> productList = new ArrayList<>();


        for (BasketOrder currentObject : resultList) {
            productList.add(currentObject.getProduct());
        }

        return productList;
    }
}
