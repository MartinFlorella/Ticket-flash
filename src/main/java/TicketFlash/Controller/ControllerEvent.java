package TicketFlash.Controller;

import TicketFlash.DTO.EventDTO;
import TicketFlash.Model.Event;
import TicketFlash.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerEvent {

    @Autowired
    private EventService eventService;

    @PostMapping("/event/create/{categoryId}")
    public String saveEvent(@RequestBody EventDTO eventDto, @PathVariable Long categoryId){
        eventService.saveEvent(eventDto, categoryId);
        return "This event was created";
    }


    @GetMapping ("/event/findById/{eventId}")
    public Event findById(@PathVariable Long eventId){

        return  eventService.findById(eventId);
    }

    @GetMapping("/events/search")
    public List<Event> buscarEventos(@RequestParam String nombre) {
        return eventService.buscarPorNombre(nombre);
    }
}
