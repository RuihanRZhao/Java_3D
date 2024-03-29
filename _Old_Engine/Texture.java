//Done
package _Old_Engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

public class Texture {
    public int[] pixels;
    private String loc;
    public final int SIZE;

    public Texture(String location, int size) {
        loc = location;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(new File((getClass().getResource(loc)).toURI()));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Texture wood = new Texture("image/wood.png", 64);
    public static Texture brick = new Texture("image/brick.png", 64);
    public static Texture bluestone = new Texture("image/glass.png", 64);
    public static Texture stone = new Texture("image/walkstone.png", 64);
}

