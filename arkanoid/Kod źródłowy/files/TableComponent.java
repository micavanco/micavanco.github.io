package files;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TableComponent extends JPanel{
    private final int DEFAULT_WIDTH;
    private final int DEFAULT_HEIGHT;
    private List<Table> tables=new ArrayList<>();

    public TableComponent(int w,int h)
    {
        DEFAULT_HEIGHT=h-500;
        DEFAULT_WIDTH=w;
    }

    public void add(Table t)
    {
        tables.add(t);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        for(Table t:tables)
        {
            g2.fill(t.getShape());
        }
    }
}
