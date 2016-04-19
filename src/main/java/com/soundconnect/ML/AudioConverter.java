

import java.io.*;
import java.util.Scanner;

import javax.sound.sampled.*;

public class AudioConverter{
	
	

  public static void main(String[] args){
	 
	  
    if(args.length != 2){
      System.out.println(
                "Usage: java AudioFileConvert02 "
                       + "inputFile outputFile");
      System.exit(0);
    }//end if

    AudioFileFormat.Type outputType =
         getTargetType(args[1].substring(args[1].
                          lastIndexOf(".") + 1));

    if(outputType == null){
      System.out.println(
                   "Output type not supported.");
      System.exit(0);
    }//end else

    File inputFileObj = new File(args[0]);
    AudioInputStream audioInputStream = null;
    try{
      audioInputStream = AudioSystem.
               getAudioInputStream(inputFileObj);

      AudioSystem.write(audioInputStream,
                              outputType,
                              new File(args[1]));
    }catch (Exception e){
      e.printStackTrace();
      System.exit(0);
    }//end catch

  }//end main
  //-------------------------------------------//

  private static AudioFileFormat.Type
                 getTargetType(String extension){
    AudioFileFormat.Type[] typesSupported =
                 AudioSystem.getAudioFileTypes();
    for(int i = 0; i < typesSupported.length;
                                            i++){
      if(typesSupported[i].getExtension().
                              equals(extension)){
        return typesSupported[i];
      }//end if
    }//end for loop
    return null;//no match
  }//end getTargetType
  //-------------------------------------------//
}//end class