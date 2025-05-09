package M1S11.repositories;

import M1S11.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
