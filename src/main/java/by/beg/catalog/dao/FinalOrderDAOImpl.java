package by.beg.catalog.dao;

import by.beg.catalog.entity.FinalOrder;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FinalOrderDAOImpl implements FinalOrderDAO {

    private static Logger logger = LoggerFactory.getLogger(FinalOrderDAOImpl.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public FinalOrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<FinalOrder> getListFinalOrder() {
        Query<FinalOrder> finalOrders = sessionFactory.getCurrentSession().createQuery("FROM FinalOrder");
        return finalOrders.getResultList();
    }

    @Override
    public void removeOrder(int id) {
        FinalOrder finalOrder = sessionFactory.getCurrentSession().get(FinalOrder.class, (long) id);
        sessionFactory.getCurrentSession().delete(finalOrder);
        logger.info("Final order was removed:" + finalOrder);
    }
}
