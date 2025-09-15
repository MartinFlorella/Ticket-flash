package TicketFlash.ExternalClient;

import com.mercadopago.MercadoPagoConfig;
import org.codehaus.plexus.component.annotations.Component;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;


public class MercadoPagoClient {





   String mercadoPagoAccessToken = "APP_USR-1277614748626495-061713-8c7b79b0897692a9c419a489554648c7-2491148940";

        public JSONObject getMerchantOrder(Long merchantOrderId) throws IOException {

            String url = "https://api.mercadopago.com/merchant_orders/" + merchantOrderId;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + mercadoPagoAccessToken);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                String response = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                        .lines().collect(Collectors.joining("\n"));
                return new JSONObject(response);
            } else {
                System.err.println("❌ Error obteniendo merchant order: " + responseCode);
                return null;
            }
        }
    }


