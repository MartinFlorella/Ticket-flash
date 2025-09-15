package TicketFlash.ExternalClient;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRGenerator {

    public static void generarQR(String data, String filePath) throws WriterException, IOException {
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix matriz = qrWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300);
        Path path = Paths.get(filePath);
        MatrixToImageWriter.writeToPath(matriz, "PNG", path);
}

}

