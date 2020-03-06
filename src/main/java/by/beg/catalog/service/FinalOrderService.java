package by.beg.catalog.service;

import by.beg.catalog.entity.FinalOrder;

import java.util.List;

public interface FinalOrderService {

    List<FinalOrder> getListFinalOrder();

    void removeOrder(int id);
}
