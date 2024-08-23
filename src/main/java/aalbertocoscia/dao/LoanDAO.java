package aalbertocoscia.dao;

import aalbertocoscia.entities.Loan;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan l) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(l);
        transaction.commit();
        System.out.println("Loan " + l.getId() + " successfully saved");
    }

    public Loan findLoanById(String loanId) {
        Loan found = em.find(Loan.class, UUID.fromString(loanId));
        if (found == null) throw new NotFoundException(loanId);
        return found;
    }

    public void findLoanByIdAndDelete(String loanId) {
        Loan found = findLoanById(loanId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Loan " + found.getId() + " successfully deleted");
    }

    public List<Loan> findAllExpiredLoans() {
        TypedQuery<Loan> query = em.createQuery(
                "SELECT l FROM Loan l WHERE l.estimatedEndDate < CURRENT_DATE AND l.actualEndDate IS NULL",
                Loan.class
        );
        return query.getResultList();
    }
}
