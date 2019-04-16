package app.dao;

import app.entities.Logs;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogsDao {

    //List<Logs> getLogFor(LogsNamespace logsNamespace);

    List<Logs> getAll(Date from, Date to);

    void save(Logs logs);

    void delete(int id);
}
