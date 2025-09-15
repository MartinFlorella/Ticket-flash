package TicketFlash.Service;

import TicketFlash.DTO.TicketDTO;
import TicketFlash.DTO.TicketResponseDTO;
import TicketFlash.Exceptions.ResourceNotFoundException;
import TicketFlash.Model.*;
import TicketFlash.Repository.*;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService{

    @Autowired
    ITicketRepository ticketRepository;
    @Autowired
    ICategoryTicketRepository categoryTicketRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IPreTicketRepository preTicketRepository;
    @Autowired
    QrService qrService;

    @Autowired
    private ICategoryTicketService categoryTicketService;



        // PARA PROBAR ESTE METODO Y TODO LO QUE TENGA QUE VER CON TICKET SE DEBE PONER EL TOKEN CORRECTO DE MERCADO PAGO, SINO NO VA A FUNCIONAR

    @Override
    public boolean validarPagoYCrearTicket(Long paymentId) throws MPException , MPApiException{
        System.out.println("Iniciando validación y creación de ticket para pagoId: " + paymentId);

        MercadoPagoConfig.setAccessToken("APP_USR-4157568653814383-061708-41c2c3a6c122f61e5c77348a7f1b5a1e-2491148940");

        PaymentClient paymentClient = new PaymentClient();
        Payment payment = paymentClient.get(paymentId);

        if (payment == null) {
            System.out.println("⚠️ Pago no encontrado para ID: " + paymentId);
            return false;
        }

        System.out.println("Estado del pago: " + payment.getStatus());

        if (!"approved".equalsIgnoreCase(payment.getStatus())) {
            System.out.println("Pago no aprobado");
            return false;
        }

        String uuid = payment.getExternalReference();

        PreTicket pre = preTicketRepository.findByUuid(uuid);
        if (pre == null) {
            System.out.println("PreTicket no encontrado para uuid: " + uuid);
            return false;
        }

        CategoryTicket category = categoryTicketRepository.findById(pre.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (category.getStock() <= 0) {
            System.out.println("No hay stock disponible para categoría: " + category.getCategoria());
            return false;
        }

        User user = userRepository.findById(pre.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        category.setStock(category.getStock() - 1);
        categoryTicketRepository.save(category);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setCategory(category);
        ticket.setSeat(pre.getSeat());
        ticket.setQrCode(uuid);
        ticket.setDate(LocalDateTime.now());

        ticketRepository.save(ticket);
        System.out.println("Ticket creado correctamente para usuario: " + user.getId());

        // Eliminar preTicket si querés
        preTicketRepository.delete(pre);

        return true;
    }




    /*Save ticket pero sin el pago, fase de prueba*/

    @Override
    public Ticket saveTicket(TicketDTO dto, Long id_Category) {

        // busca categoria ticket
        CategoryTicket categoryTicket = categoryTicketRepository.findById(id_Category)
                .orElseThrow(() -> new RuntimeException("la categoria del evento con ID " + id_Category + " no existe."));

        // verifica el stock disponible
        if (categoryTicket.getStock() <= 0) {
            throw new RuntimeException("No quedan mas entradas de la Categoria " + categoryTicket.getCategoria() + ".") ;
        }

        // busca el usuario que va a comprar el ticket
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //descuenta el stock y guarda la categoria actualizada
        categoryTicketService.reducirStock(categoryTicket.getId(), 1);
        categoryTicketRepository.save(categoryTicket);

        //crea y guarda el ticket
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setCategory(categoryTicket);
        ticket.setSeat(dto.getSeat());
        ticket.setQrCode(dto.getQrCode());
        ticket.setDate(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }


    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElseThrow(()-> new RuntimeException("El ticket con el id " + id + ", no existe"));
    }

    @Override
    public void deleteTicket(Long id) {

        if(ticketRepository.findById(id).isEmpty())
            throw new RuntimeException("El ticket que desea eliminar no existe");
        ticketRepository.deleteById(id);
    }
    public List<TicketResponseDTO> obtenerTicketsPorUsuario(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        return tickets.stream()
                .map(TicketResponseDTO::new)
                .collect(Collectors.toList());
    }


}
