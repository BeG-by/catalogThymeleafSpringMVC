package by.beg.catalog.service;

import by.beg.catalog.entity.Product;

import java.util.List;

public interface BasketOrderService {

    void addProduct(int userId, int productId);

    void removeProduct(int userId, int productId);

    List<Product> getProductList(int userId);

}
