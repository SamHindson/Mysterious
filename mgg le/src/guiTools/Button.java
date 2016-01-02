package guiTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Blake on 12/30/2015.
 */
public class Button {
    private Event event;
    private Rectangle r;
    private int x, y, width, height;
    private Color c, h, d, current;
    private String text,path;
    private Image i;
    private int id,b,v,de;


    public Button(int x, int y, int width, int height, String text, Color d, Event event) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.event = event;
        this.d = d;
        this.text = text;
        current = d;
        r = new Rectangle(x, y, width, height);
        h = new Color(d.getRed() + 30, d.getGreen() + 30, d.getBlue() + 30);
        c = new Color(d.getRed() - 30, d.getGreen() - 30, d.getBlue() - 30);
    }

    public Button(int x, int y, int width, int height,int b,int v,int de, String path, String text, Color d, Event event) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.event = event;
        this.d = d;
        this.text = text;
        this.path =path;
        current = d;
        this.b=b;
        this.v =v;
        this.de =de;
        r = new Rectangle(x, y, width, height);
        h = new Color(d.getRed() + 30, d.getGreen() + 30, d.getBlue() + 30);
        c = new Color(d.getRed() - 30, d.getGreen() - 30, d.getBlue() - 30);
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setBVD(int block, int variant, int decor){
        this.b = block;
        this.v = variant;
        this.de = decor;

    }
    public void setImage(){
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void paintButton(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(current);
        g2d.fillRect(x + 2, y + 2, width - 4, height - 4);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Impact", Font.BOLD, 32));
        g2d.drawString(text, x + width / 2 - g2d.getFontMetrics().stringWidth(text) / 2, y + height - 10);

    }

    public void paintBlockButton(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(current);
        g2d.fillRect(x + 2, y + 2, width - 4, height - 4);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Impact", Font.BOLD, 20));
        g2d.drawString(text, x + width / 2 - g2d.getFontMetrics().stringWidth(text) / 2 + width / 5 - 13, y + height - 20);
        g2d.drawImage(i,x+3,y+6,x+height-6,y+height-6, 0,b*16,16,16*b+16,null);
    }

    public void paintDecorButton(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(x,y,width,height);
        g2d.setColor(current);
        g2d.fillRect(x+1, y+1, width-2, height-2);
        g2d.drawImage(i,x+2,y+2,x+width-2,y+height-2,de*16,(b)*16,de*16+16,(b)*16+16,null);
    }

    public void hover(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Rectangle point = new Rectangle(mx, my, 1, 1);
        if (point.intersects(r.getBounds())) {
            current = h;
        } else {
            current = d;
        }
    }

    public void click(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Rectangle point = new Rectangle(mx, my, 1, 1);
        if (point.intersects(r.getBounds())) {
            event.doSomething();
            current = c;
        } else {
            current = d;
        }
    }

}
