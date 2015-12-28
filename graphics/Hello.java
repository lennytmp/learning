package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;


class Square {
  int x1, x2, y1, y2;

  public Square(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }


  public void draw(Graphics g) {
    g.drawLine(x1, y1, x2, y1);
    g.drawLine(x1, y1, x1, y2);
    g.drawLine(x2, y1, x2, y2);
    g.drawLine(x1, y2, x2, y2);
  }
}

public class Hello extends Canvas {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final Random random = new Random();

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(randomColor());
        Square s = new Square(10, 10, 25, 25);
        s.draw(g);
    }

    private Color randomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(WIDTH, HEIGHT);
        frame.add(new Hello());

        frame.setVisible(true);
    }
}
