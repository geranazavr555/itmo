package ru.itmo.sdcourse.hw4.repository.dao.impl;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import ru.itmo.sdcourse.hw4.model.Task;
import ru.itmo.sdcourse.hw4.model.TaskList;
import ru.itmo.sdcourse.hw4.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Properties;

public class UserDaoImplTest {
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(UserDaoImplTest.class.getResourceAsStream("/test.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String TEST_JDBC_URL = PROPERTIES.getProperty("spring.datasource.url");
    private static final String TEST_DIALECT = "org.hibernate.dialect.MariaDBDialect";

    private static MariaDbDataSource dataSource;

    @BeforeAll
    public static void prepare() {
        dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl(TEST_JDBC_URL);
            dataSource.setUser(PROPERTIES.getProperty("spring.datasource.username"));
            dataSource.setPassword(PROPERTIES.getProperty("spring.datasource.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        MetadataSources metadataSources = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting(AvailableSettings.DIALECT, TEST_DIALECT)
                        .applySetting(AvailableSettings.JAKARTA_JDBC_URL, TEST_JDBC_URL)
                        .applySetting(AvailableSettings.CONNECTION_PROVIDER, new ConnectionProvider() {
                            @Override
                            public Connection getConnection() throws SQLException {
                                return dataSource.getConnection();
                            }

                            @Override
                            public void closeConnection(Connection conn) throws SQLException {
                                conn.close();
                            }

                            @Override
                            public boolean supportsAggressiveRelease() {
                                return false;
                            }

                            @Override
                            public boolean isUnwrappableAs(Class<?> unwrapType) {
                                return false;
                            }

                            @Override
                            public <T> T unwrap(Class<T> unwrapType) {
                                return null;
                            }
                        }).build()
        );

        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Task.class);
        metadataSources.addAnnotatedClass(TaskList.class);

        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setFormat(false);
        schemaExport.setDelimiter(";");
        schemaExport.execute(
                EnumSet.of(TargetType.DATABASE), SchemaExport.Action.BOTH, metadataSources.buildMetadata());
    }

    @Test
    public void testUserInsert() {
        UserDaoImpl userDao = new UserDaoImpl(dataSource);
        User user = new User();
        user.setLogin("login");
        userDao.saveWithPasswordHashing(user, "password");

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
            resultSet.next();
            Assertions.assertTrue(resultSet.getInt(1) > 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUserAuth() {
        UserDaoImpl userDao = new UserDaoImpl(dataSource);
        User user = new User();
        user.setLogin("login1");
        userDao.saveWithPasswordHashing(user, "password");

        Optional<User> userOptional = userDao.findByLoginAndPassword("login1", "password");
        Assertions.assertTrue(userOptional.isPresent());

        userOptional = userDao.findByLoginAndPassword("login1", "dsfsdf");
        Assertions.assertTrue(userOptional.isEmpty());
    }
}
