package MultiInteraction.GGWP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        Socket s = null;
        ServerSocket ss2 = null;
        System.out.println("Server listening...");
        ss2 = new ServerSocket(5151);

        while (true) {
            try {
                s = ss2.accept();
                System.out.println("Connection started");
                ServerThread st = new ServerThread(s);
                st.start();
            } catch (Exception e) {
                System.out.println("Error Occurred");
            }
        }
    }
}

class ServerThread extends Thread {
    String line = null;
    DataInputStream is = null;
    DataInputStream br = null;
    PrintWriter os = null;
    Socket sl = null;

    public ServerThread(Socket s) {
        sl = s;
    }

    public void run() {
        try {
            is = new DataInputStream(sl.getInputStream());
            os = new PrintWriter(sl.getOutputStream());
            line = is.readLine();
            while (line.compareTo("Quit") != 0) {
                os.println(line);
                os.flush();
                System.out.println("Response of Client:" + line);
                line = is.readLine();
            }
            is.close();
            os.close();
            sl.close();
        } catch (Exception e) {
        }
    }
}