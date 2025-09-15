package TicketFlash.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private User user;

    private String seat; //asiento


    private String qrCode;

    private LocalDateTime date;

    @ManyToOne
    private CategoryTicket category; // ← NUEVO: la categoría del ticket


    public Ticket(Long id, User user, String seat, String qrCode, LocalDateTime date, CategoryTicket category) {
        this.id = id;
        this.user = user;
        this.seat = seat;
        this.qrCode = qrCode;
        this.date = date;
        this.category = category;
    }

    public Ticket() {
    }

    public String getQrCode() {
        return qrCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public CategoryTicket getCategory() { return category;}

    public void setCategory(CategoryTicket category) { this.category = category;}


}
