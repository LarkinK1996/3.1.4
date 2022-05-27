package com.example.REST_3_1_4.dao;

import com.example.REST_3_1_4.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    public void delete(int id) {
        Query query = entityManager.createQuery("delete from User where id =:userID");
        query.setParameter("userID", id);
        query.executeUpdate();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public void updateUser(int id, User newUser) {

        User user = getUser(id);
        user.setFirst_name(newUser.getFirst_name());
        user.setLast_name(newUser.getLast_name());
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {

        return entityManager.createQuery("select user from User user where user.email = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }


    @Override
    public List<User> getAllUsers() {

        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }
}

