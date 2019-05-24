package app.dao;

import app.entities.Log;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogDao extends BasicCrudDao<Log>{

    void createLog(List<Log> logs);

    List<Log> findByDay(); //использовать Period    ID

    List<Log> findByWeek();

    List<Log> findByPeriod(Date periodFrom, Date periodTo);
}
