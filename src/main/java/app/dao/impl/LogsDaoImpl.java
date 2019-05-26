package app.dao.impl;

import app.dao.LogDao;
import app.entities.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class LogsDaoImpl extends BasicCrudDaoImpl<Log> implements LogDao {

    @Autowired
    private SessionFactory sessionFactory;


    //private static final String FIND_BY_DAY = " FROM Log WHERE date = CURRENT_DATE()";
   // private static final String FIND_BY_WEEK = "select * FROM Logs WHERE date BETWEEN (CURDATE() - INTERVAL 1 WEEK - INTERVAL WEEKDAY(CURDATE()) DAY)  AND(CURDATE() - INTERVAL WEEKDAY(CURDATE())\n" +
   //         " DAY -INTERVAL 1 SECOND)GROUP BY date";

    private static final String FIND_BY_PERIOD = "FROM Log WHERE date BETWEEN :startDate AND :EndDate";

    @Override
    public void createLog(List<Log> logs) {
        Session session = sessionFactory.getCurrentSession();
        for (Log entity : logs) {
            session.save(entity);
        }
    }

    //SELECT * FROM orders WHERE MONTHNAME(order_delivery) = 'April'
    //mysql> SELECT * FROM orders WHERE order_delivery BETWEEN '2018-05-01' AND '2018-06-01';

    @Override
    public List<Log> findByDay() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_PERIOD);
        return query.getResultList();
    }

    @Override
    public List<Log> findByWeek() {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery(FIND_BY_PERIOD);
        return query.getResultList();

    }

    @Override
    public List<Log> findByPeriod(Date periodFrom, Date periodTo) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_PERIOD);
        query.setParameter("startDate", periodFrom);
        query.setParameter("EndDate", periodTo);
        return query.getResultList();
    }

        /*String test = String.valueOf(timestamp);
        test = test.substring(0,7);
        test = test + "-%";
        Query query = session.createQuery("from ProductEntity where date like :datq");
        query.setString("datq",test);
        resultList = query.list();
        return logs;    }*/
}
