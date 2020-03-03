package by.beg.catalog.service;

import by.beg.catalog.dao.BasketOrderDAO;
import by.beg.catalog.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasketOrderServiceImpl implements BasketOrderService {

    private BasketOrderDAO basketOrderDAO;

    @Autowired
    public BasketOrderServiceImpl(BasketOrderDAO basketOrderDAO) {
        this.basketOrderDAO = basketOrderDAO;
    }


    @Override
    @Transactional
    public void addProduct(int userId, int productId) {
        basketOrderDAO.addProduct(userId, productId);
    }

    @Override
    @Transactional
    public void removeProduct(int userId, int productId) {
        basketOrderDAO.removeProduct(userId, productId);
    }

    @Override
    @Transactional
    public List<Product> getProductList(int userId) {
        return basketOrderDAO.getProductsByUserId(userId);
    }
}
