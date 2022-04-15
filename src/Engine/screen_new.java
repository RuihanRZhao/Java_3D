package Engine;

import System.*;

public class screen_new {
    public static Map map;
    public static texture_information tex;

    public screen_new(Map $map, texture_information $tex) {
        this.map = $map;
        this.tex = $tex;
    }

    public static int[] update(camera cam, int[] pixel){
        return pixel;
    }
}
