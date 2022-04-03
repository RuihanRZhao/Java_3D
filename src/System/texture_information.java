package System;

import Engine.texture;

import java.util.ArrayList;

public class texture_information extends FileAction {
    public int total;
    public ArrayList<texture> Index;
    public texture_information(String $target) {
        super($target);
        this.Index=new ArrayList<texture>();
        ArrayList<String> input = this.Input();
        this.total = Integer.parseInt(input.get(0));
        for(int i = 1;i < input.size();i++){
            String[] Line = input.get(i).split(", ");
            Index.add(new texture(Line[0],Line[1],Integer.parseInt(Line[2])));
        }
    }


    public static void main(String[] args){//for testing usage
        texture_information a=new texture_information("src/storage/texture");
    }
}
