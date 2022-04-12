package System;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class FileAction {
    public File target;
    public FileAction(String $target){
        this.target = new File($target);
    }

    public ArrayList<String> Input(){
        ArrayList<String> input = new ArrayList<String>();
        try{
            BufferedReader Read = new BufferedReader(new FileReader(this.target));
            IO.print("$ Loading File: "+this.target+"\n");
            String Get;
            while((Get=Read.readLine())!=null){
                input.add(Get);
            }
            IO.print("$ Loading of "+this.target+" done\n\n");
        } catch (IOException event) {
            event.printStackTrace();
        }
        return input;
    }
    public void Output(ArrayList<String> output){
        try{
            BufferedWriter Write = new BufferedWriter(new FileWriter(target));
            IO.print("$ Writing File: "+this.target+"\n");
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
