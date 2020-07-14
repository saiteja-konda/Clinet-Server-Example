import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    private ServerSocket serverSocket =null;
    private Socket clientSocket = null;
    private DataInputStream input =null;
    public Server(int port){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            //server started and waiting for the client to get connected
            System.out.println("waiting for the client to connect");
            clientSocket = serverSocket.accept();
            //server connected to the client
            System.out.println("connected to client");

            //Taking input from the client Socket
            input = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));


            String line = "";
            // reads message from client until "Over" is sent
            while (!line.equals("Over")) {
                try {
                    line = input.readUTF();

                        System.out.println("Message from Client : " + line);


                }
                catch(IOException i) {
                    System.out.println(i);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        //closing connection
        try{
            input.close();
            serverSocket.close();
            clientSocket.close();
        }catch (IOException e2){
            e2.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        Server server = new Server(4040);

    }
}

