package rest_api.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_api.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
}
