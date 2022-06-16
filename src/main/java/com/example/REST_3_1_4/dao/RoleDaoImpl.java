package com.example.REST_3_1_4.dao;

import com.example.REST_3_1_4.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public Role getRole(String name) {
        return entityManager
                .createQuery("select role from Role role where role.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Role getRoleById(Integer role_id) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id =:role_id", Role.class);
        query.setParameter("role_id", role_id);
        return query.getResultList().stream().findAny().orElse(null);

    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
