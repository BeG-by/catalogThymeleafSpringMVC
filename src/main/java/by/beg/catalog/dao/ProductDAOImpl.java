package by.beg.catalog.dao;

import by.beg.catalog.entity.Product;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createProduct(Product product) {
        sessionFactory.getCurrentSession().persist(product);
        logger.info("Product was created: " + product);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAllProducts() {

        List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product ").list();

        if (products != null) {
            for (Product currentProduct : products) {
                logger.info("Product was found: " + currentProduct);
            }
        }

        return products;
    }

    @Override
    public void removeProduct(int id) {
        Product product = sessionFactory.getCurrentSession().load(Product.class, id);
        if (product != null) {
            sessionFactory.getCurrentSession().delete(product);
            logger.info("Product was deleted: " + product);
        }
    }

    @Override
    public Product getProductById(int id) {
        return sessionFactory.getCurrentSession().load(Product.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findProducts(String string) {

        String sql = "FROM Product WHERE name LIKE '" + string + "%'";
        List<Product> products = sessionFactory.getCurrentSession().createQuery(sql).list();

        if (products != null) {
            for (Product currentProduct : products) {
                logger.info("Product was found: " + currentProduct);
            }
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) {
        sessionFactory.getCurrentSession().update(product);
        logger.info("Product was updated: " + product);
    }
}