import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            Scanner s = new Scanner(System.in);
            
            //getting ip by name
            InetAddress ip = InetAddress.getByName("localhost");
            
            //establish connection with port number 5000
            Socket ss = new Socket(ip,5000);
            
            //input and output stream
            DataInputStream dis = new DataInputStream(ss.getInputStream());
            DataOutputStream dos = new DataOutputStream(ss.getOutputStream());
            
            //the following loop will exchange information between client and server
            while(true){
                String fromserver = (String)dis.readUTF();
                System.out.println(fromserver); // check server is on or not
                System.out.println("Enter Message");
                
                String msg = s.nextLine();
                dos.writeUTF(msg);// 
                
                if(msg.equals("exit")){
                    System.out.println("Clossing This Connection "+ss);
                    ss.close(); // close connection if client write "exit"
                    System.out.println("Connection Closed");
                    break;
                }
                
                String received =(String) dis.readUTF();
                System.out.println(received);
            }
            
            //closing recources
            
            dis.close();
            dos.close();
            s.close();
            
            
        }catch(Exception e){
            System.out.println("got exception");
            e.printStackTrace();
        }
    }
}
