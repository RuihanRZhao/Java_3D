package Engine;

import System.IO;

import java.awt.event.*;

public class camera implements KeyListener, MouseListener, MouseMotionListener {
    public static position P;
    public static boolean left;
    public static boolean right;
    public static boolean forward;
    public static boolean back;
    public boolean Jump;
    public static final double MOVE_SPEED = .08;
    public static final double ROTATION_SPEED= .045;
    public static double x_Rotate, y_Rotate;
    public static double mouse_x, mouse_y;
    public camera(position input){
        this.P=input;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)Jump = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_A) -> left = true;
            case (KeyEvent.VK_D) -> right = true;
            case (KeyEvent.VK_W) -> forward = true;
            case (KeyEvent.VK_S) -> back = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_A) -> left = false;
            case (KeyEvent.VK_D) -> right = false;
            case (KeyEvent.VK_W) -> forward = false;
            case (KeyEvent.VK_S) -> back = false;
        }
    }




    @Override
    public void mouseClicked(MouseEvent e) {
        x_Rotate=0;
        y_Rotate=0;
        int x = e.getX(), y = e.getY();
        x_Rotate = mouse_x - x;
        y_Rotate = mouse_y - y;
        mouse_x=x;
        mouse_y=y;
        IO.print("1\n"+"x: "+mouse_x+"\ty: "+mouse_y+"\n\n");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x_Rotate=0;
        y_Rotate=0;
        int x = e.getX(), y = e.getY();
        x_Rotate = mouse_x - x;
        y_Rotate = mouse_y - y;
        mouse_x=x;
        mouse_y=y;
        IO.print("2\n"+"x: "+mouse_x+"\ty: "+mouse_y+"\n\n");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x_Rotate=0;
        y_Rotate=0;
        int x = e.getX(), y = e.getY();
        x_Rotate = mouse_x - x;
        y_Rotate = mouse_y - y;
        mouse_x=x;
        mouse_y=y;
        IO.print("3\n"+"x: "+mouse_x+"\ty: "+mouse_y+"\n\n");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        x_Rotate=0;
        y_Rotate=0;
        int x = e.getX(), y = e.getY();
        x_Rotate = mouse_x - x;
        y_Rotate = mouse_y - y;
        mouse_x=x;
        mouse_y=y;
        IO.print("4\n"+"x: "+mouse_x+"\ty: "+mouse_y+"\n\n");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        x_Rotate=0;
        y_Rotate=0;
        int x = e.getX(), y = e.getY();
        x_Rotate = mouse_x - x;
        y_Rotate = mouse_y - y;
        mouse_x=x;
        mouse_y=y;
        IO.print("5\n"+"x: "+mouse_x+"\ty: "+mouse_y+"\n\n");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x_Rotate=0;
        y_Rotate=0;
        int x = e.getX(), y = e.getY();
        x_Rotate = mouse_x - x;
        y_Rotate = mouse_y - y;
        mouse_x=x;
        mouse_y=y;
        IO.print("6\n"+"x: "+mouse_x+"\ty: "+mouse_y+"\n\n");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x_Rotate=0;
        y_Rotate=0;
        int x = e.getX(), y = e.getY();
        x_Rotate = mouse_x - x;
        y_Rotate = mouse_y - y;
        mouse_x=x;
        mouse_y=y;
        IO.print("7\n"+"x: "+mouse_x+"\ty: "+mouse_y+"\n\n");

    }
    public static void update(int[][] map){
        //position change by WASD

        double newX=0.0, newY=0.0;
        if(forward) { newX=P.x.direction;  newY=P.y.direction; }
        if(back)    { newX=-P.x.direction; newY=-P.y.direction;}
        if(left)    { newX=-P.y.direction; newY=P.x.direction; }
        if(right)   { newX=P.y.direction;  newY=-P.x.direction;}
        if(forward || back || left || right){
            if(map[(int)(P.x.position + newX * MOVE_SPEED)][(int)P.y.position] == 0) P.x.position+=newX*MOVE_SPEED;
            if(map[(int)P.x.position][(int)(P.y.position + newY * MOVE_SPEED)] ==0) P.y.position+=newY*MOVE_SPEED;
        }
        //direction change by moving mouse

        double oldxDir=P.x.direction;
        P.x.direction = P.x.direction*Math.cos(ROTATION_SPEED * x_Rotate) - P.y.direction*Math.sin(ROTATION_SPEED * x_Rotate);
        P.y.direction = oldxDir*Math.sin(ROTATION_SPEED * x_Rotate) + P.y.direction*Math.cos(ROTATION_SPEED * x_Rotate);
        double oldxPlane = P.x.plane;
        P.x.plane = P.x.plane*Math.cos(ROTATION_SPEED * x_Rotate) - P.y.plane*Math.sin(ROTATION_SPEED * x_Rotate);
        P.y.plane = oldxPlane*Math.sin(ROTATION_SPEED * x_Rotate) + P.y.plane*Math.cos(ROTATION_SPEED * x_Rotate);
    }
}
