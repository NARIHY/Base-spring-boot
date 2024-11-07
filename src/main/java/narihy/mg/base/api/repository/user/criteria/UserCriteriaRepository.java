package narihy.mg.base.api.repository.user.criteria;
import narihy.mg.base.api.commons.repository.CriteriaRepository;
import narihy.mg.base.api.persistences.models.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public class UserCriteriaRepository extends CriteriaRepository<UserEntity> {

    public UserCriteriaRepository() {
        super(UserEntity.class, null); // Le EntityManager sera injecté via Spring
    }

    // Vous pouvez définir des requêtes personnalisées avec des critères dynamiques ici
    public Page<UserEntity> findByCriteria(MultiValueMap<String, String> criteria, Pageable pageable) {
        return super.findByCriteria(pageable, criteria);
    }
}