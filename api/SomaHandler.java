import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DivHandler implements HttpHandler{

    // public static final String PATH = "/soma";

    public void handle(HttpExchange conn) throws IOException {
       
        String[] partes = conn.getRequestURI().getPath().split("/");

        // partes { partes[0] = "", partes[1] = "soma", partes[2] = "number_1", partes[3] = "number_2"    
        String parcela1 = partes[2];
        String parcela2 = partes[3];

        byte[] result = calculateSoma(parcela1, parcela2);
        try {
            conn.sendResponseHeaders(200, result.length);

            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");

            try (OutputStream out = conn.getResponseBody()) {
                out.write(result);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    byte[] calculateSoma(String parcela1, String parcela2){
        double dParcela1 = Double.parseDouble(parcela1);
        double dParcela2 = Double.parseDouble(parcela2);

       

     
        double soma = (dParcela1 / dParcela2);

        return Double.toString(soma).getBytes();
    }
}