import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;

public class Server{

    private static final int BACKLOG_SIZE = 10; // Limite de conexões que o servidor pode aceitar

    public static void main(String[] args) throws IOException{
        
        InetSocketAddress addr = new InetSocketAddress(50000);     // Servidor ouvindo na porta 50000
        HttpServer server = HttpServer.create(addr, BACKLOG_SIZE); // Criando um servidor
        server.setExecutor(Executors.newSingleThreadExecutor());

        // Definindo as rotas
        // server.createContext('/', new IndexHandler()); Endereço raiz a ser desenvolvido

        // Iniciando o servidor
        server.start();
        System.out.printf("O servidor está ouvindo pela porta %s", server.getAddress().getPort());
    }
}