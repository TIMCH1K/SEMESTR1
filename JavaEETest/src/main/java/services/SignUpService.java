package services;

import dto.SignUpForm;

import java.util.UUID;

public interface SignUpService {
    UUID singUp(SignUpForm form);
}
