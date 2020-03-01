package by.beg.catalog.service;

import by.beg.catalog.dao.ProductDAO;
import by.beg.catalog.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    @Transactional
    public void createProduct(Product product) {
        productDAO.createProduct(product);
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    @Transactional
    public void removeProduct(int id) {
        productDAO.removeProduct(id);
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    @Transactional
    public List<Product> findProducts(String string) {
        return productDAO.findProducts(string);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    @Transactional
    public void orderById() {
        List<Product> productList = productDAO.getAllProducts();
        productList.sort(Comparator.comparingInt(Product::getId));
    }

    @Override
    @Transactional
    public void orderByName() {
        List<Product> productList = productDAO.getAllProducts();
        productList.sort((Product p1, Product p2) -> {
            if (p1.getName().compareTo(p2.getName()) != 0) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return p1.getId() - p2.getId();
            }
        });
    }

    @Override
    @Transactional
    public void orderByPrice() {
        List<Product> productList = productDAO.getAllProducts();
        productList.sort((Product p1, Product p2) -> {
            if (p1.getPrice() - p2.getPrice() != 0) {
                return p1.getPrice() - p2.getPrice();
            } else {
                return p1.getId() - p2.getId();
            }
        });
    }

    @Override
    @Transactional
    public void orderByType() {
        List<Product> productList = productDAO.getAllProducts();
        productList.sort((Product p1, Product p2) -> {
            if (p1.getType().getName().compareTo(p2.getType().getName()) != 0) {
                return p1.getType().getName().compareTo(p2.getType().getName());
            } else {
                return p1.getId() - p2.getId();
            }
        });
    }

}
