import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class readBashScript {
	public static void main(String[] args) {
        try {
	    Socket SOCK = new Socket("192.168.1.210",1045);
	
  	     Process proc = Runtime.getRuntime().exec("/root/bash_scripts/test1.sh"); //Whatever you want to execute
            PrintStream PS = new PrintStream(SOCK.getOutputStream());
            String okei = "NIISAMA";	
	    /* PS.println(okei);
            SOCK.close();
	    */
	

	    BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            	try {
                	proc.waitFor();
           	 } catch (InterruptedException e) {
               		 System.out.println(e.getMessage());
           	 }
           	 while (read.ready()) {
               		 PS.println(read.readLine());
           	 }
	
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}	
