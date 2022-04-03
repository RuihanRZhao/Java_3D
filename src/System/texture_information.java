package System;

import java.util.ArrayList;

public class texture_information extends FileAction {
    public int total;
    public ArrayList<String> locate;
    public ArrayList<Integer> size;
    public texture_information(String $target) {
        super($target);
        this.locate = new ArrayList<String>();
        this.size = new ArrayList<Integer>();
        ArrayList<String> input = this.Input();
        this.total = Integer.parseInt(input.get(0));
        for(int i = 1;i < input.size();i++){
            String[] Line = input.get(i).split(", ");
            locate.add(Line[0]);
            size.add(Integer.parseInt(Line[1]));
            IO.print(locate.get(i-1)+" "+size.get(i-1)+"\n");
        }
    }

    public static void main(String[] args){//for testing usage
        texture_information a=new texture_information("src/storage/texture");
    }
}
