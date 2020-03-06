package by.beg.catalog.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "basket_order")
public class BasketOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Product product;

    public BasketOrder(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public BasketOrder() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public String toString() {
        return "BasketOrder{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
