package TicketFlash.Model;

import TicketFlash.Enums.PermissionName;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PermissionName name;

    public Permission() {
    }

    public Permission(Long id, PermissionName name) {
        this.id = id;
        this.name = name;
    }

    public PermissionName getName() {
        return name;
    }

    public void setName(PermissionName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
