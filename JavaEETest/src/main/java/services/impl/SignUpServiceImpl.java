package services.impl;

import dto.SignUpForm;
import models.User;
import repository.UsersRepository;
import services.SignUpService;
import util.security.PasswordHasher;

import java.util.UUID;

public class SignUpServiceImpl implements SignUpService {
    private UsersRepository repository;

    public SignUpServiceImpl(UsersRepository repository){
        this.repository = repository;
    }

    @Override
    public UUID singUp(SignUpForm form) {
        if (repository.findByEmail(form.getEmail()).isPresent()){
            return null;
        }
        User user = User.builder()
                .nickname(form.getNickname())
                .email(form.getEmail())
                .password(PasswordHasher.hashPassword(form.getPassword()))
                .build();
        repository.save(user);


        return repository.findByEmail(form.getEmail()).get().getId();
    }
}
