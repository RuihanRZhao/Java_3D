package System;

import Engine.texture;

import java.io.File;
import java.util.ArrayList;

public class map_read extends FileAction implements FileState{
    public int x,y;
    public int[][] map=null;
    public map_read(String $target){
        super($target);
        start(this);
        ArrayList<String> input = this.Input();
        String[] size=input.get(0).split(", ");
        x=Integer.parseInt(size[0]);
        y=Integer.parseInt(size[1]);
        map = new int[x][y];
        for(int i0 = 1; i0 < input.size(); i0++){
            String[] line=input.get(i0).split(", ");
            for(int i1=0; i1 < line.length; i1++){
                map[i0-1][i1]=Integer.parseInt(line[i1]);
            }
            process((float)((i0-1)*1.0 / (input.size()-1)));

        }
        process((float)(1.0));

        end(this,true);
    }

    @Override
    public void start(File target) {
        IO.print("$ Component: MAP\n" +
                "$ Start Input File: "+target.getName()+ "\n" +
                "          Location: "+target.getAbsolutePath()+"\n");
    }

    @Override
    public void process(float percent){
        try {
            if(percent==(float)0.0){
                IO.print("  [                         ] " + String.format(("% 3.2f"),percent*100)+"%");
            }
            else if(percent!=1.0){
                for(int i1=0;i1<40;i1++)IO.print("\b");
                StringBuilder output= new StringBuilder("  [");
                int i=0;
                for(;i<((int)(percent*100)/4);i++) output.append("|");
                for(;i<25;i++)output.append(" ");
                output.append("] " + String.format(("% 3.2f"),percent*100)+"%");
                IO.print(String.valueOf(output));


            } else{
                for(int i1=0;i1<40;i1++)IO.print("\b");
                IO.print("  [|||||||||||||||||||||||||] " + String.format(("% 3.2f"),percent*100)+"%");
            }

            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void end(File target, boolean result) {
        IO.print("\n  End Input File: "+target.getName()+ "\n" +
                 "          Result: "+((result)? ("Succeed") : ("Failed"))+"\n\n");
    }



    public static void main(String[] args){//for testing usage
        map_read a=new map_read("src/storage/map");
    }
}
