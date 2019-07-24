
import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 
  
// Server class 
public class Server1  
{ 
    public static void main(String[] args) throws IOException  
    { 
        Scanner scr = new Scanner(System.in);
        DataInputStream dis = null;
        DataOutputStream dos = null;
        ServerSocket ss = null;
        Socket s = null;
        
        try{
            
            ss = new ServerSocket(5000); //listen port 5000
            s = ss.accept(); //accept request from clients
            
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            
            String line = ""; 
  
            // reads message from client until "Over" is sent 
            while (!line.equals("over")) 
            { 
                try
                { 
                    line = dis.readUTF(); 
                    System.out.println(s+" :"+line );
                    
                    line = scr.nextLine();
                    dos.writeUTF(line);
  
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
             System.out.println("Closing connection"); 
  
            // close connection 
            s.close(); 
            dis.close();
            dos.close();
            
            
        }catch(Exception e){
            System.out.println("Got Exception ");
            System.out.println(e.getMessage());
        }
    } 
} 
  

