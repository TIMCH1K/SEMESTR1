package repository;

import models.Post;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

        void save(Post post) throws SQLException;

        Optional<Post> findById(UUID id) throws SQLException;
        boolean delete(UUID id) throws SQLException;
        boolean update(UUID id, Post post) throws SQLException;
}
