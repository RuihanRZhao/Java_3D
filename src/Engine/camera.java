package Engine;

import System.IO;

import java.awt.event.*;

public class camera implements KeyListener{
    public static position P;
    public boolean Jump;
    public static MotionTrend MT;

    public static final double MOVE_SPEED = .08;
    public static final double ROTATION_SPEED= .045;
    public static double x_Rotate, y_Rotate;
    public static double mouse_x, mouse_y;
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
            case (KeyEvent.VK_A) -> MT.move.left = true;
            case (KeyEvent.VK_D) -> MT.move.right = true;
            case (KeyEvent.VK_W) -> MT.move.up = true;
            case (KeyEvent.VK_S) -> MT.move.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_A) -> MT.move.left = false;
            case (KeyEvent.VK_D) -> MT.move.right = false;
            case (KeyEvent.VK_W) -> MT.move.up = false;
            case (KeyEvent.VK_S) -> MT.move.down = false;
        }
    }

    public static void update(int[][] map){
        //position change by WASD

        if(MT.move.up)      {
            makeMove(map, P.x.direction, P.y.direction);
        }
        if(MT.move.down)    {

        }
        if(MT.move.left)    {
            makeMove(map, -P.x.direction, P.y.direction);
        }
        if(MT.move.right)   {
            makeMove(map, P.x.direction, -P.y.direction);
        }
        //direction change by moving mouse

        double oldxDir=P.x.direction;
        P.x.direction = P.x.direction*Math.cos(ROTATION_SPEED * x_Rotate) - P.y.direction*Math.sin(ROTATION_SPEED * x_Rotate);
        P.y.direction = oldxDir*Math.sin(ROTATION_SPEED * x_Rotate) + P.y.direction*Math.cos(ROTATION_SPEED * x_Rotate);
        double oldxPlane = P.x.plane;
        P.x.plane = P.x.plane*Math.cos(ROTATION_SPEED * x_Rotate) - P.y.plane*Math.sin(ROTATION_SPEED * x_Rotate);
        P.y.plane = oldxPlane*Math.sin(ROTATION_SPEED * x_Rotate) + P.y.plane*Math.cos(ROTATION_SPEED * x_Rotate);
    }

    public static void makeMove(int[][] map, double X, double Y){
        if(map[(int)(P.x.position + X * MOVE_SPEED)][(int)P.y.position] == 0) P.x.position+=X*MOVE_SPEED;
        if(map[(int)P.x.position][(int)(P.y.position + Y * MOVE_SPEED)] ==0) P.y.position+=Y*MOVE_SPEED;
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
    public boolean haveTrend(){
        return (this.left || this.right || this.down || this.up);
    }
}