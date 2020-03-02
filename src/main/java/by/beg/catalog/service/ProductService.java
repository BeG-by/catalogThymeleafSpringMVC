package by.beg.catalog.service;

import by.beg.catalog.entity.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);

    List<Product> getAllProducts();

    void removeProduct(int id);

    Product getProductById(int id);

    List<Product> findProducts(String string);

    void updateProduct(Product product);

    void orderById(List<Product> productList);

    void orderByName(List<Product> productList);

    void orderByPrice(List<Product> productList);

    void orderByType(List<Product> productList);
}
