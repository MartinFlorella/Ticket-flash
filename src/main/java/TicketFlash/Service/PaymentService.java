package TicketFlash.Service;



import TicketFlash.DTO.TicketDTO;
import TicketFlash.Model.CategoryTicket;
import TicketFlash.Model.PreTicket;
import TicketFlash.Repository.ICategoryTicketRepository;
import TicketFlash.Repository.IPreTicketRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.MercadoPagoClient;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.merchantorder.MerchantOrderPayment;
import com.mercadopago.resources.preference.Preference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    IPreTicketRepository preTicketRepository;
    @Autowired
    ICategoryTicketRepository categoryTicketRepository;
    @Autowired
    private TicketService ticketService;


    // CREAR PREFERENCIA CONECTADO CON LA CREACION DEL TICKET //
// PARA PROBAR ESTE METODO Y TODO LO QUE TENGA QUE VER CON TICKET SE DEBE PONER EL TOKEN CORRECTO DE MERCADO PAGO, SINO NO VA A FUNCIONAR
    public String createPreference(TicketDTO dto, Long categoryId) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken("APP_USR-1277614748626495-061713-8c7b79b0897692a9c419a489554648c7-2491148940");
        // Guardar PreTicket
        String uuid = UUID.randomUUID().toString();
        PreTicket preTicket = new PreTicket();
        preTicket.setUuid(uuid);
        preTicket.setUserId(dto.getUserId());
        preTicket.setSeat(dto.getSeat());
        preTicket.setQrCode(dto.getQrCode());
        preTicket.setCategoryId(categoryId);
        preTicketRepository.save(preTicket);

        CategoryTicket catTicket = categoryTicketRepository.findById(categoryId).orElseThrow();



        // Crear preferencia con UUID como external_reference
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title(catTicket.getCategoria())
                .quantity(1)
                .unitPrice(catTicket.getPrecio())
                .build();

        PreferenceRequest request = PreferenceRequest.builder()
                .items(List.of(item))
                .backUrls(
                        PreferenceBackUrlsRequest.builder()
                                .success("https://tuapp.com/success")
                                .failure("https://tuapp.com/failure")
                                .pending("https://tuapp.com/pending")
                                .build()
                )
                .autoReturn("approved")
                .externalReference(uuid)
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(request);

        return preference.getInitPoint();
    }



    public void processWebhook(Map<String, Object> payload) throws MPException, MPApiException {
        System.out.println("🔔 Webhook recibido: " + payload);

        String type = (String) payload.get("type");
        Map<String, Object> data = (Map<String, Object>) payload.get("data");

        if ("topic_merchant_order_wh".equals(type)) {
            Long merchantOrderId = null;

            // Intentamos sacar el ID desde data.id si existe
            if (data != null && data.get("id") != null) {
                merchantOrderId = Long.valueOf(data.get("id").toString());
            } else if (payload.get("id") != null) {
                // Tomamos el id de arriba si data.id no existe
                merchantOrderId = Long.valueOf(payload.get("id").toString());
            }

            if (merchantOrderId != null) {
                MerchantOrderClient client = new MerchantOrderClient();
                MerchantOrder merchantOrder = client.get(merchantOrderId);

                if (merchantOrder != null) {
                    List<MerchantOrderPayment> payments = merchantOrder.getPayments();

                    for (MerchantOrderPayment payment : payments) {
                        String status = payment.getStatus();

                        if ("approved".equals(status)) {
                            Long paymentId = payment.getId();
                            ticketService.validarPagoYCrearTicket(paymentId);
                            System.out.println("✅ Ticket creado para paymentId: " + paymentId);
                            return;
                        }
                    }

                    System.out.println("⚠ No hay pagos aprobados en esta merchant_order.");
                } else {
                    System.out.println("❌ Merchant order no encontrada.");
                }
            } else {
                System.out.println("⚠ No se pudo determinar merchant_order_id.");
            }
        } else {
            System.out.println("⚠ Webhook tipo no esperado: " + type);
        }
    }









    /*Primer metodo de prueba con API MP*/
    public String createPreferenceTest() throws MPException, MPApiException {
        // 1. Configurar el token de acceso
        MercadoPagoConfig.setAccessToken("APP_USR-1277614748626495-061713-8c7b79b0897692a9c419a489554648c7-2491148940");

        // 2. Crear los ítems de la preferencia
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title("Platea")
                .quantity(1)
                .unitPrice(BigDecimal.valueOf(1000))
                .build()

                ;
        // 3. Crear la preferencia
        PreferenceRequest request = PreferenceRequest.builder()
                .items(List.of(item))
                .backUrls(
                        PreferenceBackUrlsRequest.builder()
                                .success("https://tuapp.com/success")
                                .failure("https://tuapp.com/failure")
                                .pending("https://tuapp.com/pending")
                                .build()
                )
                .autoReturn("approved")
                .build();
        // 4. Crear el cliente y enviar la preferencia
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(request);

        // 5. Retornar la URL de inicio de pago
        return preference.getInitPoint();
    }



}