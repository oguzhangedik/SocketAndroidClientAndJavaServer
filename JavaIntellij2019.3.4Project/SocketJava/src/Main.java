import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5555);

        while (true){
            System.out.println("IP : " + java.net.InetAddress.getLocalHost());
            Socket client = server.accept();

            InputStreamReader isr = new InputStreamReader(client.getInputStream());

            BufferedReader br = new BufferedReader(isr);

            PrintWriter printWriter = new PrintWriter(client.getOutputStream());

            System.out.println("Androidten gelen veri : " + br.readLine());

            printWriter.println("Serverdan, client'ın '"+ br.readLine() +"' istegine bu yanıt dönüldü. ");

            printWriter.flush();

            client.close();
            printWriter.close();
            isr.close();
            br.close();
        }
    }
}
