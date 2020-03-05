package by.beg.catalog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class FinalOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIME)
    private Date time;

    @ManyToOne(cascade = CascadeType.ALL)
    private BasketOrder basketOrder;

    public FinalOrder(Date time, BasketOrder basketOrder) {
        this.time = time;
        this.basketOrder = basketOrder;
    }


    public FinalOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BasketOrder getBasketOrder() {
        return basketOrder;
    }

    public void setBasketOrder(BasketOrder basketOrder) {
        this.basketOrder = basketOrder;
    }

    @Override
    public String toString() {
        return "FinalOrder{" +
                "id=" + id +
                ", time=" + time +
                ", basketOrder=" + basketOrder +
                '}';
    }
}
