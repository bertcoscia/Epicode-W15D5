package aalbertocoscia.dao;

import aalbertocoscia.entities.WrittenMedium;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

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
        System.out.println("Element " + wm.getTitle() + " successfully saved");
    }

    public WrittenMedium findMediumByIsbn(String isbn) {
        WrittenMedium found = em.find(WrittenMedium.class, isbn);
        if (found == null) throw new NotFoundException(isbn);
        return found;
    }

    public void findMediumByIsbnAndDelete(String isbn) {
        WrittenMedium found = findMediumByIsbn(isbn);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Element " + found.getIsbn() + " successfully deleted");
    }

    public List<WrittenMedium> findMediaByPubYear(String year) {
        TypedQuery<WrittenMedium> query = em.createQuery("SELECT m FROM WrittenMedium m WHERE EXTRACT(YEAR FROM m.publicationDate) = :year", WrittenMedium.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    public List<WrittenMedium> findBooksByAuthor(String author) {
        TypedQuery<WrittenMedium> query = em.createQuery("SELECT m FROM WrittenMedium m WHERE m.medium_type = 'Book' AND LOWER(m.author) = LOWER(:author)", WrittenMedium.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    public List<WrittenMedium> findMediaByTitle(String title) {
        TypedQuery<WrittenMedium> query = em.createQuery("SELECT m FROM WrittenMedium m WHERE LOWER(m.title) LIKE LOWER (:title) ", WrittenMedium.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

}
