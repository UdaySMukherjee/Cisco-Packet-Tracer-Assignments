import java.io.*;
import java.net.*;

public class multiclient {
    public static void main(String[] args) {
        String hostName = "127.0.0.1";
        int portNumber = 8050;

        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);

                System.out.println("Received from server: " + in.readLine());

                if (userInput.equals("quit")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}

