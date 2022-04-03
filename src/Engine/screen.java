package Engine;

import System.*;

import java.awt.*;

public class screen {
    public static Map map;
    public static texture_information tex;
    public screen(Map $map, texture_information List){
        this.map = $map;
        this.tex = List;
    }
    public static int[] update(camera Cam, int[] pixels){
        Color back=new Color(47,207,255);
        for(int n=0; n<pixels.length/2; n++) {
            if(pixels[n] != back.getRGB()) pixels[n] = back.getRGB();
        }
        for(int i=pixels.length/2; i<pixels.length; i++){
            if(pixels[i] != back.getRGB()) pixels[i] = back.getRGB();
        }
        for(int x=0; x < map.Width; ++x){
            double cameraX = 2 * x / (double)(map.Width) -1;
            double rayDirX = Cam.P.x.direction + Cam.P.x.plane * cameraX;
            double rayDirY = Cam.P.y.direction + Cam.P.y.plane * cameraX;
            //Map position
            int mapX = (int)Cam.P.x.position;
            int mapY = (int)Cam.P.y.position;
            //length of ray from current position to next x or y-side
            double sideDistX;
            double sideDistY;
            //Length of ray from one side to next in map.json
            double deltaDistX = Math.sqrt(1 + (rayDirY*rayDirY) / (rayDirX*rayDirX));
            double deltaDistY = Math.sqrt(1 + (rayDirX*rayDirX) / (rayDirY*rayDirY));
            double perpWallDist;
            //Direction to go in x and y
            int stepX, stepY;
            boolean hit = false;//was a wall hit
            int side=0;//was the wall vertical or horizontal
            //Figure out the step direction and initial distance to a side
            if (rayDirX < 0) {
                stepX = -1;
                sideDistX = (Cam.P.x.position - mapX) * deltaDistX;
            }
            else {
                stepX = 1;
                sideDistX = (mapX + 1.0 - Cam.P.x.position) * deltaDistX;
            }
            if (rayDirY < 0) {
                stepY = -1;
                sideDistY = (Cam.P.y.position - mapY) * deltaDistY;
            }
            else {
                stepY = 1;
                sideDistY = (mapY + 1.0 - Cam.P.y.position) * deltaDistY;
            }
            //Loop to find where the ray hits a wall
            while(!hit) {
                //Jump to next square
                if (sideDistX < sideDistY) {
                    sideDistX += deltaDistX;
                    mapX += stepX;
                    side = 0;
                }
                else {
                    sideDistY += deltaDistY;
                    mapY += stepY;
                    side = 1;
                }
                //Check if ray has hit a wall
                //System.out.println(mapX + ", " + mapY + ", " + map.json[mapX][mapY]);
                if(map.map[mapX][mapY] > 0) hit = true;
            }
            //Calculate distance to the point of impact
            if(side==0)
                perpWallDist = Math.abs((mapX - Cam.P.x.position + (1 - stepX) / 2.0) / rayDirX);
            else
                perpWallDist = Math.abs((mapY - Cam.P.y.position + (1 - stepY) / 2.0) / rayDirY);
            //Now calculate the height of the wall based on the distance from the camera
            int lineHeight;
            if(perpWallDist > 0) lineHeight = Math.abs((int)(map.Height / perpWallDist));
            else lineHeight = map.Height;
            //calculate lowest and highest pixel to fill in current stripe
            int drawStart = -lineHeight/2+ map.Height/2;
            if(drawStart < 0)
                drawStart = 0;
            int drawEnd = lineHeight/2 + map.Height/2;
            if(drawEnd >= map.Height)
                drawEnd = map.Height - 1;
            //add a texture
            int texNum = map.map[mapX][mapY] - 1;
            double wallX;//Exact position of where wall was hit
            if(side==1) {//If its a y-axis wall
                wallX = (Cam.P.x.position + ((mapY - Cam.P.y.position + (1 - stepY) / 2) / rayDirY) * rayDirX);
            } else {//X-axis wall
                wallX = (Cam.P.y.position + ((mapX - Cam.P.x.position + (1 - stepX) / 2) / rayDirX) * rayDirY);
            }
            wallX-=Math.floor(wallX);
            //x coordinate on the texture
            int texX = (int)(wallX * (tex.Index.get(texNum).size));
            if(side == 0 && rayDirX > 0) texX = tex.Index.get(texNum).size - texX - 1;
            if(side == 1 && rayDirY < 0) texX = tex.Index.get(texNum).size - texX - 1;
            //calculate y coordinate on texture
            for(int y=drawStart; y<drawEnd; y++) {
                int texY = (((y*2 - map.Height + lineHeight) << 6) / lineHeight) / 2;
                int color;
                if(side==0) color = tex.Index.get(texNum).pixels[texX + (texY * tex.Index.get(texNum).size)];
                else color = (tex.Index.get(texNum).pixels[texX + (texY * tex.Index.get(texNum).size)]>>1) & 8355711;//Make y sides darker
                pixels[x + y*(map.Width)] = color;
            }
        }
        return pixels;
    }
}
