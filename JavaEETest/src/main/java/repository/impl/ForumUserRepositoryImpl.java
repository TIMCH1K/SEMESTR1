package repository.impl;

import models.ForumUser;
import repository.ForumUserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ForumUserRepositoryImpl implements ForumUserRepository {
    private DataSource dataSource;
    private static final String SQL_INSERT_INTO_FORUM_USERS = "INSERT INTO forum_users (forum_nickname, age, country, city, post_count, comments_count, is_admin, is_banned) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_FROM_FORUM_USERS_BY_NICKNAME = "SELECT * FROM forum_users WHERE forum_nickname = ?";
    private static final String UPDATE_ADMIN_RIGHTS_TRUE = "UPDATE forum_users SET is_admin = true WHERE id = ?";
    private static final String UPDATE_ADMIN_RIGHTS_FALSE = "UPDATE forum_users SET is_admin = false WHERE id = ?";
    private static final String UPDATE_BAN_STATUS_TRUE = "UPDATE forum_users SET is_banned = true WHERE id = ?";
    private static final String UPDATE_BAN_STATUS_FALSE = "UPDATE forum_users SET is_banned = false WHERE id = ?";
    private static final String SQL_UPDATE_FORUM_USER = "UPDATE forum_users SET forum_nickname = ?, age = ?, country = ?, city = ?, user_image = ? WHERE id = ?";


    public ForumUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void save(ForumUser forumUser) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_FORUM_USERS)) {
            preparedStatement.setString(1, forumUser.getForumNickname());
            preparedStatement.setInt(2, forumUser.getAge());
            preparedStatement.setString(3, forumUser.getCountry());
            preparedStatement.setString(4, forumUser.getCity());
            preparedStatement.setInt(5, forumUser.getPostCount());
            preparedStatement.setInt(6, forumUser.getCommentsCount());
            preparedStatement.setBoolean(7, forumUser.isAdmin());
            preparedStatement.setBoolean(8, forumUser.isBanned());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ForumUser> findByNickname(String nickname) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_FORUM_USERS_BY_NICKNAME)) {
            preparedStatement.setString(1, nickname);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ForumUser forumUser = new ForumUser(
                            resultSet.getString("forum_nickname"),
                            resultSet.getInt("age"),
                            resultSet.getString("country"),
                            resultSet.getString("city"),
                            resultSet.getInt("post_count"),
                            resultSet.getInt("comments_count"),
                            resultSet.getBoolean("is_admin"),
                            resultSet.getBoolean("is_banned")
                    );
                    return Optional.of(forumUser);
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public boolean grantAdminRights(int userId) {


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_RIGHTS_TRUE)) {
            preparedStatement.setInt(1, userId);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean revokeAdminRights(int userId) {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_RIGHTS_FALSE)) {
                statement.setInt(1, userId);
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error revoking admin rights for user with ID " + userId, e);
        }
    }


    @Override
    public boolean banUser(int userId) {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_BAN_STATUS_TRUE)) {
                statement.setInt(1, userId);
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error banning user with ID " + userId, e);
        }
    }

    @Override
    public boolean unbanUser(int userId) {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_BAN_STATUS_FALSE)) {
                statement.setInt(1, userId);
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error unbanning user with ID " + userId, e);
        }
    }
    @Override
    public Optional<ForumUser> findByUserId(int userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM forum_users WHERE id = ?")) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ForumUser forumUser = new ForumUser(
                            resultSet.getInt("id"),
                            resultSet.getString("forum_nickname"),
                            resultSet.getInt("age"),
                            resultSet.getString("country"),
                            resultSet.getString("city"),
                            resultSet.getInt("post_count"),
                            resultSet.getInt("comments_count"),
                            resultSet.getBoolean("is_admin"),
                            resultSet.getBoolean("is_banned")
                    );
                    return Optional.of(forumUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public void update(ForumUser user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_FORUM_USER)) {

            preparedStatement.setString(1, user.getForumNickname());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getUserImage());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
