package narihy.mg.base.api.service;

import narihy.mg.base.api.commons.service.BaseService;
import narihy.mg.base.api.persistences.models.UserEntity;

import narihy.mg.base.api.commons.exception.EntityNotFoundException;
import narihy.mg.base.api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<UserEntity, Long> {

    private final List<UserEntity> users = new ArrayList<>();
    @Autowired
    private UserRepository userRepository;

    // Création d'un utilisateur
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    // Récupérer un utilisateur par son ID ou lever une exception s'il n'existe pas
    public UserEntity getByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    // Récupérer un utilisateur par son email
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }
}
