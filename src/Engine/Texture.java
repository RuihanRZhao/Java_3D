package Engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class texture {
    public int[] pixels;
    private File location;
    public final int size;

    public texture(String locate, int size){
        this.location = new File(locate);
        this.size = size;
        pixels = new int[size*size];
        Load();
    }
    private void Load() {
        try{
            BufferedImage image= ImageIO.read(location);
            image.getRGB(0,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
        } catch(IOException event){
            event.printStackTrace();
        }
    }

    public static ArrayList<texture> Texture_Input(int No, String[] locate, int[] size){
        ArrayList<texture> output=new ArrayList<texture>();
            for(int i=0;i<No;i++){
                output.add(new texture(locate[i],size[i]));
            }
        return output;
    }
}
