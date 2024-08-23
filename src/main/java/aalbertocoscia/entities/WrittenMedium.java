package aalbertocoscia.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Supplier;

@Entity
@Table(name = "written_medium")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "medium_type", discriminatorType = DiscriminatorType.STRING)
public class WrittenMedium {
    @Id
    @Column(name = "isbn")
    protected String isbn;
    protected String title;
    @Column(name = "publication_date")
    protected LocalDate publicationDate;
    @Column(name = "number_pages")
    protected int numPages;

    Supplier<String> isbnSupplier = () -> {
        Faker faker = new Faker();
        return "978-0-" + faker.number().numberBetween(10, 99) + "-" + faker.number().numberBetween(10000, 99999) + "-" + faker.number().numberBetween(0, 9);
    };

    Supplier<LocalDate> pubDateSupplier = () -> {
        Faker faker = new Faker();
        LocalDate startDate = LocalDate.parse("1900-01=01");
        LocalDate today = LocalDate.now();
        return asLocalDate(faker.date().between(asDate(startDate), asDate(today)));
    };

    public WrittenMedium() {
    }

    public WrittenMedium(String title) {
        Faker faker = new Faker();
        this.isbn = isbnSupplier.get();
        this.title = title;
        this.publicationDate = pubDateSupplier.get();
        this.numPages = faker.number().numberBetween(30, 500);
    }

    protected static Date asDate(LocalDate ld) {
        return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    protected static LocalDate asLocalDate(Date d) {
        return Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return "WrittenMedium{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", numPages=" + numPages +
                '}';
    }
}
