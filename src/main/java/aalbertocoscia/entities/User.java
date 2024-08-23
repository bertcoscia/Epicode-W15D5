package aalbertocoscia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "card_id", unique = true)
    private UUID cardId;
    private String name;
    private String surname;
    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @OneToMany(mappedBy = "user")
    private List<Loan> loanList;

    public User() {
    }

    public User(String name, String surname, LocalDate dateBirth) {
        this.name = name;
        this.surname = surname;
        this.dateBirth = dateBirth;
    }

    public UUID getCardId() {
        return cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    @Override
    public String toString() {
        return "User{" +
                "cardId=" + cardId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateBirth=" + dateBirth +
                ", loanList=" + loanList +
                '}';
    }
}
