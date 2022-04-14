package Engine;

/***************
 * Import Part *
 ***************/

import System.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class control extends JFrame implements Runnable{
    private static final long serialVersionUID=1L;
    // The range of the map
    private Thread Graph_Thread;
    private Boolean statue;
    private BufferedImage image;
    public int[] pixels;
    public texture_information texture_List;
    public camera Camera;
    public position Position;

    public screen Screen;
    public Map map=new Map();

    public control(Map target, int screen_width, int screen_height){
        //Initialize
        Graph_Thread = new Thread(this);                                                      //Thread
        image = new BufferedImage(screen_width, screen_height, BufferedImage.TYPE_INT_RGB);         //Image
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        texture_List = new texture_information("src/storage/texture");
        Position = new position(24.5,49.5,0,1,0,0,0,-0.577 ,0);
        Camera = new camera(Position);
        Screen = new screen(map,texture_List);

        addKeyListener(Camera);
        setSize(map.Width,map.Height);
        setResizable(false);
        setTitle("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(47,207,255));
        setLocationRelativeTo(null);
        setVisible(true);
        start();
    }
    private synchronized void start() {
        statue = true;



        Graph_Thread.start();
    }
    public synchronized void stop() {
        statue = false;
        try {
            Graph_Thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        bs.show();
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;//60 times per second
        double delta = 0;
        requestFocus();
        while(statue) {
            long now = System.nanoTime();
            delta = delta + ((now-lastTime) / ns);
            lastTime = now;
            while (delta >= 1){//Make sure update is only happening 60 times a second
                //handles all of the logic restricted time
                screen.update(Camera, pixels);
                camera.update(map.map);
                delta--;
            }
            render();//displays to the screen unrestricted time
        }
    }
}
