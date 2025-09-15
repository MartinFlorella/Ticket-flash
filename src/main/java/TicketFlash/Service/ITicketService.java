package TicketFlash.Service;

import TicketFlash.DTO.TicketDTO;
import TicketFlash.Model.Event;
import TicketFlash.Model.Ticket;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import java.util.List;

public interface ITicketService {
    public List<Ticket> findAll();
    public Ticket findById(Long id);
    public Ticket saveTicket(TicketDTO dto, Long id_Evento);
    public void deleteTicket(Long id);
    /*public void validarPagoYCrearTicket(Long paymentId) throws MPException, MPApiException;*/
    public boolean validarPagoYCrearTicket(Long paymentId) throws MPException, MPApiException;
}
