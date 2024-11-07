package narihy.mg.base.api.repository.user;
import narihy.mg.base.api.persistences.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}