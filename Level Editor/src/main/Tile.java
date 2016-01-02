package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Blake on 12/30/2015.
 */
public class Tile {
    private Rectangle r;
    private int x, y, width, length, id;
    private Image i;
    int b=0 ,v=0, d=0;


    public Tile(int x, int y, int width, int length) {
        this.width = width;
        this.length = length;
        this.x = x;
        this.y = y;


        r = new Rectangle(x, y, width, length);


    }


    public void setImage(String path){
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getIntLength(int a){
        return Integer.toHexString(a).length();
    }
    public void setImage(Image i){
        this.i = i;
    }
    public Image getI(){
        return i;
    }

    public void setTileID(int id) {
        this.id = id;
        int length = getIntLength(id);

        if(length == 3) {
            b = (id & 0xf00) >> 8;
            if(b != LevelEditor.LEVELCHANGER) {
                v = (id & 0xf0) >> 4;
                d = (id & 0xf);
            }else{
                v=0;
                d=0;
            }
        }
        if(length == 1){
            b = id;
        }
        if(length == 2){
            b = (id&0xf0) >> 4;;
            d = 0;
        }
        if(length == 5){
            b = (id&0xf0000) >>16;
            v = (id&0xf000)  >>12;
            d = (id&0xf00)   >>8;

        }

    }
    public int getID(){
        return id;
    }

    public void drawTile(Graphics2D g2d) {
        g2d.drawImage(i,x,y,x+width,y+length, d*16,b*16,d*16+16,b*16+16,null);

    }

    public Rectangle getRectangle() {
        return r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
