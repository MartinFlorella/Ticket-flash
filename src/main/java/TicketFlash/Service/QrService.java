package TicketFlash.Service;


    import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.awt.image.BufferedImage;

    @Service
    public class QrService {

        public String generateBase64Qr(String text) {
            try {
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);
                BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                javax.imageio.ImageIO.write(image, "png", baos);
                byte[] imageBytes = baos.toByteArray();

                return Base64.getEncoder().encodeToString(imageBytes);
            } catch (Exception e) {
                throw new RuntimeException("Error generando QR", e);
            }
        }
    }


