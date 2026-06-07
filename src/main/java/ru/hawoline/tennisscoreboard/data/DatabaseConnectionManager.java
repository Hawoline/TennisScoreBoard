package ru.hawoline.tennisscoreboard.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final HikariDataSource HIKARI_DATA_SOURCE;

    static {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:h2:mem:default");
        hikariConfig.setDriverClassName("org.h2.Driver");

        HIKARI_DATA_SOURCE = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() throws SQLException {
        return HIKARI_DATA_SOURCE.getConnection();
    }
}
