package narihy.mg.base.api.commons.exception;

// Classe d'exception personnalisée pour les entités non trouvées
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}