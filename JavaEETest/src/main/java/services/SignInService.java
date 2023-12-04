package services;

import dto.SignInForm;

import java.util.UUID;

public interface SignInService {
    UUID singIn(SignInForm form);
}
