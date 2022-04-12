package Engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import System.*;

public class texture {
    public int[] pixels;
    public String name;
    private File location;
    public final int size;

    public texture(String name, String locate, int size){
        this.location = new File(locate);
        this.size = size;
        pixels = new int[size*size];
        Load();
    }
    private void Load() {
        try{
            BufferedImage image= ImageIO.read(location);
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
            IO.print("$ Loading image "+location+" succeed.\n");
        } catch(IOException event){
            IO.print("$ Loading image "+location+" loading failed.\n\n");
            event.printStackTrace();
        }
    }

    public static ArrayList<texture> Texture_Input(int No, String[] name, String[] locate, int[] size){
        ArrayList<texture> output=new ArrayList<texture>();
            for(int i = 0; i < No; i++){
                IO.print("$ Loading texture: "+locate[i]+"\n name: "+name[i]+"\n");
                output.add(new texture(name[i], locate[i], size[i]));
                IO.print("$ Texture "+output.get(output.indexOf(i))+" loaded.\n\n");
            }
        return output;
    }
}
