import java.io.File;
import java.util.Scanner;


public class ConverterUsage {
	ConverterUsage(){}

	public static void main(String[] args) {
		AudioConverter ac=new AudioConverter();
		Scanner sc=new Scanner(System.in);
		System.out.println("Input  folder");
		String folderPath=sc.next();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		String[] x=new String[2];
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  x[0]=listOfFiles[i].getAbsolutePath();
		    	  x[1]=listOfFiles[i].getAbsolutePath().substring(listOfFiles[i].getAbsolutePath().length()-9)+".wav";
		        System.out.println("File " + listOfFiles[i].getName());
		        ac.main(x);
		        
		        ac.main(args);
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		
		// TODO Auto-generated method stub

	}

}
