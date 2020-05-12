package tweetme;


import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

public class Log {


    
    public Log(String logText)
    {
        try{
         
            File file = new File("log.txt");
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(new java.util.Date() + ": " + logText + "\r");
            bw.flush();
            bw.close();
        }catch(IOException e){
        e.printStackTrace();
        }        
    }


}