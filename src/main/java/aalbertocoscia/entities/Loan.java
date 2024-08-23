package aalbertocoscia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Loan {
    @Id
    @GeneratedValue
    @Column(name = "loan_id", unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medium_id", nullable = false)
    private WrittenMedium writtenMedium;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "estimated_end_date", nullable = false)
    private LocalDate estimatedEndDate;

    @Column(name = "actual_end_date")
    private LocalDate acutalEndDate;

    public Loan() {
    }

    public Loan(User user, WrittenMedium writtenMedium, LocalDate startDate, LocalDate acutalEndDate) {
        this.user = user;
        this.writtenMedium = writtenMedium;
        this.startDate = startDate;
        this.estimatedEndDate = startDate.plusDays(30);
        this.acutalEndDate = acutalEndDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", writtenMedium=" + writtenMedium +
                ", startDate=" + startDate +
                ", estimatedEndDate=" + estimatedEndDate +
                ", acutalEndDate=" + acutalEndDate +
                '}';
    }
}
