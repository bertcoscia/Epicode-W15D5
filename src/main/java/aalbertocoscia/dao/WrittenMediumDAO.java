package aalbertocoscia.dao;

import aalbertocoscia.entities.WrittenMedium;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class WrittenMediumDAO {
    private final EntityManager em;

    public WrittenMediumDAO(EntityManager em) {
        this.em = em;
    }

    public void save(WrittenMedium wm) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(wm);
        transaction.commit();
        System.out.println("Written Medium " + wm.getTitle() + " successfully saved");
    }

    public WrittenMedium findMediumByIsbn(String isbn) {
        WrittenMedium found = em.find(WrittenMedium.class, isbn);
        if (found == null) throw new NotFoundException(isbn);
        return found;
    }
}
