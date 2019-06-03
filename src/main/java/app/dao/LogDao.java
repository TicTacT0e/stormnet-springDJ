package app.dao;

import app.entities.Log;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LogDao extends BasicCrudDao<Log> {

    void createLog(List<Log> logs);

    void updateLog(List<Log> logs);

    List<Log> findByDay();

    List<Log> findByPeriod(Timestamp periodFrom, Timestamp periodTo);
}
