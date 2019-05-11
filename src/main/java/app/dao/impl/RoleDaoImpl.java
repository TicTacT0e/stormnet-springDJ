package app.dao.impl;

import app.dao.RoleDao;
import app.entities.Role;
import app.exceptions.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() {
        Query query3
                = sessionFactory.getCurrentSession()
                .createQuery("from app.entities.Role");
        return query3.getResultList();
    }

    /*@Override
    public Role findByCode(String code) {
        Role role = sessionFactory.getCurrentSession()
                .get(Role.class, code);
        if (role == null) {
            throw new EntityNotFoundException();
        }
        return role;
    }

    @Override
    public void deleteByCode(String code) {
        sessionFactory.getCurrentSession()
                .delete(findByCode(code));
    }*/

    @Override
    public List<Role> findByCode(String code) {
        Query query4
                = sessionFactory.getCurrentSession()
                .createQuery("from Role where code = :paramName");
        query4.setParameter("paramName", code);
        return query4.getResultList();
    }

    @Override
    public void deleteByCode(String code) {
        Query query5
                = sessionFactory.getCurrentSession()
                .createQuery("delete Role where code = :paramName");
        query5.setParameter("paramName", code);
        int rows = query5.executeUpdate();
    }

    @Override
    public void create(Role entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }
}
