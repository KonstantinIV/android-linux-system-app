import java.io.File;
import java.lang.Math;


public class Disk_Space {
public static void main(String[] args) {
	//File f = new File("/dev/sda5");
	File f2 = new File("/");
	File b = new File("/root");
	long  c = b.getTotalSpace()/1024;
	//System.out.println(((f.getTotalSpace()/1024)/1024)/1024 +" G bytes");
	//System.out.println(f.getTotalSpace() +" G bytes555");
	//System.out.println(f.getFreeSpace() +" G bytes5555");
	 System.out.println(f2.getTotalSpace() +" G bytes1");
        System.out.println(f2.getFreeSpace() +" G bytes1");


	}
}
