package by.beg.catalog.service;

import by.beg.catalog.dao.BasketOrderDAO;
import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BasketOrderServiceImpl implements BasketOrderService {

    private BasketOrderDAO basketOrderDAO;

    @Autowired
    public BasketOrderServiceImpl(BasketOrderDAO basketOrderDAO) {
        this.basketOrderDAO = basketOrderDAO;
    }


    @Override
    public void addProduct(int userId, int productId) {
        basketOrderDAO.addProduct(userId, productId);
    }

    @Override
    public void removeProduct(int userId, int productId) {
        basketOrderDAO.removeProduct(userId, productId);
    }

    @Override
    public List<Product> getProductList(int userId) {
        return basketOrderDAO.getProductsByUserId(userId);
    }

    @Override
    public void doOrder(User user) {
        basketOrderDAO.doOrder(user);
    }
}
