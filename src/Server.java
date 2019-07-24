import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;

public class Server {
    public static void main(String[] args) throws IOException{
        //server is listening port 5000
        ServerSocket ss = new ServerSocket(5000);
        
        //running infinte loop for client request
        while(true){
            Socket s = null;
            
            try{
                s = ss.accept(); // socket object received incoming clients request
                 System.out.println("A new client is connected : " + s); 
                
                 // obtaining input and out streams 
                final DataInputStream dis = new DataInputStream(s.getInputStream()); 
                final DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                
                DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
                DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
                
                String received; 
                String toreturn; 
                
                 try { 
          
                        // Ask user what he wants 
                        dos.writeUTF("What do you want?[Date | Time]..\n"+ 
                                    "Type Exit to terminate connection."); 
                          
                        // receive the answer from client 
                        received =(String) dis.readUTF(); 
                          
                        if(received.equals("Exit")) 
                        {  
                            System.out.println("Client " + s + " sends exit..."); 
                            System.out.println("Closing this connection."); 
                            s.close(); 
                            System.out.println("Connection closed"); 
                            break; 
                        } 
                          
                        // creating Date object 
                        Date date = new Date(); 
                          
                        // write on output stream based on the 
                        // answer from the client 
                        switch (received) { 
                          
                            case "Date" : 
                                toreturn = fordate.format(date); 
                                dos.writeUTF(toreturn); 
                                break; 
                                  
                            case "Time" : 
                                toreturn = fortime.format(date); 
                                dos.writeUTF(toreturn); 
                                break; 
                                  
                            default: 
                                dos.writeUTF("Invalid input"); 
                                break; 
                        } 
                    }catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                 
                  try
                { 
                    // closing resources 
                    dis.close(); 
                    dos.close(); 
                      
                }catch(IOException e){ 
                    e.printStackTrace(); 
                }  
                
            }catch(Exception e){
                System.out.println("Got Exception");
                System.out.println(e.getMessage());
            }
            
        }
    }
}
