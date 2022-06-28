import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DivHandler implements HttpHandler{

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
            String dividendo = partes[2];
            String divisor = partes[3];
            byte[] result = calculateDiv(dividendo, divisor);

            conn.sendResponseHeaders(200, result.length);

            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html; charset=UTF-8");

            try (OutputStream out = conn.getResponseBody()) {
                out.write(result);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch(NumberFormatException ex){
            // throw new IOException("Parâmetros não numéricos.");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    byte[] calculateDiv(String dividendo, String divisor){
        double dDividendo = Double.parseDouble(dividendo);
        double dDivisor = Double.parseDouble(divisor);
        
        if (dDivisor == 0){
            String frase = "O divisor não pode ser zero.";
            return frase.getBytes();
        }

        double quociente = (dDividendo / dDivisor);

        return Double.toString(quociente).getBytes();
    }
}

