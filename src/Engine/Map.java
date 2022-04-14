package Engine;

import System.*;

import java.io.File;
import java.io.IOException;

public class Map {
    public int[][] map;
    public int Width = 1600, Height = 900;
    public Map(){
        map= new map_read("src/storage/map").map;

    }
    public int[][] MapPrint(){
        return this.map;
    }
}
