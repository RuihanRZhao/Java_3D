package Engine;

import java.awt.event.*;
import System.IO;

public class camera implements KeyListener{
    public static position P;
    public boolean Jump;
    public static MotionTrend MT;

    public static final double MoveSpeed = .08;
    public static final double RotateSpeed= .045;
    public camera(position input){
        this.P = input;
        this.MT=new MotionTrend();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)Jump = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_A):     MT.move.left    = true; break;
            case (KeyEvent.VK_D):     MT.move.right   = true; break;
            case (KeyEvent.VK_W):     MT.move.up      = true; break;
            case (KeyEvent.VK_S):     MT.move.down    = true; break;
            case (KeyEvent.VK_LEFT):  MT.point.left   = true; break;
            case (KeyEvent.VK_RIGHT): MT.point.right  = true; break;
            case (KeyEvent.VK_UP):    MT.point.up     = true; break;
            case (KeyEvent.VK_DOWN):  MT.point.down   = true; break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_A):     MT.move.left    = false; break;
            case (KeyEvent.VK_D):     MT.move.right   = false; break;
            case (KeyEvent.VK_W):     MT.move.up      = false; break;
            case (KeyEvent.VK_S):     MT.move.down    = false; break;
            case (KeyEvent.VK_LEFT):  MT.point.left   = false; break;
            case (KeyEvent.VK_RIGHT): MT.point.right  = false; break;
            case (KeyEvent.VK_UP):    MT.point.up     = false; break;
            case (KeyEvent.VK_DOWN):  MT.point.down   = false; break;
        }
    }

    public static void update(int[][] map){
        // position change by WASD
        if(MT.move.up)    makeMove(map, P.x.direction, P.y.direction);
        if(MT.move.down)  makeMove(map, -P.x.direction, -P.y.direction);
        if(MT.move.left)  makeMove(map, -P.y.direction, P.x.direction);
        if(MT.move.right) makeMove(map, P.y.direction, -P.x.direction);
        // direction change by moving mouse
        if(MT.point.left) makeRotate(RotateSpeed);
        if(MT.point.right) makeRotate(-RotateSpeed);

    }
    public static void makeRotate(double speed){
        double oldxDir=P.x.direction;
        P.x.direction = P.x.direction*Math.cos(speed) - P.y.direction*Math.sin(speed);
        P.y.direction = oldxDir*Math.sin(speed) + P.y.direction*Math.cos(speed);
        double oldxPlane = P.x.plane;
        P.x.plane = P.x.plane*Math.cos(speed) - P.y.plane*Math.sin(speed);
        P.y.plane = oldxPlane*Math.sin(speed) + P.y.plane*Math.cos(speed);


        IO.print("$ Direction: ("+P.x.direction+", "+P.y.direction+") \n\t（"+(float)P.x.plane+", "+(float)P.y.plane+"）\n\n");
    }
    public static void makeMove(int[][] map, double X, double Y){
        if(map[(int)(P.x.position + X * MoveSpeed)][(int)P.y.position] == 0) P.x.position+=X* MoveSpeed;
        if(map[(int)P.x.position][(int)(P.y.position + Y * MoveSpeed)] ==0) P.y.position+=Y* MoveSpeed;


        IO.print("$ Direction: ("+P.x.direction+", "+P.y.direction+") \n\t（"+(float)P.x.plane+", "+(float)P.y.plane+"）\n\n");
    }
}
class MotionTrend{
    public direction move;
    public direction point;
    public MotionTrend(){
        this.move = new direction();
        this.point = new direction();
    }
}
class direction{
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    public direction(){
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
    }
}