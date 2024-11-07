package narihy.mg.base.api.commons.controller;

import narihy.mg.base.api.commons.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
public abstract class BaseController<T, ID> {


    // La dépendance de baseService qui est un service générique
    @Autowired
    protected BaseService<T, ID> baseService;

    // Constructeur pour l'injection de BaseService
    public BaseController(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    // Méthode pour récupérer un élément par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<T>> getById(@PathVariable ID id, @RequestParam Optional<String> fields) {
        Optional<T> entity = baseService.findById(id);

        if (entity.isPresent()) {
            T data = entity.get();

            // Si un paramètre 'fields' est passé, nous ne retournons que les champs demandés
            if (fields.isPresent()) {
                data = filterFields(data, fields.get());
            }

            return ResponseEntity.ok(ApiResponse.success(data));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Entity not found"));
        }
    }

    // Méthode pour récupérer tous les éléments avec pagination
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<T>>> getAllPaginated(Pageable pageable, @RequestParam Optional<String> fields) {
        Page<T> resultPage = baseService.findAll(pageable);

        // Ajouter des en-têtes pour la pagination
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(resultPage.getTotalElements()));
        responseHeaders.set("x-result-count", String.valueOf(resultPage.getNumberOfElements()));

        // Si un paramètre 'fields' est passé, nous filtrons les résultats
        if (fields.isPresent()) {
            resultPage = resultPage.map(entity -> filterFields(entity, fields.get()));
        }

        return ResponseEntity.ok().headers(responseHeaders).body(ApiResponse.success(resultPage));
    }

    // Méthode pour enregistrer un nouvel élément
    @PostMapping
    public ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody T entity) {
        T savedEntity = baseService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(savedEntity));
    }

    // Méthode pour supprimer un élément par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable ID id) {
        if (baseService.existsById(id)) {
            baseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Entity not found"));
        }
    }

    // Méthode pour vérifier si un élément existe par son ID
    @GetMapping("/exists/{id}")
    public ResponseEntity<ApiResponse<Boolean>> existsById(@PathVariable ID id) {
        boolean exists = baseService.existsById(id);
        return ResponseEntity.ok(ApiResponse.success(exists));
    }

    // Méthode pour filtrer les champs d'une entité
    private T filterFields(T entity, String fields) {
        // Cette méthode doit filtrer les champs de l'entité en fonction du paramètre 'fields'
        // Le paramètre 'fields' peut être une chaîne avec des noms de champs séparés par des virgules.
        // Par exemple : "name,age" pour retourner uniquement les champs 'name' et 'age'.
        // Implémentation dépendante de votre logique spécifique
        return entity;  // Exemple basique, à adapter selon la logique de filtrage
    }
}
