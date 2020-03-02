package by.beg.catalog.dao;

import by.beg.catalog.entity.Product;

import java.util.List;

public interface ProductDAO {

    void createProduct(Product product);

    List<Product> getAllProducts();

    void removeProduct(int id);

    Product getProductById(int id);

    List<Product> findProducts(String string);

    void updateProduct(Product product);

}
