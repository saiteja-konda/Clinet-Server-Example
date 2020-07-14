import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Client{
    private Socket ClientSocket = new Socket();
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Client(String address, int port) throws IOException {
        try{
            //establishing the connection
            ClientSocket= new Socket(address,port);


            System.out.println("YOU ARE CONNECTED TO SERVER");
            //Taking Input from the console
            input = new DataInputStream(System.in);
            //Printing Output to the socket
            System.out.println("Enter your message to SERVER :");
            output = new DataOutputStream(ClientSocket.getOutputStream());

        }
        //Exceptions
        catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }

        String Line = "";
        while(!Line.equals("Over")){
            Line = input.readLine();
            output.writeUTF(Line);

        }
        //Closing the connection
        try{
            input.close();
            output.close();
            ClientSocket.close();
        }catch (IOException e3){
            e3.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException{
        Client client = new Client("127.0.0.1",4040);
    }
}