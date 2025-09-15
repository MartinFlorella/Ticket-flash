package TicketFlash.Service;

import TicketFlash.DTO.EventDTO;
import TicketFlash.Model.Event;

import java.util.List;

public interface IEventService {

    public List<Event> findAll();
    public Event findById(Long id);
    public Event saveEvent(EventDTO eventDto, Long categoryId);
    public void deleteEvent(Long id);
    List<Event> buscarPorNombre(String nombre);
}
