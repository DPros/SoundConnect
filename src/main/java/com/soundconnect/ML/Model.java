import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.*;

public class Model {
 
	private Runtime rt;
	private String[] cmd;
	
	
	
	
	Model(String pythonScriptPath){
		cmd=new String[2];
		rt=Runtime.getRuntime();
		cmd[0] = "python"; // check version of installed python: python -V
		cmd[1] = pythonScriptPath;}
	public void exec(){
		
		try {
			Process pr=rt.exec(cmd);
			giveOutput(pr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void giveOutput(Process pr){
		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		try {
			while((line = bfr.readLine()) != null) {
			// display each output line form python script
			System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
/**
* @param args
* @throws IOException
*/
public static void main(String[] args) throws IOException {
	
}
}
