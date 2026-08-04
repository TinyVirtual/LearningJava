package net.tinyprod.javacourse.httpDeploy;
import java.util.*;
import java.util.function.Function;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;

@SuppressWarnings("unused")

public class HttpDeploy {
    private static int defaultPort = 8461;
    private static HttpServer currentServer = null;

    private static class InnerHttpDeploy {
        public String path = "/";
        public Function<String,String> logic = (String req)->"Hello world!";
    }

    private static ArrayList<InnerHttpDeploy> listenings = new ArrayList<InnerHttpDeploy>();

    public static HttpServer Open(Integer port) throws IOException{
        int myPort = defaultPort;

        if(port >= 1024 && port <= 49151){
            myPort = port;
        } else {
            System.err.println("\u001B[33m[ "+new Date().toString().substring(11,19)+" WARN]: Port "+port.toString()+" is outside allowed range!\u001B[0m");
        }
        try {
            HttpDeploy.currentServer = HttpServer.create(new InetSocketAddress(myPort), 0);
        } catch(IOException error) {
            throw error;
        }
        return currentServer;
    }

    public static String Listen(String path, Function<String,String> logic){
        InnerHttpDeploy myListener = new InnerHttpDeploy();
        if(listenings.stream().filter(listener -> listener.path.equals(path)).findFirst().orElse(null) != null){
            myListener.path = path;
            myListener.logic = logic;

            listenings.add(myListener);

            HTTPPathHandler handler = new HTTPPathHandler();
            handler.setLogic(logic);
            currentServer.createContext(path, handler);
        }
        return "";
    }

    static class HTTPPathHandler implements HttpHandler {
        public static Function<String,String> pathLogic = (String req)->"Hello world!";

        public void setLogic(Function<String,String> logic){
            pathLogic = logic;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = pathLogic.apply(exchange.getRequestHeaders().toString());
            //String response = (String.format("%.4f",Math.random()) + " generated at "+ new Date().toString());
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
