package app.dao;

import app.entities.Timesheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimesheetDao {

    Timesheet findById(int id);

    List<Timesheet> findAll();

    void add(Timesheet timesheet);

    void delete(int id);

    void update(Timesheet timesheet);

}
