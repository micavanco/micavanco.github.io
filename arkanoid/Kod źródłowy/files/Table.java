package files;

import java.awt.geom.Rectangle2D;

public class Table {
    private static double XSize;
    private double Y;
    private double x;
    private double dx=2;

    public Table(int x,int y,double tSize)
    {
        Y=y;
        XSize=tSize;
        this.x=x;
    }
    public void moveLeft(Rectangle2D bounds)
    {
        x-=dx;
        if(x<bounds.getMinX())
        {
            x=bounds.getMinX();
        }
    }
    public void moveRight(Rectangle2D bounds)
    {
        x+=dx;
        if(x>=bounds.getMaxX()-XSize)
        {
            x=bounds.getMaxX()-XSize;
        }
    }
    public double getX(){return x;}

    public Rectangle2D getShape()
    {
        return new Rectangle2D.Double(x,Y,XSize,20.0);
    }
}
