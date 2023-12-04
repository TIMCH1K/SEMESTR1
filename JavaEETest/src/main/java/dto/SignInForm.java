package dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInForm {
    private String nickname;
    private String password;
}
