import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DivHandler implements HttpHandler{

    // public static final String PATH = "/multi";

    public void handle(HttpExchange conn) throws IOException {
       
        String[] partes = conn.getRequestURI().getPath().split("/");

        // partes { partes[0] = "", partes[1] = "multi", partes[2] = "number_1", partes[3] = "number_2"    
        String fator1 = partes[2];
        String fator2 = partes[3];

        byte[] result = calculateMulti(fator1, fator2);
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

    byte[] calculateMulti(String fator1, String fator2){
        double dFator1 = Double.parseDouble(fator1);
        double dFator2 = Double.parseDouble(fator2);

        // Primeira verificação, depois penso nas outras

        double produto = (dFator1 * dFator2);

        return Double.toString(produto).getBytes();
    }
}