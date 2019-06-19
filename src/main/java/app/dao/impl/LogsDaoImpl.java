package app.dao.impl;

import app.dao.LogDao;
import app.entities.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class LogsDaoImpl extends BasicCrudDaoImpl<Log> implements LogDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final String FIND_BY_DAY = "FROM Log WHERE "
            + "date > current_date()";
    private static final String FIND_BY_PERIOD = "FROM Log WHERE date"
            + " BETWEEN :startDate AND :endDate";

    @Override
    public void createLog(List<Log> logs) {
        Session session = sessionFactory.getCurrentSession();
        for (Log entity : logs) {
            session.save(entity);
        }
    }

    @Override
    public void updateLog(List<Log> logs) {
        Session session = sessionFactory.getCurrentSession();
        for (Log entity : logs) {
            session.update(entity);
        }
    }

    @Override
    public List<Log> findByDay() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_DAY);
        return query.getResultList();
    }

    @Override
    public List<Log> findByPeriod(Timestamp periodFrom, Timestamp periodTo) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery(FIND_BY_PERIOD);
        query.setParameter("startDate", periodFrom);
        query.setParameter("endDate", periodTo);
        return query.getResultList();
    }
}
