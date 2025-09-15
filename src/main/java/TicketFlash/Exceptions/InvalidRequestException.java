package TicketFlash.Exceptions;

public class InvalidRequestException extends RuntimeException  {
    public InvalidRequestException(String message) { //EXCEPCION PARA CUANDO NO ES POSIBLE REALIZAR TAL ACCION EJEMPLO
        super(message);                              //if (evento.getCapacity() <= 0)
                                                     // throw new InvalidRequestException("El evento no tiene más capacidad.");
    }
}
