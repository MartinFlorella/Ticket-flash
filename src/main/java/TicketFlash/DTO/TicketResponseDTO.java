package TicketFlash.DTO;

import TicketFlash.Model.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketResponseDTO {

    private Long id;
    private String seat;
    private String qrCode;
    private LocalDateTime date;
    private String categoryName;
    private BigDecimal categoryPrice;

    // Constructor
    public TicketResponseDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.seat = ticket.getSeat();
        this.qrCode = ticket.getQrCode();
        this.date = ticket.getDate();
        this.categoryName = ticket.getCategory().getCategoria();
        this.categoryPrice = ticket.getCategory().getPrecio();
    }

    // Getters y Setters si los necesitás

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getCategoryPrice() {
        return categoryPrice;
    }

    public void setCategoryPrice(BigDecimal categoryPrice) {
        this.categoryPrice = categoryPrice;
    }
}
