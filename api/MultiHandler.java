import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MultiHandler implements HttpHandler{

    // public static final String PATH = "/multi";

    public void handle(HttpExchange conn) throws IOException {
       
        try {
            String[] partes = conn.getRequestURI().getPath().split("/");

            if (partes.length > 4){
                throw new IOException("Tem mais de dois parâmetros.");
            }
            else if (partes.length < 4){
                throw new IOException("Tem menos de dois parâmetros.");
            }

            // partes { partes[0] = "", partes[1] = "multi", partes[2] = "number_1", partes[3] = "number_2"    
            String multiplicando = partes[2];
            String multiplicador = partes[3];

        byte[] result = calculateMulti(multiplicando, multiplicador);

            conn.sendResponseHeaders(200, result.length);

            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");

            try (OutputStream out = conn.getResponseBody()) {
                out.write(result);
            } catch(NumberFormatException ex){
                ex.printStackTrace();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    byte[] calculateMulti(String multiplicando, String multiplicador){
        double dMultiplicando = Double.parseDouble(multiplicando);
        double dMultiplicador = Double.parseDouble(multiplicador);

        double produto = (dMultiplicando * dMultiplicador);

        return Double.toString(produto).getBytes();
    }
}