package System;

import Engine.texture;

import java.util.ArrayList;

public class map_read extends FileAction {
    public int x,y;
    public int[][] map=null;
    public map_read(String $target) {
        super($target);
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

        }
    }


    public static void main(String[] args){//for testing usage
        map_read a=new map_read("src/storage/map");
    }
}
