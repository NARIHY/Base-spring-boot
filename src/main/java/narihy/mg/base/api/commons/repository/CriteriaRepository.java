package narihy.mg.base.api.commons.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class CriteriaRepository<E> {

    private final EntityManager entityManager;
    private final Class<E> domainType;

    public CriteriaRepository(Class<E> clazz, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.domainType = clazz;
    }

    /**
     * Méthode pour rechercher des entités selon des critères dynamiques avec pagination.
     */
    public Page<E> findByCriteria(Pageable pageable, MultiValueMap<String, String> criteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(domainType);
        Root<E> root = query.from(domainType);

        // Création d'une liste de predicates pour filtrer les résultats selon les critères
        List<Predicate> predicates = buildPredicates(criteria, criteriaBuilder, root);

        // Application des filtres (WHERE clause)
        query.where(predicates.toArray(new Predicate[0]));

        // Application du tri si nécessaire
        query = applySorting(pageable.getSort(), criteriaBuilder, query, root);

        // Exécution de la requête
        List<E> resultList = executeQuery(query, pageable);

        // Comptage du nombre total de résultats
        long total = countResults(criteria, predicates);

        return new PageImpl<>(resultList, pageable, total);
    }

    /**
     * Construire la liste des predicates (conditions de filtrage) à partir des critères fournis.
     */
    private List<Predicate> buildPredicates(MultiValueMap<String, String> criteria, CriteriaBuilder criteriaBuilder, Root<E> root) {
        List<Predicate> predicates = new ArrayList<>();

        Set<Map.Entry<String, List<String>>> entrySet = criteria.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String param = entry.getKey();
            Path<?> path = root.get(param); // On suppose ici que les critères sont directement des attributs simples
            for (String value : entry.getValue()) {
                Predicate predicate = buildPredicate(criteriaBuilder, path, value);
                predicates.add(predicate);
            }
        }

        return predicates;
    }

    /**
     * Créer un predicate (condition) pour un attribut donné avec une valeur spécifique.
     */
    private Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Path<?> path, String value) {
        // Si le path est un String, on peut utiliser LIKE
        if (path.getJavaType().equals(String.class)) {
            // Si la valeur contient des '%', c'est un LIKE
            if (value.contains("%")) {
                return criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), value.toLowerCase());
            }
            // Sinon, on fait une égalité sur les chaînes de caractères
            return criteriaBuilder.equal(criteriaBuilder.lower((Path<String>) path), value.toLowerCase());
        }

        // Si le path est de type Integer, Long, ou tout autre type numérique
        if (path.getJavaType().equals(Integer.class) || path.getJavaType().equals(Long.class)) {
            try {
                // Convertir la valeur en nombre
                Number numberValue = Double.parseDouble(value);
                return criteriaBuilder.equal(path, numberValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format for value: " + value);
            }
        }

        // Si le path est de type Boolean
        if (path.getJavaType().equals(Boolean.class)) {
            Boolean boolValue = Boolean.parseBoolean(value);
            return criteriaBuilder.equal(path, boolValue);
        }

        // Si le path est de type Date
        if (path.getJavaType().equals(java.util.Date.class)) {
            try {
                java.util.Date dateValue = javax.xml.bind.DatatypeConverter.parseDateTime(value).getTime();
                return criteriaBuilder.equal(path, dateValue);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid date format for value: " + value, e);
            }
        }

        // Si le path est un autre type, vous pouvez adapter cette logique pour le type spécifique
        // Par défaut, on fait une égalité simple
        return criteriaBuilder.equal(path, value);
    }



    /**
     * Appliquer le tri à la requête, basé sur le tri du pageable.
     */
    private CriteriaQuery<E> applySorting(Sort sort, CriteriaBuilder criteriaBuilder, CriteriaQuery<E> query, Root<E> root) {
        if (sort == null || sort.isEmpty()) {
            return query;
        }

        List<Order> orders = new ArrayList<>();
        for (Sort.Order order : sort) {
            Path<?> path = root.get(order.getProperty());
            if (order.isAscending()) {
                orders.add(criteriaBuilder.asc(path));
            } else {
                orders.add(criteriaBuilder.desc(path));
            }
        }
        query.orderBy(orders);

        return query;
    }

    /**
     * Exécuter la requête de recherche et obtenir les résultats paginés.
     */
    private List<E> executeQuery(CriteriaQuery<E> query, Pageable pageable) {
        return entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    /**
     * Comptabiliser le nombre total de résultats sans pagination (utilisé pour le calcul du total de la page).
     */
    private long countResults(MultiValueMap<String, String> criteria, List<Predicate> predicates) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> countRoot = countQuery.from(domainType);

        countQuery.select(criteriaBuilder.count(countRoot));
        countQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
