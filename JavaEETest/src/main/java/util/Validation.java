package util;

import org.apache.commons.validator.routines.EmailValidator;

public class Validation {
    public static boolean isValid(String nickname, String email, String password) {
        boolean nicknameValid = nickname != null && !nickname.isEmpty();
        boolean emailValid = emailValidator(email);
        boolean passwordValid = passwordValidator(password);

        System.out.println(nicknameValid);
        System.out.println(emailValid);
        System.out.println(passwordValid);
        System.out.println(nicknameValid && emailValid && passwordValid);


        return nicknameValid && emailValid && passwordValid;
    }

    private static boolean emailValidator(String email) {
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);
    }

    private static boolean passwordValidator(String password) {
        if (password == null) {
            return false;
        }
        // (от 4 до 30 символов хотя бы один символ верхнего и нижнего регистра цифры и спецсимволы)
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]).{4,30}$";

        return password.matches(passwordPattern);
    }
}
