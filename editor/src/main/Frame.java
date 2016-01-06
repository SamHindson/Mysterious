package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Blake on 12/30/2015.
 *
 * I got framed once
 */
public class Frame extends JPanel {

    LevelEditor le;

    public Frame() {
        super();
        le = new LevelEditor(this);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                le.click(e);
                le.buttonClick(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                le.click(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                le.hover(e);
            }
        });

        setFocusable(true);


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        le.paint(g2d);


    }

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("Mysterious SLevel Editor");
        Frame j = new Frame();
        f.setSize(800 + 300+200+200, 880);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.add(j);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boolean running = true;

        // TODO get rid of this infinite loop - this kind of stuff scares me.
        //                          - Sam

        while (running) {
            f.repaint();
            Thread.sleep(16);
        }
    }
}
