package files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel
{
    private final int DEFAULT_WIDTH;
    private final int DEFAULT_HEIGHT;
    private List<Ball> balls=new ArrayList<>();
    private List<Table> tables=new ArrayList<>();
    private List<Block> blocks=new ArrayList<>();

    public GamePanel(int w,int h)
    {
        DEFAULT_HEIGHT=h-50;
        DEFAULT_WIDTH=w;
    }

    public void addBall(Ball b)
    {
        balls.add(b);
    }
    public void addTable(Table b)
    {
        tables.add(b);
    }
    public void addBlock(Block b)
    {
        blocks.add(b);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        for(Ball b:balls)
        {
            g2.setPaint(Color.BLUE);
            g2.fill(b.getShape());
        }
        for(Table t:tables)
        {
            g2.setPaint(Color.BLACK);
            g2.fill(t.getShape());
        }
        for(Block block:blocks)
        {
            g2.setPaint(Color.RED);
            if(block.isDestroyed())
                block.setVisible();
            else
                g2.fill(block.getShape());
        }
    }
}
