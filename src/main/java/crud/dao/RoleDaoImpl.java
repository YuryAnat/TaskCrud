package crud.dao;

import crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addRole(Role role) {
        entityManager.joinTransaction();
        entityManager.persist(role);
    }

    @Override
    public Role getRole(long id) {
        return entityManager.createQuery("From Role r Where r.id = :id",Role.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void deleteRole(Role role) {
        entityManager.joinTransaction();
        entityManager.remove(role);
    }

    @Override
    public void editRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("From Role r Where r.roleName = :roleName",Role.class).setParameter("roleName", name).getSingleResult();
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("From Role", Role.class).getResultList();
    }
}