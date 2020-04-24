/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumbeer;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Jumbeer extends JFrame {

    public Jumbeer() {
        setVisible(true);
        panel p = new panel();
        add(p);
        addKeyListener(p);
        setFocusable(true);
        addMouseListener(p);
        setSize(600, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        // TODO code application logic here
        Jumbeer b = new Jumbeer();
    }

    class panel extends JPanel implements ActionListener, KeyListener,MouseListener {

        int y[] = {20, 70, 30, 170, 220, 180, 320, 370, 330};
        int x[] = {30, 200, 370, 30, 200, 370, 30, 200, 370};
        int xblok[] = {100, 300, 150, 50, 210, 350, 150, 50};
        int yblock[] = {520, 420, 320, 220, 120, 20, -80, -180};
        int x1 = 0;
        int y1 = 630;
        int xplayer = 250;
        int yplayer = 600;
        int failspeed = 10;
        boolean jumb = false;
        int score = 0;
        Date start;
        ImageIcon image = new ImageIcon("src/jumbeer/image/ssd.png");
        ImageIcon image1 = new ImageIcon("src/jumbeer/image/Untitled-1.png");
        ImageIcon wall1 = new ImageIcon("src/jumbeer/image/wall1.png");
        ImageIcon wall2 = new ImageIcon("src/jumbeer/image/wall2.png");
        ImageIcon blocks = new ImageIcon("src/jumbeer/image/blocks.png");
        ImageIcon player = new ImageIcon("src/jumbeer/image/jumber.png");
        Image icon4 = blocks.getImage();

        AudioClip sound;
        boolean move = false;
        Timer time = new Timer(30, this);

        public panel() {
            URL uri2 = getClass().getResource("");
            sound = Applet.newAudioClip(uri2);
            start = new Date();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image icon = image.getImage();
            Image icon1 = image1.getImage();
            g.drawImage(icon, 0, 0, this);
            for (int i = 0; i < 9; i++) {
                g.drawImage(icon1, x[i], y[i], this);
                if (y[i] + icon1.getHeight(this) >= 700) {
                    y[i] = -100;
                }
            }
            Image icon2 = wall1.getImage();
            g.drawImage(icon2, 540, -1, this);
            g.drawImage(icon2, -27, -1, this);
             
            for (int i = 0; i < 8; i++) {
                g.drawImage(icon4, xblok[i], yblock[i], this);
                if (yblock[i] + icon4.getHeight(this) >= 700) {
                    yblock[i] = -150;
                       score+=1;
                }
                update(xblok[i], yblock[i], icon4);
              }

            Image icon5 = player.getImage();
            if (xplayer <= 20) {
                xplayer = 30;
            }
            if (xplayer - icon5.getWidth(this) >= 480) {
                xplayer = 500;
            }
            g.drawImage(icon5, xplayer, yplayer, this);
            Image icon3 = wall2.getImage();
            g.drawImage(icon3, x1, y1, this);
            g.setColor(Color.GREEN);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 23));
            g.drawString("Time:" + gettime(), 440, 20);
            g.drawString("Score:" + score, 220, 20);
            g.drawString("Level:", 100, 20);
            if (yplayer + icon5.getHeight(this) >= 720) {
                g.setColor(Color.BLACK);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 33));
                g.drawString(" Game over !!", getHeight() / 4, getWidth() / 2);
                time.stop(); 
                
            }
        }

        public long gettime() {
            Date now = new Date();
            long time1 = now.getTime() - start.getTime();
            return time1 / 1000;
        }

        public void update(int x, int y, Image icon) {
            int postion;

            jumb = true;
            if(xplayer==x &&yplayer==y)
            {
            failspeed=0;
            xplayer=x;
            yplayer=y;
            }
//            if (xplayer >= x - 15 && xplayer <= x + icon.getWidth(this) - 20 && yplayer <= y + icon.getHeight(this)) {
//                postion = y - icon.getHeight(this);
//                yplayer = postion - 20;
//            }

        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            for (int i = 0; i < 9; i++) {
                y[i] += 2;
            }
            for (int i = 0; i < 8; i++) {
                yblock[i] += 2;

            }
            yplayer += failspeed;
            y1 = 700;
            repaint();
            sound.play();
        }

        @Override
        public void keyTyped(KeyEvent ke) {

        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                // yplayer -= 140;
                time.start();
                repaint();
            } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                xplayer -= 20;
                repaint();

            } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                xplayer += 20;
                repaint();

            } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                time.stop();
            }
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
               
                yplayer -= 100;
                
                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
             yplayer -= 100;
             repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
             yplayer -= 100;
             repaint();
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

    }

}
