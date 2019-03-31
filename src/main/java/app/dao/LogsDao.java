package app.dao;

import app.entities.Logs;
import app.entities.namespace.LogsNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsDao {

    List<Logs> getLogFor(LogsNamespace logsNamespace);

    List<Logs> getAll();

    void save(Logs logs);

    void delete(Logs logs);
}
