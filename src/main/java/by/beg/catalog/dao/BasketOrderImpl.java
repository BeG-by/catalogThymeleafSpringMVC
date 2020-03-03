package by.beg.catalog.dao;

import by.beg.catalog.entity.BasketOrder;
import by.beg.catalog.entity.Product;
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
        NativeQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO basket_order VALUES (null , ? , ?)");
        sqlQuery.setParameter(1, userId);
        sqlQuery.setParameter(2, productId);
        sqlQuery.executeUpdate();
        logger.info("BasketOrder was added: user_id(" + userId + "), product_id(" + productId + ")");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeProduct(int userId, int productId) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE id_user = :id_user AND id_product = :id_product");
        query.setParameter("id_user", userId);
        query.setParameter("id_product", productId);
        List<BasketOrder> resultList = query.getResultList();

        sessionFactory.getCurrentSession().delete(resultList.get(0));

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsByUserId(int userId) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM BasketOrder WHERE id_user = :id_user");
        query.setParameter("id_user", userId);

        List<BasketOrder> resultList = query.getResultList();
        List<Product> productList = new ArrayList<>();


        for (BasketOrder currentObject : resultList) {
            productList.add(currentObject.getProduct());
        }

//        System.out.println("productOrderList" + productList);

        return productList;
    }
}
