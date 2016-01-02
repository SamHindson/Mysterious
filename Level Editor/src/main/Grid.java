package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.logging.Level;

/**
 * Created by Blake on 12/30/2015.
 */
public class Grid {
    private Tile[][] g;
    private int length, width, size;
    private Rectangle selected = new Rectangle(0, 0, 0, 0), gridR;
    private Frame f;

    private int tileID;


    public Grid(Frame f, int x, int y, int width, int length, int size) {
        this.length = length;
        this.f = f;
        this.width = width;
        this.size = size;
        g = new Tile[width][length];
        gridR = new Rectangle(x - 10, y, width * size + 10, length * size);
        int x1 = x;
        int y1 = y;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                g[i][j] = new Tile(x1, y1, size, size);
                x1 += size;
            }
            x1 = x;
            y1 += size;
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                int rnd =new Random().nextInt(7);

                if(rnd ==6 || rnd == 5){
                    rnd -=2 ;
                }
                if (LevelEditor.currentLayer == 0) {
                    g[i][j].setTileID(LevelEditor.FLOOR << 8| new Random().nextInt(3));
                    g[i][j].setImage("Level Editor/assets/" + LevelEditor.VARIANT[LevelEditor.getCurrentVariant()] + ".png");
                }
                if(LevelEditor.currentLayer < 7){
                    if(i ==0 || i==length-1|| j ==0||j==width-1){
                        g[i][j].setTileID(LevelEditor.WALL << 8 | rnd);
                        g[i][j].setImage("Level Editor/assets/" + LevelEditor.VARIANT[LevelEditor.getCurrentVariant()] + ".png");
                    }

                }
                if(LevelEditor.currentLayer ==7){
                    g[i][j].setTileID(LevelEditor.WALL << 8 | rnd);
                    g[i][j].setImage("Level Editor/assets/" + LevelEditor.VARIANT[LevelEditor.getCurrentVariant()] + ".png");
                }

            }

        }
        if(LevelEditor.currentLayer == 1){
            g[18][10].setTileID(LevelEditor.PLAYERSPAWN << 8);
            g[18][10].setImage("Level Editor/assets/" + LevelEditor.VARIANT[LevelEditor.getCurrentVariant()] + ".png");

        }


    }

    public Tile[][] getG() {
        return g;
    }

    public void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.white);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                g[i][j].drawTile(g2d);
            }
        }
        g2d.setColor(Color.black);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                g2d.draw(g[i][j].getRectangle());
            }
        }
        g2d.setColor(new Color(0f, 0.8f, 0.03529412f, 0.7f));
        g2d.fill(selected);

    }

    public void drawDisplayGrid(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                g[i][j].drawTile(g2d);
            }
        }


    }

    public void hover(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Rectangle point = new Rectangle(mx, my, LevelEditor.bsize * size + 1, LevelEditor.bsize * size + 1);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (point.intersects(g[i][j].getRectangle().getBounds())) {
                    selected = new Rectangle(mx - (LevelEditor.bsize * size + 1) / 2, my - (LevelEditor.bsize * size + 1) / 2, LevelEditor.bsize * size + 1, LevelEditor.bsize * size + 1);
                }
            }
        }
    }

    public void click(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Rectangle point = new Rectangle(mx - (LevelEditor.bsize * size) / 2, my - (LevelEditor.bsize * size) / 2, LevelEditor.bsize * size+1, LevelEditor.bsize * size+1);
        Rectangle point1 = new Rectangle(mx, my, 1, 1);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (point.intersects(g[i][j].getRectangle().getBounds()) && point1.intersects(gridR.getBounds())) {
                    g[i][j].setTileID(LevelEditor.getID());
                    selected = new Rectangle(mx - (LevelEditor.bsize * size + 1) / 2, my - (LevelEditor.bsize * size + 1) / 2, LevelEditor.bsize * size + 1, LevelEditor.bsize * size + 1);
                    g[i][j].setImage("Level Editor/assets/" + LevelEditor.VARIANT[LevelEditor.getCurrentVariant()] + ".png");
                }
            }
        }
    }

    public void setID(int id) {
        tileID = id;
    }
}
