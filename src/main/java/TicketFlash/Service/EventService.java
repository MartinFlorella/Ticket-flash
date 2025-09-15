package TicketFlash.Service;

import TicketFlash.DTO.EventDTO;
import TicketFlash.Model.Category;
import TicketFlash.Model.Event;
import TicketFlash.Repository.ICategoryRepository;
import TicketFlash.Repository.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService{

    @Autowired
    IEventRepository eventRepository;
    @Autowired
    ICategoryRepository categoryRepository;




    @Override
    public Event saveEvent(EventDTO eventDto, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("La categoria con ID " + categoryId + " no existe."));

        Event event = new Event();
        event.setCapacity(eventDto.getCapacity());
        event.setCategory(eventDto.getCategory());
        event.setId(eventDto.getId());
        event.setName(eventDto.getName());
        event.setStartDateTime(eventDto.getStartDateTime());
        event.setEndDateTime(eventDto.getEndDateTime());
        event.setLocation(eventDto.getLocation());
        event.setCategory(category);

        return eventRepository.save(event);
    }



    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("El evento con ID " + id +", No existe"));

    }


    @Override
    public void deleteEvent(Long id) {
            if(eventRepository.findById(id).isEmpty())
                throw new RuntimeException("No se encontro el evento que desea eliminar");
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> buscarPorNombre(String nombre) {

        return eventRepository.findByNameContainingIgnoreCase(nombre);
    }

}
