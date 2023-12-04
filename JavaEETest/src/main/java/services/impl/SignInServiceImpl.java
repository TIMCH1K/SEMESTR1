package services.impl;

import dto.SignInForm;
import models.User;
import repository.UsersRepository;
import services.SignInService;
import util.security.PasswordHasher;

import java.util.Optional;
import java.util.UUID;

public class SignInServiceImpl implements SignInService {
    private UsersRepository repository;

    public SignInServiceImpl(UsersRepository repository){
        this.repository = repository;
    }

    @Override
    public UUID singIn(SignInForm form) {
        Optional<User> userOptional = repository.findByNickname(form.getNickname());
        if (!userOptional.isPresent()){
            System.out.println("service null 1");
            return null;
        }
        User user = userOptional.get();
        if (PasswordHasher.checkPassword(form.getPassword(), user.getPassword())){
            System.out.println("service not null 1");
            return user.getId();
        }
        System.out.println("service null 2");
        return null;
    }
}
