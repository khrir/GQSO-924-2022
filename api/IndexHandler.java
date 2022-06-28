import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class IndexHandler implements HttpHandler{
    
    public static final String PATH = "/";

    public void handle(HttpExchange conn) throws IOException{
        byte[] opa = "Opa".getBytes();
        try {

            // Status http
            conn.sendResponseHeaders(200, opa.length);

            // Headers
            Headers headers = conn.getResponseHeaders();
            headers.add("Content-Type", "text/html ; charset=UTf-8");

            // Data
            try (OutputStream out = conn.getResponseBody()){
                out.write(opa);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            conn.close();
        }
    }
}