package repository.impl;

import models.Post;
import models.User;
import repository.PostRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PostRepositoryJDBCImpl implements PostRepository {
    private final Connection connection;
    private final String SQL_SAVE;
    private final String SQL_FIND_BY_ID;
    private final String SQL_DELETE;
    private final String SQL_UPDATE;
    private final String SQL_GET_SOME;
    private PreparedStatement savedPreparedStatement;
    private PreparedStatement findByIdPreparedStatement;
    private PreparedStatement getSomePreparedStatement;
    private PreparedStatement deletePreparedStatement;
    private PreparedStatement updatePreparedStatement;

    public PostRepositoryJDBCImpl(Connection connection) {
        this.connection = connection;
        //language=sql
        SQL_SAVE = "INSERT INTO posts (user_id, title, description)" + "VALUES (?,?,?)";
        SQL_FIND_BY_ID = "SELECT *  FROM posts where id = ?";
        SQL_GET_SOME = "SELECT * FROM posts ORDER BY publishing DESC LIMIT 10";
        SQL_DELETE = null;
        SQL_UPDATE = "UPDATE posts SET title = ?, description = ?)";
    }


    @Override
    public void save(Post post) throws SQLException {
        if (savedPreparedStatement == null) {
            try {
                savedPreparedStatement = connection.prepareStatement(SQL_SAVE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            savedPreparedStatement.setObject(1, post.getUser().getId());
            savedPreparedStatement.setObject(2, post.getTitle());
            savedPreparedStatement.setObject(3, post.getDescription());

            savedPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Post> findById(UUID id) throws SQLException {
        if (findByIdPreparedStatement == null) {
            try {
                findByIdPreparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Post resultPost = null;
        try {
            findByIdPreparedStatement.setObject(1, id);
            ResultSet resultSet = findByIdPreparedStatement.executeQuery();

            if (resultSet.next()) {
                UUID postId = resultSet.getObject("id", UUID.class);
                UUID userId = resultSet.getObject("user_id", UUID.class);
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date publishing = resultSet.getDate("publishing");

                User user = User.builder()
                        .id(id)
                        .build();
                resultPost = new Post(postId, user, title, description, publishing);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (resultPost == null) {
            return Optional.empty();
        }
        return Optional.of(resultPost);
    }

    @Override
    public boolean delete(UUID id) throws SQLException {
        if (deletePreparedStatement == null) {
            deletePreparedStatement = connection.prepareStatement(SQL_DELETE);
        }
        deletePreparedStatement.setObject(1, id);
        int rowsAffected = deletePreparedStatement.executeUpdate();
        return rowsAffected > 0;
    }

    @Override
    public boolean update(UUID id, Post post) throws SQLException {
        if (updatePreparedStatement == null) {
            updatePreparedStatement = connection.prepareStatement(SQL_UPDATE);
        }
        updatePreparedStatement.setString(1, post.getTitle());
        updatePreparedStatement.setString(2, post.getDescription());
        updatePreparedStatement.setObject(3, id);
        int rowsAffected = updatePreparedStatement.executeUpdate();
        return rowsAffected > 0;

    }
    public List<Post> getSome(int amount) throws SQLException {
        List<Post> result = new ArrayList<>();
        if (getSomePreparedStatement == null) {
            getSomePreparedStatement = connection.prepareStatement(SQL_GET_SOME);
        }
        try {
            ResultSet resultSet = getSomePreparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID postId = (UUID) resultSet.getObject("id");
                UUID userId = (UUID) resultSet.getObject("user_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date publishing = new Date(resultSet.getTimestamp("publishing").getTime());


                User user = User.builder()
                        .id(userId)
                        .build();

                // Используем этот объект User для создания объекта Post
                Post post = new Post(postId, user, title, description, publishing);
                result.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
