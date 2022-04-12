package System;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class FileAction extends java.io.File {

    public FileAction(String $target){
        super($target);
    }

    public ArrayList<String> Input(){
        ArrayList<String> input = new ArrayList<String>();
        try{
            BufferedReader Read = new BufferedReader(new FileReader(this.getAbsoluteFile()));
            IO.print("$ Loading File: "+this.getPath()+"\n");
            String Get;
            while((Get=Read.readLine())!=null){
                input.add(Get);
            }
            IO.print("$ Loading of "+this.getPath()+" done\n\n");
        } catch (IOException event) {
            event.printStackTrace();
        }
        return input;
    }
    public void Output(ArrayList<String> output){
        try{
            BufferedWriter Write = new BufferedWriter(new FileWriter(this.getAbsoluteFile()));
            IO.print("$ Writing File: "+this.getPath()+"\n");
            String Get;
            for(String i : output){
                Write.write(i+"\n");
            }
            Write.close();
            IO.print("$ Done\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
