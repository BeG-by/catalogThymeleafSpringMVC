package by.beg.catalog.service;

import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;

import java.util.List;

public interface BasketOrderService {

    boolean addProduct(int userId, int productId);

    void removeProduct(int userId, int productId);

    List<Product> getProductList(int userId);

    boolean doOrder(User user);

}
