package dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpForm {
    private String nickname;
    private String password;
    private String email;
}
