package app.dao.impl;

import app.entities.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ActivityDaoImpl extends BasicCrudDaoImpl<Activity> {
}
