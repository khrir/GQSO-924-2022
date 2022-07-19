import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class SubHandler implements HttpHandler{

    // public static final String PATH = "/div";

    public void handle(HttpExchange conn) throws IOException {
          
        try {
            String[] partes = conn.getRequestURI().getPath().split("/");

            if (partes.length > 4){
                throw new IOException("Tem mais de dois parâmetros.");
            }
            else if (partes.length < 4){
                throw new IOException("Tem menos de dois parâmetros.");
            }

            // partes { partes[0] = "", partes[1] = "div", partes[2] = "number_1", partes[3] = "number_2"    
            String numero1 = partes[2];
            String numero2 = partes[3];

        byte[] result = calculateSub(numero2, numero2);
            conn.sendResponseHeaders(200, result.length);

            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");

            try (OutputStream out = conn.getResponseBody()) {
                out.write(result);
            } 
            catch(NumberFormatException ex){
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

    byte[] calculateSub(String numero1, String numero2){
        double dNumero1 = Double.parseDouble(numero1);
        double dNumero2 = Double.parseDouble(numero2);

        
        double total = (dNumero1-dNumero2);

        return Double.toString(total).getBytes();
    }