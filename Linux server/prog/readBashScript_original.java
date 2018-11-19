import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class readBashScript_original {
	public static void main(String[] args) {
        try {
            Process proc = Runtime.getRuntime().exec("/root/bash_scripts/test1.sh"); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            	try {
                	proc.waitFor();
           	 } catch (InterruptedException e) {
               		 System.out.println(e.getMessage());
           	 }
           	 while (read.ready()) {
               		 System.out.println(read.readLine());
           	 }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}	
