package Engine;

import System.*;

import java.awt.*;

public class screen {
    public static Map map;
    public static texture_information tex;

    public static int stepX, stepY;
    public static double sideDistX, sideDistY;
    public static double deltaDistX, deltaDistY;

    public screen(Map $map, texture_information $tex) {
        map = $map;
        tex = $tex;
    }

    public static int[] update(camera cam, int[] pixel){
        // sky and floor
        for (int i = 0; i < pixel.length / 2; i++) pixel[i] = new Color(128, 207, 236).getRGB();
        for (int i = pixel.length / 2; i < pixel.length; i++) pixel[i] = new Color(169, 131, 72).getRGB();

        for (int i0=0; i0< map.Width; ++i0){
            double camX = 2* i0 / (double) (map.Width) -1;

            double rayDirX = cam.P.x.direction + cam.P.x.plane * camX;
            double rayDirY = cam.P.y.direction + cam.P.y.plane * camX;
            
            int mapX = (int) cam.P.x.position;
            int mapY = (int) cam.P.y.position;

            deltaDistX = Math.sqrt(1 + (rayDirY * rayDirY) / (rayDirX * rayDirX));
            deltaDistY = Math.sqrt(1 + (rayDirX * rayDirX) / (rayDirY * rayDirY));
            double perpWallDist;

            boolean hit = false;
            int side = 0;

            if ((rayDirX < 0)) setX(-1, (cam.P.x.position - mapX) * deltaDistX);
            else setX(1, (mapX + 1.0 - cam.P.x.position) * deltaDistX);
            if (rayDirY < 0) setY(-1,  sideDistY = (cam.P.y.position - mapY) * deltaDistY);
            else setY(1, (mapY + 1.0 - cam.P.y.position) * deltaDistY);

            while (!hit) {
                if (sideDistX < sideDistY) {
                    sideDistX += deltaDistX;
                    mapX += stepX;
                    side = 0;
                } else {
                    sideDistY += deltaDistY;
                    mapY += stepY;
                    side = 1;
                }
                if (map.map[mapX][mapY] > 0) hit = true;
            }

            if (side == 0) perpWallDist = Math.abs((mapX - cam.P.x.position + (1 - stepX) / 2.0) / rayDirX);
            else perpWallDist = Math.abs((mapY - cam.P.y.position + (1 - stepY) / 2.0) / rayDirY);

            int lineHeight;
            if (perpWallDist > 0) lineHeight = Math.abs((int) (map.Height / perpWallDist));
            else lineHeight = map.Height;

            int drawStart = -lineHeight / 2 + map.Height / 2;
            if (drawStart < 0)
                drawStart = 0;
            int drawEnd = lineHeight / 2 + map.Height / 2;
            if (drawEnd >= map.Height)
                drawEnd = map.Height - 1;

            int texNum = map.map[mapX][mapY] - 1;
            double wallX;
            if (side == 1) {
                wallX = (cam.P.x.position + ((mapY - cam.P.y.position + (1 - stepY) / 2.0) / rayDirY) * rayDirX);
            } else {
                wallX = (cam.P.y.position + ((mapX - cam.P.x.position + (1 - stepX) / 2.0) / rayDirX) * rayDirY);
            }
            wallX -= Math.floor(wallX);

            int texX = (int) (wallX * (tex.Index.get(texNum).size));

            if (side == 0 && rayDirX > 0) texX = tex.Index.get(texNum).size - texX - 1;
            if (side == 1 && rayDirY < 0) texX = tex.Index.get(texNum).size - texX - 1;

            for (int i1 = drawStart; i1 < drawEnd; i1++) {
                int texY = (((i1 * 2 - map.Height + lineHeight) << 6) / lineHeight) / 2;
                int color;
                if (side == 0) color = tex.Index.get(texNum).pixels[texX + (texY * tex.Index.get(texNum).size)];
                else color = (tex.Index.get(texNum).pixels[texX + (texY * tex.Index.get(texNum).size)] >> 1) & 8355711;
                pixel[i0 + i1 * (map.Width)] = color;
            }
        }
        return pixel;
    }

    private static void setX(int step, double sideDist){
        stepX = step;
        sideDistX = sideDist;
    }
    private static void setY(Integer step, double sideDist){
        stepY = step;
        sideDistY = sideDist;
    }
}
