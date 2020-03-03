package by.beg.catalog.dao;

import by.beg.catalog.entity.Product;

import java.util.List;

public interface BasketOrderDAO {

    void addProduct(int userId, int productId);

    void removeProduct(int userId, int productId);

    List<Product> getProductsByUserId(int userId);

}
