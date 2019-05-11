package app.dao;

import app.entities.Role;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleDao {

    public List<Role> findAll();

    public List<Role> findByCode(String code);

    public void deleteByCode(String code);

    public void create(Role entity);
}
