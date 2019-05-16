package app.dao.impl;

import app.entities.Settings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SettingsDaoImpl extends BasicCrudDaoImpl<Settings> {
}
