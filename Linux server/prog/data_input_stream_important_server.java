
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
	    File f = new File("/dev/sda");
	    dOut.writeUTF(f.getTotalSpace() +" bytes");
	    dOut.flush(); 
	    dOut.writeByte(2);
           dOut.writeUTF("This is the first type of message.");
            dOut.flush();
	   done = true;

	    break;
   case 2: // Type B
     //System.out.println("Message B: " + dIn.read
	dOut.writeByte(1);
        Process proc = Runtime.getRuntime().exec("/root/bash_scripts/test1.sh");
	BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
	try {
                        proc.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read.ready()) {
                         dOut.writeUTF(read.readLine());
                 	dOut.flush();
		}
	 dOut.writeUTF("end");
            dOut.flush();
		read.close();
		done = true;

	break;
   case 3: // Type C
    dOut.writeByte(1);
        Process proc1 = Runtime.getRuntime().exec("/root/bash_scripts/test10.sh");
        BufferedReader read1 = new BufferedReader(new InputStreamReader(
                    proc1.getInputStream()));
        try {
                        proc1.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read1.ready()) {
                         dOut.writeUTF(read1.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read1.close();
                



        Process proc16 = Runtime.getRuntime().exec("/root/bash_scripts/test2.sh");
        BufferedReader read16 = new BufferedReader(new InputStreamReader(
                    proc16.getInputStream()));
        try {
                        proc16.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read16.ready()) {
                         dOut.writeUTF(read16.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read16.close();
                done = true;



        break;

   case 4:
	dOut.writeByte(1);
        Process proc2 = Runtime.getRuntime().exec("/root/bash_scripts/test3.sh");
        BufferedReader read2 = new BufferedReader(new InputStreamReader(
                    proc2.getInputStream()));
        try {
                        proc2.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read2.ready()) {
                         dOut.writeUTF(read2.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read2.close();
                done = true;

        break;
    case 5:
	dOut.writeByte(1);
        Process proc3 = Runtime.getRuntime().exec("/root/bash_scripts/test4.sh");
        BufferedReader read3 = new BufferedReader(new InputStreamReader(
                    proc3.getInputStream()));
        try {
                        proc3.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read3.ready()) {
                         dOut.writeUTF(read3.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read3.close();
                done = true;

        break;
    case 6:
        dOut.writeByte(1);
        Process proc4 = Runtime.getRuntime().exec("/root/bash_scripts/test5.sh");
        BufferedReader read4 = new BufferedReader(new InputStreamReader(
                    proc4.getInputStream()));
        try {
                        proc4.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read4.ready()) {
                         dOut.writeUTF(read4.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read4.close();
                done = true;

        break;

    case 7:
        dOut.writeByte(1);
        Process proc5 = Runtime.getRuntime().exec("/root/bash_scripts/test6.sh");
        BufferedReader read5 = new BufferedReader(new InputStreamReader(
                    proc5.getInputStream()));
        try {
                        proc5.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read5.ready()) {
                         dOut.writeUTF(read5.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read5.close();
                done = true;

        break;
    case 8:
        dOut.writeByte(1);
        Process proc6 = Runtime.getRuntime().exec("/root/bash_scripts/test7.sh");
        BufferedReader read6 = new BufferedReader(new InputStreamReader(
                    proc6.getInputStream()));
        try {
                        proc6.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read6.ready()) {
                         dOut.writeUTF(read6.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read6.close();
                done = true;

        break;

      case 9:
        dOut.writeByte(1);
	String command = dIn.readUTF();
	String command2 = command;
	String command3 = "/root/bash_scripts/test8.sh";
	dOut.writeUTF(command3);
        //Process proc7 = Runtime.getRuntime().exec('/root/bash_scripts/test8.sh "' + command +'"');
        //Process proc7 = Runtime.getRuntime().exec(command3);
	ProcessBuilder proc71 = new ProcessBuilder("sh", command3,command2);
	Process proc7 = proc71.start();
	BufferedReader read7 = new BufferedReader(new InputStreamReader(
                    proc7.getInputStream()));
        try {
                        proc7.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read7.ready()) {
                         dOut.writeUTF(read7.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read7.close();
                done = true;

        break;
	
	case 10:
        dOut.writeByte(1);
        //String command = dIn.readUTF();
        //dOut.writeUTF(command);
        //Process proc7 = Runtime.getRuntime().exec('/root/bash_scripts/test8.sh "' + command +'"');
        //Process proc7 = Runtime.getRuntime().exec(command3);
        //ProcessBuilder proc71 = new ProcessBuilder("sh", command);
        //Process proc7 = proc71.start();
        Process proc8 = Runtime.getRuntime().exec("/root/bash_scripts/test9.sh");
	 BufferedReader read8 = new BufferedReader(new InputStreamReader(
                    proc8.getInputStream()));
        try {
                        proc8.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read8.ready()) {
                         dOut.writeUTF(read8.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read8.close();
                done = true;

        break;
	
	case 11:
        dOut.writeByte(1);
        String commandd = dIn.readUTF();
	 String commandd2 = "/root/scripts/"+commandd;
        dOut.writeUTF(commandd2);
        //Process proc7 = Runtime.getRuntime().exec('/root/bash_scripts/test8.sh "' + command +'"');
        //Process proc7 = Runtime.getRuntime().exec(command3);
        //ProcessBuilder proc71 = new ProcessBuilder("sh", command);
        //Process proc7 = proc71.start();
        Process proc9 = Runtime.getRuntime().exec(commandd2);
         BufferedReader read9 = new BufferedReader(new InputStreamReader(
                    proc9.getInputStream()));
        try {
                        proc9.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }
                 while (read9.ready()) {
                         dOut.writeUTF(read9.readLine());
                        dOut.flush();
                }
         dOut.writeUTF("end");
            dOut.flush();
                read9.close();
                done = true;

        break;

	case 12:
        dOut.writeByte(1);
        byte type = dIn.readByte();
        String service = dIn.readUTF();
		
			switch(type){
				case 1:
				String command4 = "service "+ service +" start";

				Runtime.getRuntime().exec(command4);
				
				break;
				
				case 2:
				String command5 = "service "+ service +" stop";

                                Runtime.getRuntime().exec(command5);
				break;
				case 3: 
				String command6 = "service "+ service +" restart";

                                Runtime.getRuntime().exec(command6);
				break;
			}


		done = true;
        //dOut.writeUTF(command3);
        //Process proc7 = Runtime.getRuntime().exec('/root/bash_scripts/test8.sh "' + command +'"');
        //Process proc7 = Runtime.getRuntime().exec(command3);
        //ProcessBuilder proc71 = new ProcessBuilder("sh", command3);
        //Process proc7 = proc71.start();
        /*BufferedReader read7 = new BufferedReader(new InputStreamReader(
                    proc7.getInputStream()));*/
        /*try {
                        proc7.waitFor();
                 } catch (InterruptedException e) {
                         System.out.println(e.getMessage());
                 }*/
                 /*while (read7.ready()) {
                         dOut.writeUTF(read7.readLine());
                        dOut.flush();
                }*/
         //dOut.writeUTF("end");
            //dOut.flush();
                //read7.close();
                //done = true;

        break;



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
