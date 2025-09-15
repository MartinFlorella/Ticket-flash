package TicketFlash.Enums;

public enum RoleName {

    ADMIN,
    ORGANIZER,
    CUSTOMER;

    // Puedes agregar métodos adicionales si es necesario
    public String getRoleWithPrefix() {
        return "ROLE_" + this.name();
    }
}
