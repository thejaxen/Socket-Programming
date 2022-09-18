package MultiInteraction.GGWP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket sl = null;
        String line = null;
        DataInputStream br = null;
        DataInputStream is = null;
        PrintWriter os = null;

        try {
            sl = new Socket("localhost", 5151);
            br = new DataInputStream(System.in);
            is = new DataInputStream(sl.getInputStream());
            os = new PrintWriter(sl.getOutputStream());
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        System.out.println("Enter data to echo server");
        String response = null;
        try {
            line = br.readLine();
            while (line.compareTo("Quit") != 0) {
                os.println(line);
                os.flush();
                response = is.readLine();
                System.out.println("Server response:" + response);
                line = br.readLine();
            }
            is.close();
            os.close();
            br.close();
            sl.close();
            System.out.println("Connection closed");

        } catch (Exception e) {
            System.out.println("Socket error");
        }
    }

}
