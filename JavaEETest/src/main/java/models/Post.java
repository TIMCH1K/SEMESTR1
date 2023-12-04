package models;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private UUID id;
    private User user;
    private String  title;
    private String description;
    private Date publishingTime;
}
