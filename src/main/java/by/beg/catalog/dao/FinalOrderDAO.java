package by.beg.catalog.dao;

import by.beg.catalog.entity.FinalOrder;

import java.util.List;

public interface FinalOrderDAO {

    List<FinalOrder> getListFinalOrder();

    void removeOrder(int id);

}
