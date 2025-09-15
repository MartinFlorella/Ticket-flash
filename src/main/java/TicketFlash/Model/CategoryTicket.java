package TicketFlash.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CategoryTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria; // Ej: "Campo", "Platea", "VIP"

    private BigDecimal precio;

    private Integer stock; // Entradas disponibles para esta categoría

    @ManyToOne
    private Event event; // Evento al que pertenece esta categoría

    // Constructors
    public CategoryTicket() {
    }

    public CategoryTicket(String categoria, BigDecimal precio, Integer stock, Event event) {
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.event = event;
    }

    public void setId(Long id) {
        this.id = id;
    }
// Getters y Setters

    public Integer getStock() {return stock;}

    public Long getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public  void setStock(Integer stock) {
        this.stock = stock;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
