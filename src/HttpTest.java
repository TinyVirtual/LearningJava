import java.util.Date;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@SuppressWarnings("unused")
public class HttpTest {
    private static int defaultPort = 8461;

    public static void main(String[] args) throws IOException {
        System.out.println("Starting server!");
        // Great, this is so fucking simple, but so fucking hard since i still know nothing
        System.out.println("Your arguments are \"" + String.join("\", \"", args) + "\" with a length of " + String.valueOf(args.length) );
        // ok, still bullshit, but here is something to start with

        int myPort = defaultPort;
        if(args.length > 0 && !Double.isNaN(Double.parseDouble(args[0]))){
            int prePort = Integer.parseInt(args[0]);
            if(prePort >= 1024 && prePort <= 49151){
                myPort = prePort;
            } else {
                throw new Error("Invalid server port range!");
            }

        }
        // Create an HttpServer instance
        HttpServer server = HttpServer.create(new InetSocketAddress(myPort), 0);

        // Create a context for a specific path and set the handler
        server.createContext("/", new HTTPRNG());

        // Start the server
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server is running on port "+String.valueOf(myPort));
    }

    // Define a custom HttpHandler
    static class HTTPRNG implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Handle the request
            String response = (String.format("%.4f",Math.random()) + " generated at "+ new Date().toString());
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
// Do not forget the line break at the end
