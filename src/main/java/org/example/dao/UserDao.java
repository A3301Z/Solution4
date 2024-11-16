package org.example.dao;

import org.example.entity.User;
import org.example.sql_queries.UserCrudQueries;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try {
            Connection connection = connect(dataSource);
            PreparedStatement statement = connection.prepareStatement(UserCrudQueries.FIND_ALL.getQuery());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(new User.builder()
                        .id(UUID.fromString(resultSet.getString("id")))
                        .username(resultSet.getString("username"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при попытке получения списка пользователей");
        }

        return users;
    }

    public User findById(UUID id) {
        try {
            Connection connection = connect(dataSource);
            PreparedStatement statement = connection.prepareStatement(UserCrudQueries.FIND_BY_ID.getQuery());
            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User.builder()
                        .id(UUID.fromString(resultSet.getString("id")))
                        .username(resultSet.getString("username"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при попытке получить пользователя: ", e);
        }
        return null;
    }

    public void save(String username) {
        try {
            Connection connection = connect(dataSource);

            PreparedStatement statement = connection.prepareStatement(UserCrudQueries.SAVE.getQuery());

            statement.setObject(1, UUID.randomUUID());
            statement.setString(2, username);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID id) {
        try {
            Connection connection = connect(dataSource);
            PreparedStatement statement = connection.prepareStatement(UserCrudQueries.DELETE.getQuery());

            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection connect(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к ресурсу: ", e);
        }
    }
}
