package by.beg.catalog.service;

import by.beg.catalog.dao.FinalOrderDAO;
import by.beg.catalog.entity.FinalOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FinalOrderServiceImpl implements FinalOrderService {

    private FinalOrderDAO finalOrderDAO;

    @Autowired
    public FinalOrderServiceImpl(FinalOrderDAO finalOrderDAO) {
        this.finalOrderDAO = finalOrderDAO;
    }

    @Override
    public List<FinalOrder> getListFinalOrder() {
        return finalOrderDAO.getListFinalOrder();
    }

    @Override
    public void removeOrder(int id) {
        finalOrderDAO.removeOrder(id);
    }
}
