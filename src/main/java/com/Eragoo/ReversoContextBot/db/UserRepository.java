package com.Eragoo.ReversoContextBot.db;

import java.sql.*;
import java.util.Optional;
import java.util.Properties;

import static com.Eragoo.ReversoContextBot.config.DbConfig.*;

public class UserRepository {

    public UserRepository() {
    }

    public void saveUser(UserAction userAction) {
        if (isNewUser(userAction)) {
            save(userAction);
        } else {
            update(userAction);
        }
    }

    private boolean isNewUser(UserAction userAction) {
        long i = count(userAction);
        return i == 0;
    }

    private void save(UserAction userAction) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user_action (chat_id, username, command, lang)" +
                    "VALUES (?, ?, ?, ?)"
            );

            statement.setLong(1, userAction.getChatId());
            statement.setString(2, userAction.getUsername());
            statement.setString(3, userAction.getCommand());
            statement.setString(4, userAction.getLang());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(UserAction userAction) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE user_action SET username = ?, command = ?, lang = ? WHERE chat_id = ?");

            statement.setString(1, userAction.getUsername());
            statement.setString(2, userAction.getCommand());
            statement.setString(3, userAction.getLang());
            statement.setLong(4, userAction.getChatId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> getLanguage(long chatId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT lang AS lang FROM user_action WHERE chat_id = ?");
            statement.setLong(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getString("lang"));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private long count(UserAction userAction) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM user_action WHERE chat_id = ?");
            statement.setLong(1, userAction.getChatId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", USR);
        properties.setProperty("password", PASS);

        return DriverManager.getConnection(URL, properties);
    }
}
