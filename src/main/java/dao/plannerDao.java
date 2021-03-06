package dao;

import entity.Planner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Marisha on 30/10/16.
 */

@Repository
public class PlannerDao {

    @PersistenceContext(name = "planner")
    private EntityManager entityManager;


    public List<Planner> find (Boolean predicate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Planner> cq = cb.createQuery(Planner.class);
        Root<Planner> from = cq.from(Planner.class);
        cq = cq.select(from);
        if (predicate != null) {
            Predicate predicate1 = cb.equal(from.get("status"), predicate);
            cq.where(predicate1);
        }
        return entityManager.createQuery(cq).getResultList();
    }


    public void merge(Planner planner) {
        entityManager.merge(planner);
    }

    public void updateStatus(Long id){
        Planner planner = entityManager.find(Planner.class, id);
        if (planner!=null) {
            planner.setStatus(true);
            entityManager.merge(planner);
        }
    }


    public void delete(Long id) {
        Planner planner = entityManager.find(Planner.class, id);
        if (planner!=null) {
            entityManager.remove(planner);
        }
    }
}
