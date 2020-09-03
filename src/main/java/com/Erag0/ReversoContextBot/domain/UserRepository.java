package com.Erag0.ReversoContextBot.domain;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Properties;

public class UserRepository {

    public UserRepository() {
    }

    public void save(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (chat_id, username, command, language)" +
                    "VALUES (?, ?, ?, ?)");

            statement.setLong(1, user.getChatId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getCommand());
            statement.setString(4, user.getLanguage());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Users SET username = ?, command = ?, language = ? WHERE chat_id = ?");

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getCommand());
            statement.setString(3, user.getLanguage());
            statement.setLong(4, user.getChatId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLanguage(long chatId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT language AS lang FROM Users WHERE chat_id = ?");
            statement.setLong(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getString("lang");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public long count(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM Users WHERE chat_id = ?");
            statement.setLong(1, user.getChatId());
            ResultSet resultSet = statement.executeQuery();
            return (long) resultSet.getFloat("count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        return JDBC.createConnection(DatabaseProperties.URL, properties);
    }
}
