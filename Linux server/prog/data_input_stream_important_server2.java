
import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;


public class data_input_stream_important_server2 {

	static ServerSocket ss;
	static Socket s;
    static Process proc;
	static BufferedReader read;
	static DataInputStream dIn;
	static DataOutputStream dOut;
	
	
	public static void get_data(String script){
		try {
		try {
			proc = Runtime.getRuntime().exec(script);
			read = new BufferedReader(new InputStreamReader(proc.getInputStream()));							
			proc.waitFor();
		}catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
								
		while (read.ready()) {
			dOut.writeUTF(read.readLine());
			dOut.flush();
		}
															
		dOut.writeUTF("end");
		dOut.flush();
		read.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}						
	}
 

    public static void main(String[] args) {
		

		boolean done2 = false;
		while(!done2){
			
			try{
				
				ss=new ServerSocket(1045);
				s=ss.accept();
				dIn = new DataInputStream(s.getInputStream());
				dOut = new DataOutputStream(s.getOutputStream());
				boolean done = false;
				
					while(!done) {
						byte messageType = dIn.readByte();
						
						switch(messageType){

							case 2: 
								get_data("/root/bash_scripts/test1.sh");
								done = true;

							break;
							
							
							case 3: // Harddrives last version
								dOut.writeByte(1);
								get_data("/root/bash_scripts/test10.sh");
								get_data("/root/bash_scripts/test2.sh");
								done = true;
								
							break;

							case 4:  // Netowrk ports
								dOut.writeByte(1);
								get_data("/root/bash_scripts/test3.sh");
								done = true;

							break;
    
	
							case 5: //Teenused
								dOut.writeByte(1);
        						get_data("/root/bash_scripts/test4.sh");
								done = true;

							break;
							
							
							case 6: // Protssor
								dOut.writeByte(1);
                				get_data("/root/bash_scripts/test5.sh");
								done = true;

							break;
						
						
							case 7: // RAM
								dOut.writeByte(1);
                				get_data("/root/bash_scripts/test6.sh");
								done = true;

							break;
							
							
							case 8:  // Network
								dOut.writeByte(1);	
								get_data("/root/bash_scripts/test7.sh");
								done = true;

							break;
							

							case 9:  //Command
								dOut.writeByte(1);
								String command = dIn.readUTF();
								String command2 = command;
								String command3 = "/root/bash_scripts/test8.sh";
								dOut.writeUTF(command3);
        
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
							
						
							case 10: //Scripts listing
								dOut.writeByte(1);
								get_data("/root/bash_scripts/test9.sh");
								done = true;

							break;
	
							case 11: // Run script
								dOut.writeByte(1);
								String commandd = dIn.readUTF();
								String commandd2 = "/root/scripts/"+commandd;
								dOut.writeUTF(commandd2);
     
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

								
							case 12: // Teenused
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
							break;
							/*
							case 13: //space
							//dOut.writeByte(1);

							String command90 = dIn.readUTF();

							
							
								Process proc100 = Runtime.getRuntime().exec(command90);
								BufferedReader read100 = new BufferedReader(new InputStreamReader(
								proc100.getInputStream()));
								
								try {
									proc100.waitFor();
								} catch (InterruptedException e) {
									System.out.println(e.getMessage());
								}
								
								while (read100.ready()) {
								System.out.println(read100.readLine());

									dOut.writeUTF(read100.readLine());
									dOut.flush();
								}
								/*
								ProcessBuilder proc100 = new ProcessBuilder("sh", "df","--output=used /dev/sda1 | tail -1");
								Process proc700 = proc100.start();
								BufferedReader read700 = new BufferedReader(new InputStreamReader(
								proc700.getInputStream()));
								
								try {
									proc700.waitFor();
								} catch (InterruptedException e) {
									System.out.println(e.getMessage());
								}
								
								while (read700.ready()) {
									dOut.writeUTF(read700.readLine());
									dOut.flush();
								}
								
								
								
								dOut.writeUTF("end");
								dOut.flush();
								read100.close();
								done = true;
							break;*/
							

						}
					}
					
					
				dIn.close();
				dOut.close();
				ss.close();
				s.close();
				
			}catch (Exception e) {
                System.out.println(e);
			}
		}
    }
}
