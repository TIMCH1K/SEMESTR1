package models;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class User {
    private UUID id;
    private String nickname;
    private String password;
    private String email;
}