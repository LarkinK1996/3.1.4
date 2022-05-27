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
    public Set<Role> getRoleById(Integer[] role_id) {
        Set<Role> roleResult = new HashSet<>();
        if (role_id == null) {
            roleResult.add(entityManager.find(Role.class, 1));
        } else {
            for (int id : role_id) {
                TypedQuery<Role> q = entityManager.createQuery("select role from Role role where role.id = :id", Role.class);
                q.setParameter("id", id);
                Role result = q.getResultList().stream().filter(role -> role.getId() == id).findAny().orElse(null);
                roleResult.add(result);
            }
        }
        return roleResult;
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
