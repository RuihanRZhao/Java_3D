import System.*;

import java.util.ArrayList;

public class Craft {

    public static void main(String[] args) {
        FileAction test=new FileAction("src\\storage\\map.json");
        ArrayList<String> a=test.Input();
        for(String i: a){
            IO.print(i+"\n");
        }
    }
}
