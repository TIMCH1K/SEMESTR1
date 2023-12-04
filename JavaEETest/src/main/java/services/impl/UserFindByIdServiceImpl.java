package services.impl;

import models.User;
import repository.UsersRepository;
import services.UserFindByIdService;

import java.util.Optional;
import java.util.UUID;

public class UserFindByIdServiceImpl implements UserFindByIdService {
    private UsersRepository repository;

    public UserFindByIdServiceImpl(UsersRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<User> find(final UUID id) {
        return repository.findById(id);
    }
}
