package repository;

import models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends CRUDRepository<User> {

    List findAllByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);
}
