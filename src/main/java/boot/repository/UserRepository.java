package boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import boot.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String login);
}
