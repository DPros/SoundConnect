import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;


public class ModelController {
	
	
	private Model model;
	ModelController(){
		
	}
	public void train(){
		System.out.println("Model is trainig:");
		model=new Model("D:\\.android\\AudioConverter\\src\\fft.py");
		System.out.println("Finished!')");
	}
	public void test(){
		System.out.println("Model is testing:");
		model=new Model("D:\\.android\\AudioConverter\\src\\01_fft_based_classifier.py");
		System.out.println("Finished!')");
		
	}
	public void test(String pathToTest){
		System.out.println("Model is testing:");
		model=new Model("D:\\.android\\AudioConverter\\bin\\01_fft_based_classifier.py",pathToTest);
		System.out.println("Finished!2')");
	}
	public void convert(String sourcemp3,String outwav){
		//AudioConverter ac=new AudioConverter();
		String x[]=new String[2];
		x[0]=sourcemp3;
		
		x[1]=outwav;
		//ac.main(x);
		Converter c=new Converter();
		try {
			c.convert(x[0],x[1]);

		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void trim(String pathInput ,String pathOutput,int interval){
		String  command=" ffmpeg -t "+ interval+" -i "+pathInput+" -acodec copy "+ pathOutput;
		Runtime rt=Runtime.getRuntime();
		try {
			Process p =rt.exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		ModelController mc=new ModelController();
		mc.test("D:\\.android\\AudioConverter\\test1.wav");
		Scanner sc;
		try {
			sc = new Scanner(new File("D:\\.android\\AudioConverter\\src\\Output.txt"));
			while(sc.hasNext()){
				System.out.println(sc.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//mc.convert();
		//mc.trim("D:\\.android\\AudioConverter\\src\\testSample.mp3","D:\\.android\\AudioConverter\\src\\out.mp3",30);
		// TODO Auto-generated method stub

	}

}
