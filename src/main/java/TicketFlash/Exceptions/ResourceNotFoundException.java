package TicketFlash.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) { //EXCEPCION PARA CUANDO NO EXISTE UN ELEMENTO EN TAL CONTEXTO
        super(message);                                //if (!eventoExiste)
    }                                                  // throw new ResourceNotFoundException("Evento no existe");
}
