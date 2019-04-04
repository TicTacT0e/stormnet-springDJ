package app.dao;

import app.entities.Timesheet;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetDao {

    String  findById(int id);

    String findAll();

    void add(Timesheet timesheet);

    void delete(int id);

    void update(Timesheet timesheet);

}
