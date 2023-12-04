package services;

import models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserFindByIdService {
    Optional<User> find(final UUID id);
}
