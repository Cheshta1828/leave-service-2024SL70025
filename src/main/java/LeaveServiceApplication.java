import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class LeaveServiceApplication {

    public static void main(String[] args) throws IOException {

        LeaveCalculator calculator = new LeaveCalculator();

        int remainingLeave =
            calculator.calculateRemainingLeave(20, 5);

        double encashment =
            calculator.calculateLeaveEncashment(15, 1000.0);

        String environment =
            System.getenv().getOrDefault("APP_ENV", "local");

        HttpServer server = HttpServer.create(
            new InetSocketAddress(8081), 0
        );

        server.createContext("/", exchange -> {

            String response =
                "BITS ID: 2024SL70025\n" +
                "Employee Leave Service Started\n" +
                "Environment: " + environment + "\n" +
                "Remaining Leave: " + remainingLeave + "\n" +
                "Leave Encashment: " + encashment + "\n";

            exchange.getResponseHeaders().set(
                "Content-Type", "text/plain"
            );

            exchange.sendResponseHeaders(200, response.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        server.start();

        System.out.println(
            "Leave Service running on port 8080"
        );
    }
}
