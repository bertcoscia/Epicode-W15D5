package aalbertocoscia;

import aalbertocoscia.dao.LoanDAO;
import aalbertocoscia.dao.UserDAO;
import aalbertocoscia.dao.WrittenMediumDAO;
import aalbertocoscia.entities.*;
import aalbertocoscia.enums.Periodicity;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Epicode-W15D5");

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Faker faker = new Faker();

        EntityManager em = emf.createEntityManager();

        LoanDAO ld = new LoanDAO(em);
        UserDAO ud = new UserDAO(em);
        WrittenMediumDAO wmd = new WrittenMediumDAO(em);

        User aldo = new User("Aldo", "Baglio", "1970-01-01");
        User giovanni = new User("Giovani", "Storti", "1965-04-15");
        User giacomo = new User("Giacomo", "Poretti", "1963-06-25");

        Book book1 = new Book(faker.book().title(), 11, faker.book().author(), faker.book().genre());
        Book book2 = new Book(faker.book().title(), 12, faker.book().author(), faker.book().genre());
        Book book3 = new Book(faker.book().title(), 13, faker.book().author(), faker.book().genre());
        Magazine mag1 = new Magazine(faker.medical().medicineName(), 14, Periodicity.WEEKLY);
        Magazine mag2 = new Magazine(faker.medical().medicineName(), 15, Periodicity.MONTHLY);
        Magazine mag3 = new Magazine(faker.medical().medicineName(), 16, Periodicity.SEMIANNUAL);

        User aldoFromDb = ud.findUserById("d7e51a2b-3a20-4ccc-bf1d-0355910a67cd");
        User giovanniFromDb = ud.findUserById("48ef8468-91b0-4557-86e1-cc68015d099e");
        User giacomoFromDb = ud.findUserById("d23d890a-cb73-4236-a7a6-db817314c96a");

        WrittenMedium book1FromDb = wmd.findMediumByIsbn("978-0-10-8537-11");
        WrittenMedium book2FromDb = wmd.findMediumByIsbn("978-0-69-9801-12");
        WrittenMedium book3FromDb = wmd.findMediumByIsbn("978-0-33-7562-13");
        WrittenMedium mag1FromDb = wmd.findMediumByIsbn("978-0-76-8751-14");
        WrittenMedium mag2FromDb = wmd.findMediumByIsbn("978-0-86-6968-15");
        WrittenMedium mag3FromDb = wmd.findMediumByIsbn("978-0-54-2879-16");


        Loan l1 = new Loan(aldoFromDb, book1FromDb, "2024-01-01", "2024-01-15");
        Loan l2 = new Loan(aldoFromDb, book2FromDb, "2024-01-15");

        //System.out.println(ld.findAllExpiredLoans());
        //System.out.println(wmd.findBooksByAuthor("Dr. Renato Cole"));
        //System.out.println(wmd.findMediaByPubYear("1944"));
        //System.out.println(wmd.findMediaByTitle("look"));
        //System.out.println(wmd.findMediaByTitle("Look Homeward, Angel"));
        //System.out.println(wmd.findMediaInLoanByUser("d7e51a2b-3a20-4ccc-bf1d-0355910a67cd"));

    }
}
