package app.dao;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

public class MySqlDatabaseTester extends JdbcDatabaseTester {

    public MySqlDatabaseTester(String driverClass, String connectionUrl) throws ClassNotFoundException {
        super(driverClass, connectionUrl);
    }

    public MySqlDatabaseTester(String driverClass, String connectionUrl, String username, String password) throws ClassNotFoundException {
        super(driverClass, connectionUrl, username, password);
    }

    public MySqlDatabaseTester(String driverClass, String connectionUrl, String username, String password, String schema) throws ClassNotFoundException {
        super(driverClass, connectionUrl, username, password, schema);
    }

    @Override
    public IDatabaseConnection getConnection() throws Exception {
        IDatabaseConnection connection = super.getConnection();
        DatabaseConfig dbConfig = connection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new MySqlDataTypeFactory());
        dbConfig.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER,
                new MySqlMetadataHandler());
        return connection;
    }

    @Override
    public DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }
}
