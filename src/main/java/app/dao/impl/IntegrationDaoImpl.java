package app.dao.impl;

import app.entities.Integration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class IntegrationDaoImpl extends BasicCrudDaoImpl<Integration> {
}
