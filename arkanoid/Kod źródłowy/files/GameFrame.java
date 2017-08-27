package files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameFrame extends JFrame
{
    private final static int DEFAULT_WIDTH=900;
    private final static int DEFAULT_HEIGHT=600;
    private GamePanel gamePanel=new GamePanel(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    private JPanel panelSouth=new JPanel();
    private static final int DELAY=3;
    private double tableSize=150;
    private double blockSize=50;
    private int i=0;
    private Thread tableThread;
    private Ball ball;
    private Table table;
    private JButton start=new JButton("Start");
    private double tablePos=DEFAULT_WIDTH/2;
    public GameFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        add(gamePanel,BorderLayout.CENTER);
        table=new Table(DEFAULT_WIDTH/2,DEFAULT_HEIGHT-100,tableSize);
        runTable();
        gamePanel.addTable(table);
        start.addActionListener(e->startGame());
        panelSouth.add(start);
        add(panelSouth,BorderLayout.SOUTH);
        addButton(panelSouth,"Exit",e -> System.exit(0));
        add(panelSouth, BorderLayout.SOUTH);
    }

    public void addButton(Container c, String title, ActionListener listener)
    {
        JButton button=new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void startGame()
    {
        remove(gamePanel);
        tableThread.stop();
        runTable();
        gamePanel=new GamePanel(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        add(gamePanel,BorderLayout.CENTER);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        repaint();
        validate();
        gamePanel.addTable(table);
        ball=new Ball();
        gamePanel.addBall(ball);
        Lock lock=new ReentrantLock();

       List<Block> blocks=new ArrayList<>();
        for(int j=0,q=0;j<5;j++,q++)
        {
            for(int l=0;l<5-j;l++)
            {
                blocks.add(new Block(DEFAULT_WIDTH*0.3+(blockSize*0.8*j)+((blockSize+30)*l),DEFAULT_HEIGHT*0.1+45*j,blockSize));
            }
        }
        for(Block block:blocks)
            gamePanel.addBlock(block);
        Runnable b=()->
        {
            start.setEnabled(false);
            lock.lock();
            try
            {
                while(ball.move(gamePanel.getBounds(),tablePos,DEFAULT_HEIGHT-100,tableSize))
                {
                    for(Block block:blocks)
                        block.checkBlock(ball);
                    gamePanel.repaint();
                    Thread.sleep(DELAY);
                }
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }finally {
                lock.unlock();
            }
            start.setEnabled(true);
        };
        Thread ballThread=new Thread(b);
        ballThread.start();
    }

    public void runTable() {
        Runnable t = () ->
        {
            gamePanel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        i = 0;
                        while (i < 5) {
                            table.moveLeft(gamePanel.getBounds());
                            gamePanel.repaint();
                            i++;
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        i = 0;
                        while (i < 5) {
                            table.moveRight(gamePanel.getBounds());
                            gamePanel.repaint();
                            i++;
                        }
                    }
                    gamePanel.repaint();
                    tablePos = table.getX();
                }
            });
        };
        tableThread=new Thread(t);
        tableThread.start();
    }
}

