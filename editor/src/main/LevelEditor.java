package main;


import guiTools.Button;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Blake on 12/30/2015.
 *
 * The LevelEditor Class. It edits levels
 */
public class LevelEditor {
    private int width, length, height;
    public static int currentLayer;
    private Grid[] level;
    private Button up, down, save, left, right, up1, down1;
    private Button[] blocks = new Button[13];
    private Frame f;
    private Grid[] display;
    private int x = 0, y = 20, count = 1;
    private static int currentVariant = 0;
    private Button[] decorArr = new Button[16];
    private int decor = 0;
    public int b = 0;
    public int v = 0;
    public int d = 0;
    public static int id;
    private int noDecor = 0;
    private boolean show = false, disable = false;
    private int dy;
    public static int bsize = 0;
    private SLevel l;

    private Font impact32, impact19;

    public LevelEditor(Frame f) {
        this.f = f;
        width = 20;
        length = 20;
        height = 8;
        level = new Grid[height];
        display = new Grid[height];
        currentLayer = 0;
        for (int i = 0; i < height; i++) {
            currentLayer = i;
            level[currentLayer] = new Grid(f, 400, 20, width, length, 800 / width);
        }

        for (int i = 0; i < height; i++) {
            currentLayer = i;
            display[i] = new Grid(f, x, y, width, length, 200 / width);

            y = y + 200;
            if (count == 4) {
                x = 200;
                y = y - 800;
            }
            count++;
        }
        currentLayer = 0;

        up = new Button(1230, 180, 40, 40, "▲", new Color(225, 225, 225), () -> {
            if (currentLayer < height - 1)
                currentLayer++;
        });
        down = new Button(1230, 275, 40, 40, "▼", new Color(225, 225, 225), () -> {
            if (currentLayer > 0)
                currentLayer--;
        });
        up1 = new Button(1230, 380, 40, 40, "▲", new Color(225, 225, 225), () -> {
            if (bsize < 4) {
                bsize++;
            }
        });
        down1 = new Button(1230, 475, 40, 40, "▼", new Color(225, 225, 225), () -> {
            if (bsize > 0)
                bsize--;
        });
        left = new Button(1230, 10, 40, 40, "◄", new Color(225, 225, 225), () -> {

            if (currentVariant > 0)
                currentVariant--;
            setVariant(currentVariant);

        });
        right = new Button(1440, 10, 40, 40, "►", new Color(225, 225, 225), () -> {
            if (currentVariant < 5)
                currentVariant++;
            setVariant(currentVariant);
        });

        save = new Button(1210, 700, 80, 50, "SAVE", new Color(30, 200, 30), () -> {
            l = new SLevel(level);
            Serialization sel = new Serialization();
            sel.serialize(l, "editor/savedLevels/" + JOptionPane.showInputDialog("Save As") + ".mggl");
        });

        blocks[0] = new Button(1300, 60, 180, 50, WALL, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "WALL►", new Color(225, 225, 225), () -> {
            b = WALL;
            noDecor = 8;
            show = true;

            blocks[0].setBVD(WALL, v, 0);
            dy = 0;
            decorSelection();
        });
        blocks[1] = new Button(1300, 120, 180, 50, FLOOR, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "FLOOR►", new Color(225, 225, 225), () -> {
            b = FLOOR;
            noDecor = 3;
            show = true;

            blocks[1].setBVD(FLOOR, v, 0);
            dy = 60;
            decorSelection();

        });
        blocks[2] = new Button(1300, 180, 180, 50, PICKUP, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "PICK-UP►", new Color(225, 225, 225), () -> {
            b = PICKUP;
            show = true;
            noDecor = 6;

            blocks[2].setBVD(PICKUP, v, 0);
            dy = 120;
            decorSelection();
        });
        blocks[3] = new Button(1300, 240, 180, 50, DOOR, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "DOOR►", new Color(225, 225, 225), () -> {
            b = DOOR;
            noDecor = 2;
            show = true;
            blocks[3].setBVD(DOOR, v, 0);

            dy = 180;
            decorSelection();
        });
        blocks[4] = new Button(1300, 300, 180, 50, LAMPS, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "LAMP►", new Color(225, 225, 225), () -> {
            b = LAMPS;
            noDecor = 2;
            show = true;

            dy = 240;
            blocks[4].setBVD(LAMPS, v, 0);
            decorSelection();
        });
        blocks[5] = new Button(1300, 360, 180, 50, NOTHING, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "SPACE", new Color(225, 225, 225), () -> {
            b = NOTHING;
            noDecor = 1;
            show = true;
            blocks[5].setBVD(NOTHING, v, 0);

            dy = 300;
            decorSelection();
        });
        blocks[6] = new Button(1300, 420, 180, 50, PLAYERSPAWN, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "PLAYER SPAWN", new Color(225, 225, 225), () -> {
            b = PLAYERSPAWN;
            show = true;
            noDecor = 1;

            blocks[6].setBVD(PLAYERSPAWN, v, 0);
            dy = 360;
            decorSelection();
        });
        blocks[7] = new Button(1300, 480, 180, 50, ENEMYSPAWN, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "ENEMY SPAWN", new Color(225, 225, 225), () -> {
            b = ENEMYSPAWN;
            show = true;
            noDecor = 5;

            blocks[7].setBVD(ENEMYSPAWN, v, 0);
            dy = 420;
            decorSelection();
        });
        blocks[8] = new Button(1300, 540, 180, 50, LEVELCHANGER, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "LEVELCHANGER", new Color(225, 225, 225), () -> {
            b = LEVELCHANGER;
            noDecor = 1;
            show = true;
            blocks[8].setBVD(LEVELCHANGER, v, 0);
            decorSelection();
        });
        blocks[9] = new Button(1300, 600, 180, 50, DECALS, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "DECALS►", new Color(225, 225, 225), () -> {
            b = DECALS;
            noDecor = 4;
            show = true;

            blocks[9].setBVD(DECALS, v, 0);
            dy = 480;
            decorSelection();
        });
        blocks[10] = new Button(1300, 660, 180, 50, OBSTACLES, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "OBSTACLES►", new Color(225, 225, 225), () -> {
            b = OBSTACLES;
            noDecor = 2;
            show = true;
            blocks[10].setBVD(OBSTACLES, v, 0);

            dy = 540;
            decorSelection();
        });
        blocks[11] = new Button(1300, 720, 180, 50, BOSSSPAWN, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "BOSS SPAWN►", new Color(225, 225, 225), () -> {
            b = BOSSSPAWN;
            noDecor = 1;
            show = true;

            blocks[11].setBVD(BOSSSPAWN, v, 0);
            dy = 600;
            decorSelection();

        });
        blocks[12] = new Button(1300, 780, 180, 50, BUTTON, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "BUTTON", new Color(225, 225, 225), () -> {
            b = BUTTON;
            noDecor = 2;
            show = true;

            blocks[12].setBVD(BUTTON, v, 0);
            dy = 660;
            decorSelection();
        });

        impact32 = new Font("Impact", Font.BOLD, 32);
        impact19 = new Font("Impact", Font.BOLD, 19);
    }

