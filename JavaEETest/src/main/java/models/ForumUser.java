package models;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
@Setter
@EqualsAndHashCode
public class ForumUser {
    private int id;
    private String forumNickname;
    private int age;
    private String country;
    private String city;
    private int postCount;
    private int commentsCount;
    private boolean isAdmin;
    private boolean isBanned;
    private String userImage;

    public ForumUser(String forumNickname, int age, String country, String city, int postCount, int commentsCount, boolean isAdmin, boolean isBanned) {
        this.forumNickname = forumNickname;
        this.age = age;
        this.country = country;
        this.city = city;
        this.postCount = postCount;
        this.commentsCount = commentsCount;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }

    public ForumUser(int id, String forumNickname, int age, String country, String city, int postCount, int commentsCount, boolean isAdmin, boolean isBanned) {
        this.id = id;
        this.forumNickname = forumNickname;
        this.age = age;
        this.country = country;
        this.city = city;
        this.postCount = postCount;
        this.commentsCount = commentsCount;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }

    public ForumUser(String forumNickname, int age, String country, String city) {
        this.forumNickname = forumNickname;
        this.age = age;
        this.country = country;
        this.city = city;
    }

    public ForumUser() {
    }

    public ForumUser(int id, String forumNickname, int age, String country, String city, int postCount, int commentsCount, boolean isAdmin, boolean isBanned, String userImage) {
        this.id = id;
        this.forumNickname = forumNickname;
        this.age = age;
        this.country = country;
        this.city = city;
        this.postCount = postCount;
        this.commentsCount = commentsCount;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.userImage = userImage;
    }

    public int getUserId() {
        return id;
    }
}
