package app.dao.impl;

import app.entities.Log;

public class LogsDaoImpl extends BasicCrudDaoImpl<Log> {

<<<<<<< HEAD
@Repository
@Transactional
public class  LogsDaoImpl implements BasicCrudDao<Log> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Log findById(int id) {
        Log logs = sessionFactory.getCurrentSession().get(Log.class, id);
        if (logs == null) {
            throw new EntityNotFoundException();
        }
        return logs;
    }

    @Override
    public List<Log> findAll() {
        Query query
                = sessionFactory.getCurrentSession()
                .createQuery("from app.entities.Log");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public void create(Log entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(Log entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void update(Log entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
=======
>>>>>>> master
}
