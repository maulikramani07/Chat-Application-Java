// Java implementation for a client 
// Save file as Client.java 
  
import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
  
// Client class 
public class Client1  
{ 
    public static void main(String[] args) throws IOException  
    { 
        Scanner scr = new Scanner(System.in);
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Socket s = null;
        
        try{
            InetAddress ip = InetAddress.getByName("localhost");
            
            s = new Socket(ip,5000);
            
            
             dis = new DataInputStream(s.getInputStream());
             dos = new DataOutputStream(s.getOutputStream());
            
        }catch(Exception e){
            System.out.println("Got Exception 1");
            e.printStackTrace();
        }
        
        String line = ""; 
  
        // keep reading until "Over" is input 
        while (!line.equals("over")) 
        { 
            try
            { 
                line = scr.nextLine();
                dos.writeUTF(line); 
                
                line = dis.readUTF(); 
                System.out.println(s+" :"+line );
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
        
        try{
            dis.close();
            dos.close();
            scr.close();
        }catch(Exception e){
            System.out.println("GotException 2");
            e.printStackTrace();
        }
    } 
} 