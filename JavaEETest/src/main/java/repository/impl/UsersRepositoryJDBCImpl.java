package repository.impl;

import models.User;
import repository.UsersRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsersRepositoryJDBCImpl implements UsersRepository {

    private static final String SQL_SELECT_ALL_FROM_USER = "select * from form_users";
    private static final String SQL_INSERT_INTO_USER = "insert into form_users(nickname, email, password) values (?,?,?) ";
    private static final String SQL_SELECT_ALL_BY_EMAIL = "select * from form_users where email = ?";
    private static final String SQL_SELECT_USER_BY_NICKNAME = "select * from form_users where nickname = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM form_users WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM form_users WHERE id = ?";
    private DataSource dataSource;

    public UsersRepositoryJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void save(User user) {
        try (Connection connection = dataSource.getConnection()){
            String SQL_INSERT_USER_INTO_USER = "INSERT INTO form_users (nickname, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER_INTO_USER);

            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при сохранении данных в базу данных" + e);
        }
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        System.out.println("Attempting to find user by nickname: " + nickname);
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_NICKNAME)) {
            preparedStatement.setString(1, nickname);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = User.builder()
                        .id((UUID) resultSet.getObject("id"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .nickname(resultSet.getString("nickname"))
                        .build();
                System.out.println("User found: " + user.getNickname());
                return Optional.of(user);
            } else {
                System.out.println("No user found with nickname: " + nickname);
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("SQLException occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<User> findAllByEmail(String email){
        try(Connection connection = dataSource.getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_USER);
            List<User> result = new ArrayList<>();

            while(resultSet.next()) {
                User user = User.builder()
                        .id((UUID) resultSet.getObject("id"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .nickname(resultSet.getString("nickname"))
                        .build();
                if(user.getEmail().equals(email)) {
                    result.add(user);
                }
                if(result.isEmpty()){
                    throw new IllegalStateException("По введенному запросу ничего не найдено...");
                }
            }return result;
        } catch(SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = User.builder()
                        .id((UUID) resultSet.getObject("id"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .nickname(resultSet.getString("nickname"))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(UUID id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = User.builder()
                        .id((UUID) resultSet.getObject("id"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .nickname(resultSet.getString("nickname"))
                        .build();
                System.out.println(user.getId());
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}

