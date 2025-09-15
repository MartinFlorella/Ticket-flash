package TicketFlash.Controller;

import TicketFlash.DTO.TicketDTO;
import TicketFlash.DTO.TicketResponseDTO;
import TicketFlash.Model.Event;
import TicketFlash.Model.Ticket;
import TicketFlash.Service.PaymentService;
import TicketFlash.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerTicket {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private PaymentService paymentService;


    @PostMapping("/ticket/create/{id_CategoryTicket}")
    public String saveTicket(@RequestBody TicketDTO dto, @PathVariable Long id_CategoryTicket){
        ticketService.saveTicket(dto, id_CategoryTicket);
        return "This ticket was created";
    }

    @GetMapping ("/ticket/findById/{ticketId}")
    public Ticket findById(@PathVariable Long ticketId){

        return  ticketService.findById(ticketId);
    }
    @GetMapping("/tickets/user/{userId}")
    public ResponseEntity<List<TicketResponseDTO>> listarTicketsPorUsuario(@PathVariable Long userId) {
        List<TicketResponseDTO> tickets = ticketService.obtenerTicketsPorUsuario(userId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/Ticket/all")
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
}
