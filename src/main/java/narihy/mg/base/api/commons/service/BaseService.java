package narihy.mg.base.api.commons.service;

import narihy.mg.base.api.commons.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public abstract class BaseService<T, ID> {

    // Le repository pour effectuer les opérations CRUD
    @Autowired
    protected JpaRepository<T, ID> repository;

    /**
     * Récupère une entité par son ID.
     * @param id L'ID de l'entité.
     * @return Optional<T> L'entité correspondante, si elle existe.
     */
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    /**
     * Récupère toutes les entités.
     * @return Iterable<T> Liste de toutes les entités.
     */
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    /**
     * Récupère une page d'entités en fonction des critères de pagination.
     * @param pageable Informations de pagination.
     * @return Page<T> Page d'entités.
     */
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Sauvegarde une entité. Si l'entité existe déjà, elle sera mise à jour.
     * @param entity L'entité à sauvegarder.
     * @return T L'entité sauvegardée.
     */
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Sauvegarde une liste d'entités en une seule opération.
     * @param entities Liste d'entités à sauvegarder.
     * @return Iterable<T> Liste des entités sauvegardées.
     */
    @Transactional
    public Iterable<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    /**
     * Supprime une entité par son ID. Si l'entité n'existe pas, une exception sera levée.
     * @param id L'ID de l'entité à supprimer.
     */
    @Transactional
    public void deleteById(ID id) {
        // Vérifie si l'entité existe avant de la supprimer.
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
        repository.deleteById(id);
    }

    /**
     * Vérifie si une entité existe avec un certain ID.
     * @param id L'ID de l'entité.
     * @return boolean true si l'entité existe, sinon false.
     */
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    /**
     * Récupère une entité par son ID. Si l'entité n'est pas trouvée, une exception sera levée.
     * @param id L'ID de l'entité.
     * @return T L'entité correspondante.
     * @throws EntityNotFoundException Si l'entité n'est pas trouvée.
     */
    public T getByIdOrThrow(ID id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found."));
    }


}