    public void setVariant(int a) {
        v = VARIANTHEX[a];
    }

    public static int getCurrentVariant() {
        return currentVariant;
    }

    private int generateID(int b) {
        int i;
        if (b == PLAYERSPAWN) {
            i = b << 16;
        } else if (b == LEVELCHANGER) {
            i = b << 16 | Integer.parseInt(JOptionPane.showInputDialog("Enter linking level(0 - 255)")) << 12;
        } else if (b == BUTTON || b == DOOR || b == LAMPS) {
            i = b << 16 | v << 12 | d << 8 | Integer.parseInt(JOptionPane.showInputDialog("Enter Switch ID")) << 4;
        } else if (b == BOSSSPAWN) {
            i = b << 16 | v << 12;
        } else {
            i = b << 16 | v << 12 | d << 8;
        }

        System.out.println(Integer.toHexString(i));

        return i;
    }

    public void decorSelection() {
        decorArr[0] = new Button(1100, 10 + dy, 50, 50, b, v, 0, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[0];
            show = false;
            decorArr[0].setBVD(b, v, 0);
            decorArr[0].setImage();
            id = generateID(b);
        });
        decorArr[1] = new Button(1150, 10 + dy, 50, 50, b, v, 1, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[1];
            show = false;
            decorArr[1].setBVD(b, v, 1);
            decorArr[1].setImage();
            id = generateID(b);
        });
        decorArr[2] = new Button(1200, 10 + dy, 50, 50, b, v, 2, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[2];
            show = false;
            decorArr[2].setBVD(b, v, 2);
            decorArr[2].setImage();
            id = generateID(b);
        });
        decorArr[3] = new Button(1250, 10 + dy, 50, 50, b, v, 3, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[3];
            show = false;
            decorArr[3].setBVD(b, v, 3);
            decorArr[3].setImage();
            id = generateID(b);
        });
        decorArr[4] = new Button(1100, 60 + dy, 50, 50, b, v, 4, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[4];
            show = false;
            decorArr[4].setBVD(b, v, 4);
            decorArr[4].setImage();
            id = generateID(b);
        });
        decorArr[5] = new Button(1150, 60 + dy, 50, 50, b, v, 5, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[5];
            show = false;
            decorArr[5].setBVD(b, v, 5);
            decorArr[5].setImage();
            id = generateID(b);
        });
        decorArr[6] = new Button(1200, 60 + dy, 50, 50, b, v, 6, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[6];
            decorArr[6].setBVD(b, v, 6);
            show = false;
            decorArr[6].setImage();
            id = generateID(b);
        });
        decorArr[7] = new Button(1250, 60 + dy, 50, 50, b, v, 7, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[7];
            show = false;
            decorArr[7].setBVD(b, v, 7);
            decorArr[7].setImage();
            id = generateID(b);
        });
        decorArr[8] = new Button(1100, 110 + dy, 50, 50, b, v, 8, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[8];
            show = false;
            decorArr[8].setBVD(b, v, 8);
            decorArr[8].setImage();

            id = generateID(b);
        });
        decorArr[9] = new Button(1150, 110 + dy, 50, 50, b, v, 9, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[9];
            show = false;
            decorArr[9].setBVD(b, v, 9);
            decorArr[9].setImage();

            id = generateID(b);
        });
        decorArr[10] = new Button(1200, 110 + dy, 50, 50, b, v, 10, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[10];
            show = false;
            decorArr[10].setBVD(b, v, 0xa);
            decorArr[10].setImage();

            id = generateID(b);
        });
        decorArr[11] = new Button(1250, 110 + dy, 50, 50, b, v, 11, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[11];
            show = false;
            decorArr[11].setBVD(b, v, 0xb);
            decorArr[11].setImage();

            id = generateID(b);
        });
        decorArr[12] = new Button(1100, 160 + dy, 50, 50, b, v, 12, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[12];
            show = false;
            decorArr[12].setBVD(b, v, 0xc);
            decorArr[12].setImage();

            id = generateID(b);
        });
        decorArr[13] = new Button(1150, 160 + dy, 50, 50, b, v, 13, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[13];
            show = false;
            decorArr[13].setBVD(b, v, 0xd);
            decorArr[13].setImage();

            id = generateID(b);
        });
        decorArr[14] = new Button(1200, 160 + dy, 50, 50, b, v, 14, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[14];
            show = false;
            decorArr[14].setBVD(b, v, 0xe);
            decorArr[14].setImage();

            id = generateID(b);
        });
        decorArr[15] = new Button(1250, 160 + dy, 50, 50, b, v, 15, "editor/assets/" + VARIANT[currentVariant] + ".png", "", new Color(225, 225, 225), () -> {
            d = DECOR[15];
            show = false;
            decorArr[15].setBVD(b, v, 0xf);
            decorArr[15].setImage();

            id = generateID(b);
        });

    }

    public void paint(Graphics2D g2d) {
        level[currentLayer].drawGrid(g2d);
        g2d.setColor(Color.black);
        g2d.setFont(impact32);
        int layer = currentLayer + 1;
        g2d.drawString("" + layer, 1240, 260);
        int bs = bsize + 1;
        g2d.drawString("" + bs, 1240, 460);
        g2d.drawString("LAYER", 1210, 170);
        g2d.setFont(impact19);
        g2d.drawString("BRUSH SIZE", 1202, 370);

        up.paintButton(g2d);
        down.paintButton(g2d);
        up1.paintButton(g2d);
        down1.paintButton(g2d);
        save.paintButton(g2d);
        left.paintButton(g2d);
        right.paintButton(g2d);

        for (Button block : blocks) {
            block.paintBlockButton(g2d);
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < width; k++) {
                    display[i].getG()[j][k].setImage(level[i].getG()[j][k].getI());
                    display[i].getG()[j][k].setTileID(level[i].getG()[j][k].getID());
                }
            }
            display[i].drawDisplayGrid(g2d);
        }
        g2d.setColor(Color.black);
        g2d.drawString(VARIANT[currentVariant], 1270 + (1440 - 1270) / 2 - g2d.getFontMetrics().stringWidth(VARIANT[currentVariant]) / 2, 40);
        if (show) {
            for (int i = 0; i < noDecor; i++) {
                decorArr[i].paintDecorButton(g2d);
            }
        }

    }

    public void click(MouseEvent e) {
        if (!show)
            level[currentLayer].click(e);

        for (Button block : blocks) {
            block.click(e);

        }

        if (show) {
            for (int i = 0; i < noDecor; i++) {
                decorArr[i].click(e);
            }
        }
    }

    public void buttonClick(MouseEvent e) {
        up.click(e);
        down.click(e);
        up1.click(e);
        down1.click(e);
        left.click(e);
        right.click(e);
        save.click(e);
    }

    public static int getID() {
        return id;
    }

    public void hover(MouseEvent e) {
        level[currentLayer].hover(e);
        up.hover(e);
        down.hover(e);
        up1.hover(e);
        down1.hover(e);
        left.hover(e);
        save.hover(e);
        right.hover(e);

        for (Button block : blocks) {
            block.hover(e);
        }
        if (show) {
            for (int i = 0; i < noDecor; i++) {
                decorArr[i].hover(e);
            }
        }
    }

    public static final int NOTHING = 0;//        shift bits        normal b|v|d
    public static final int WALL = 0x1;//            (8)   nomral
    public static final int DOOR = 0x2;//            (16)  b|v|d|id
    public static final int LAMPS = 0x3;//           (16)  b|v|d|id
    public static final int PICKUP = 0x4;//          (8)   normal
    public static final int FLOOR = 0x5;//           (8)   normal
    public static final int PLAYERSPAWN = 0x6; //    (0)   b
    public static final int ENEMYSPAWN = 0x7;//      (4)   normal
    public static final int DECALS = 0x8;//          (8)   normal
    public static final int OBSTACLES = 0x9;//       (8)   normal
    public static final int BOSSSPAWN = 0xA;//       (4)   b|type
    public static final int BUTTON = 0xB;//          (16)  b|v|d|id
    public static final int LEVELCHANGER = 0xC;//    (8)   b|level

    public static final int[] VARIANTHEX = {0x0, 0x1, 0x2, 0x3, 0x4, 0x5}; // shift 4 bits
    public static final String[] VARIANT = {"STANDARD", "ICE", "HELL", "JUNGLE", "DESERT", "WASTELAND"};

    public static final int[] DECOR = {0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xF};//shift 4 bits
}
