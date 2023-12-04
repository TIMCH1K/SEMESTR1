package repository;

import models.ForumUser;

import java.util.Optional;

public interface ForumUserRepository extends CRUDRepository<ForumUser>{
    void save(ForumUser forumUser);

    boolean grantAdminRights(int userId);
    boolean revokeAdminRights(int userId);
    boolean banUser(int userId);
    boolean unbanUser(int userId);
    void update (ForumUser user);

    Optional<ForumUser> findByUserId(int id);
}
