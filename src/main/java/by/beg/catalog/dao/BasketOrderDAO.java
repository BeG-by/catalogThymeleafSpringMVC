package by.beg.catalog.dao;

import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;

import java.util.List;

public interface BasketOrderDAO {

    boolean addProduct(int userId, int productId);

    void removeProduct(int userId, int productId);

    List<Product> getProductsByUserId(int userId);

    boolean doOrder(User user);

}
