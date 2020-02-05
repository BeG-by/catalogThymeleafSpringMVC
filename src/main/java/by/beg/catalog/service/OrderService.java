package by.beg.catalog.service;

import by.beg.catalog.entity.Order;
import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class OrderService {

    private ArrayList<Order> orderList;

    @Autowired
    public OrderService(@Qualifier("orderList") ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }


    public void makeOder(User user , ArrayList<Product> products){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Дата:' dd.MM.yyyy 'Время:' HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        orderList.add(new Order(time, user , products));
    }
}
