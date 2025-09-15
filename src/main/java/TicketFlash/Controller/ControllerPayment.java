package TicketFlash.Controller;

import TicketFlash.DTO.TicketDTO;
import TicketFlash.Service.ITicketService;
import TicketFlash.Service.PaymentService;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class ControllerPayment {

    @Autowired
    ITicketService ticketService;


        private final PaymentService paymentService;

        public ControllerPayment(PaymentService paymentService) {
            this.paymentService = paymentService;
        }




    @PostMapping("/create-preference/{categoryId}")
    public ResponseEntity<String> createPreference(
            @RequestBody TicketDTO ticketDTO,
            @PathVariable Long categoryId) {
        try {
            String paymentUrl = paymentService.createPreference(ticketDTO, categoryId);
            return ResponseEntity.ok(paymentUrl);
        } catch (MPApiException e) {
            System.out.println("❌ Error de MercadoPago: " + e.getApiResponse().getContent());
            return ResponseEntity.badRequest().body("Error de MercadoPago: " + e.getApiResponse().getContent());
        } catch (MPException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error SDK MercadoPago: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error inesperado: " + e.getMessage());
        }
    }



    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
        try {
            paymentService.processWebhook(payload);
            return ResponseEntity.ok("Webhook procesado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando webhook");
        }
    }




    @GetMapping("/pago-exitoso")
    public ResponseEntity<String> confirmarPago(@RequestParam("payment_id") Long paymentId) {
        try {
            ticketService.validarPagoYCrearTicket(paymentId);
            return ResponseEntity.ok("✅ Ticket generado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error: " + e.getMessage());
        }
    }








}


