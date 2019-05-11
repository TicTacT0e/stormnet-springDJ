package app.dao.impl;

import app.dao.LogsDao;
import app.entities.Logs;
import app.entities.namespace.LogsNamespace;
import app.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@Repository
public class LogsDaoImpl implements LogsDao {

    private static List<Logs> logsList = new LinkedList<>();

    @Override
    public synchronized List<Logs> getLogFor(LogsNamespace logsNamespace) {
        Date boundaryDate = logsNamespace.getBoundaryDate();
        LinkedList<Logs> resultLogsList = new LinkedList<>();

        ListIterator<Logs> logsIterator
                = logsList.listIterator(logsList.size());
        Logs previous;
        while (logsIterator.hasPrevious()) {
            previous = logsIterator.previous();
            if (previous.getDate().before(boundaryDate)) {
                break;
            }
            resultLogsList.add(previous);
        }
        return resultLogsList;
    }

    @Override
    public synchronized List<Logs> getAll() {
        return logsList;
    }

    @Override
    public synchronized void save(Logs logs) {
        logsList.add(logs);
    }

    @Override
    public synchronized void delete(Logs retiringLogs) {
        if (!logsList.contains(retiringLogs)) {
            throw new EntityNotFoundException();
        }
        logsList.remove(retiringLogs);
    }

}
