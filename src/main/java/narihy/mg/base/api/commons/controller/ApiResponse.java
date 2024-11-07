package narihy.mg.base.api.commons.controller;

import java.util.Map;

public class ApiResponse<T> {
    private T data; // Données principales
    private Map<String, Object> metadata; // Pour les informations supplémentaires (ex: pagination, autres infos)
    private String message; // Message facultatif (ex: erreur, succès, etc.)

    // Constructeur
    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(T data, Map<String, Object> metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    // Getters et Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Méthode statique pour créer la réponse
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> success(T data, Map<String, Object> metadata) {
        return new ApiResponse<>(data, metadata);
    }

    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>(null);
        response.setMessage(message);
        return response;
    }
}
