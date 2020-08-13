package com.Erag0.ReversoContextBot.domain;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Properties;

public class DbClass {
    private static DbClass dbClass = new DbClass();

    private DbClass() {
    }

    public static DbClass getInstance() {
        return dbClass;
    }

    public void AddUser(long chat_id, String username, String command, String language) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (chat_id, username, command, language)" +
                    "VALUES (?, ?, ?, ?)");

            statement.setLong(1, chat_id);
            statement.setString(2, username);
            statement.setString(3, command);
            statement.setString(4, language);

            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateLang(long chat_id, String language) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Users SET language = ? WHERE chat_id = ?");
            statement.setString(1, language);
            statement.setLong(2, chat_id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String RestoreLang(long chat_id) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT language AS lang FROM Users WHERE chat_id = ?");
            statement.setLong(1, chat_id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getString("lang");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public long Count(long chat_id) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM Users WHERE chat_id = ?");
            statement.setLong(1, chat_id);
            ResultSet resultSet = statement.executeQuery();
            return (long) resultSet.getFloat("count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        return JDBC.createConnection(DbConfig.URL, properties);
    }
}
