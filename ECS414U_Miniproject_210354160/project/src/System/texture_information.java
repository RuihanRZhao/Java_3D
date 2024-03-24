package System;

import Engine.texture;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class texture_information extends FileAction implements FileState{
    public int total;
    public ArrayList<texture> Index;
    public texture_information(String $target) {
        super($target);
        start(this);
        this.Index=new ArrayList<texture>();
        ArrayList<String> input = this.Input();
        this.total = Integer.parseInt(input.get(0));
        for(int i = 1;i < input.size();i++){
            String[] Line = input.get(i).split(", ");
            try {
                Index.add(new texture(Line[0],Line[1],Integer.parseInt(Line[2])));
            } catch (IOException e) {
                end(this, false);
                throw new RuntimeException(e);
            }
            process((float)((i-1)/(input.size()-2)));
        }
        end(this,true);
    }


    public static void main(String[] args){//for testing usage
        texture_information a=new texture_information("src/storage/texture");
    }

    @Override
    public void start(File target) {
        IO.print("$ Component: Texture List\n" +
                "$ Start Input File: "+target.getName()+ "\n" +
                "          Location: "+target.getAbsolutePath()+"\n");
    }

    @Override
    public void process(float percent){
        int SLP=10;
        try {
            if(percent==(float)0.0){
                IO.print("  [                         ] " + String.format(("% 3.2f"),percent*100)+"%");
                Thread.sleep(SLP);
                for(int i1=0;i1<150;i1++)IO.print("\b");
            }
            else if(percent<1.0){
                StringBuilder output= new StringBuilder("  [");
                int i=0;
                for(;i<((int)(percent*100)/4);i++) output.append("|");
                for(;i<25;i++)output.append(" ");
                output.append("] " + String.format(("% 3.2f"),percent*100)+"%");
                IO.print(String.valueOf(output));
                Thread.sleep(SLP);
                for(int i1=0;i1<150;i1++)IO.print("\b");
            } else{

                IO.print("  [|||||||||||||||||||||||||] " + String.format(("% 3.2f"),percent*100)+"%");
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void end(File target, boolean result) {
        IO.print("\n  End Input File: "+target.getName()+ "\n" +
                "          Result: "+((result)? ("Succeed") : ("Failed"))+"\n\n");
    }
}
