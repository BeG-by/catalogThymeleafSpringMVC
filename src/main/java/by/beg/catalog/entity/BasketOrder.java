package by.beg.catalog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@Table(name = "basket_order")
public class BasketOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_basket")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name = "id_product")
    private Product product;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<FinalOrder> finalOrderList;

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

//    public List<FinalOrder> getFinalOrderList() {
//        return finalOrderList;
//    }
//
//    public void setFinalOrderList(List<FinalOrder> finalOrderList) {
//        this.finalOrderList = finalOrderList;
//    }

    @Override
    public String toString() {
        return "BasketOrder{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
