
import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;


public class data_input_stream_important_server {

 static ServerSocket ss;
    static Socket s;

 

         public static void main(String[] args) {
boolean done2 = false;
while(!done2){
         try{
 
          ss=new ServerSocket(1045);
//             System.out.println("\t\t\t\t Connection Establishing.....");
             s=ss.accept();
 
 DataInputStream dIn = new DataInputStream(s.getInputStream());
DataOutputStream dOut = new DataOutputStream(s.getOutputStream());
 boolean done = false;
 while(!done) {

   byte messageType = dIn.readByte();

   switch(messageType)
   {
   case 1: // Type A
     //System.out.println("Message A: " + dIn.readUTF());
 	//String oke = dIn.readUTF();  
 	dOut.writeByte(1);
	    dOut.writeUTF("This is the first type of message.");
	    dOut.flush(); 
	    dOut.writeByte(2);
           dOut.writeUTF("This is the first type of message.");
            dOut.flush();
	   done = true;

	    break;
   case 2: // Type B
     System.out.println("Message B: " + dIn.readUTF());
     break;
   case 3: // Type C
     System.out.println("Message C [1]: " + dIn.readUTF());
     System.out.println("Message C [2]: " + dIn.readUTF());
     break;
   case 4:
     done = true;
   }
 }
 dIn.close();
dOut.close();
ss.close();
s.close();
// System.out.println("Message A: tehtud ");
         }catch (Exception e) {
                     System.out.println(e);
         }

}
         }
         


 
 
}
