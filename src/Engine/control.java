package Engine;

/***************
 * Import Part *
 ***************/
import Engine.texture;
import Data.Map;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;

public class control extends JFrame implements Runnable{
    private static final long serialVersionUID=1L;
    // The range of the map.json
    private int Width, Height;
    private Thread Graph_Thread;
    private Boolean statue;
    private BufferedImage image;
    public int[] pixels;
    public ArrayList<texture> texture_image;
    public camera Camera;
    public screen Screen;
    public static int[][] map;

    public control(Map target, int screen_width, int screen_height){
        //Initialize
        Graph_Thread = new Thread(this);                                                      //Thread
        image = new BufferedImage(screen_width, screen_height, BufferedImage.TYPE_INT_RGB);         //Image
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        texture_image = new ArrayList<texture>();


    }

    @Override
    public void run() {

    }
}
