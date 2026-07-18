package rest_api.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_api.ecommerce.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByUsername(String username);

    Optional<User> findFirstByUsername(String username);

    Optional<User> findFirstByToken(String token);
}
