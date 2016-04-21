import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.*;

public class Model {
 
	private Runtime rt;
	private String[] cmd;
	private String genre="";
	private String [] genres={"classical","jazz","country","pop","rock","metal","disco","reggae","blues","hiphop"};
	
	
	
	
	
	Model(String pythonScriptPath){
		
		cmd=new String[2];
		rt=Runtime.getRuntime();
		cmd[0] = "python"; // check version of installed python: python -V
		cmd[1] = pythonScriptPath;}
	Model(String pythonScriptPath,String pathToTest){
		
		cmd=new String[3];
		rt=Runtime.getRuntime();
		cmd[0] = "python"; // check version of installed python: python -V
		cmd[1] = pythonScriptPath;
		cmd[2]=pathToTest;
		for(String s:cmd)System.out.println(s);
		exec();
		}
	public void exec(){
		
		try {
			Process pr=rt.exec(cmd);
			giveOutput(pr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private boolean matches(String line){
		for(String s:genres){
			if(line.compareTo(s)==0)return true;
		}
		return false;
	}
	private  static void giveOutput(Process pr){
		BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(pr.getInputStream()));
		 BufferedReader stdError = new BufferedReader(new 

	             InputStreamReader(pr.getErrorStream()));

		
		try {
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			   //if(matches(s))setGenre(s);
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setGenre(String  genre){
		this.genre=genre;
	}
	public String getGenre(){
		return this.genre;
	}
/**
* @param args
* @throws IOException
*/
public static void main(String[] args) throws IOException {
	Runtime rt =Runtime.getRuntime();
	String cmd="python D:\\Project\\01_fft_based_classifier.py D:\\.android\\AudioConverter\\test1.wav";
	Process pr=rt.exec(cmd);
	giveOutput(pr);
	
	
}
}
