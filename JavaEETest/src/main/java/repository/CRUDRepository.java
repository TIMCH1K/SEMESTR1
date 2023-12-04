package repository;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {
    void save(T enity);
    Optional<T> findByNickname(String nickname);
}
