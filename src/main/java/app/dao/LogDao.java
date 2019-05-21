package app.dao;

import app.entities.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDao {

    void createLog(List<Log> logs);

    List<Log> findByDay(); //использовать Period    ID

    List<Log> findByWeek();

    List<Log> findByPeriod(int periodFrom,int periodTo);
}