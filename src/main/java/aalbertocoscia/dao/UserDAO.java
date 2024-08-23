package aalbertocoscia.dao;

import aalbertocoscia.entities.User;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
        System.out.println("User " + user.getCardId() + " successfully saved");
    }

    public User findUserById(String userId) {
        User found = em.find(User.class, UUID.fromString(userId));
        if (found == null) throw new NotFoundException(userId);
        return found;
    }

    public void findUserByIdAndDelete(String userId) {
        User found = findUserById(userId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("User " + found.getCardId() + " successfully deleted");
    }

}
