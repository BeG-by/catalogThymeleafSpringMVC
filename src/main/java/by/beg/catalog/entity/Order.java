package by.beg.catalog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Order implements Serializable {

    private String time;
    private User user;
    private ArrayList<Product> orderProducts;

    public Order(String time, User user, ArrayList<Product> orderProducts) {
        this.time = time;
        this.user = user;
        this.orderProducts = orderProducts;
    }

    public Order() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public String toString() {
        return "Order{" +
                "time='" + time + '\'' +
                ", user=" + user +
                ", orderProducts=" + orderProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return time.equals(order.time) &&
                user.equals(order.user) &&
                orderProducts.equals(order.orderProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, user, orderProducts);
    }

}
