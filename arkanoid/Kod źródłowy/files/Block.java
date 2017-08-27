package files;

import java.awt.geom.Rectangle2D;

public class Block {
    private double XSize;
    private double Y;
    private double x;
    private boolean isDestroyed=false;
    private Rectangle2D rectangle2D;

    public Block(double x,double y,double tSize)
    {
        Y=y;
        XSize=tSize;
        this.x=x;
    }
    public double getX(){return x;}
    public void checkBlock(Ball b)
    {
        if((b.getX()+b.getXSIZE()>=x&&x+XSize>=b.getX()+b.getXSIZE())&&(b.getY()+b.getYSIZE()>=Y&&(Y+30)>=b.getY()+b.getYSIZE())&&b.getDx()>0)
        {
            b.setDx(-1*b.getDx());
            isDestroyed=true;
        }
        else if((b.getX()+b.getXSIZE()>=x&&x+XSize>=b.getX()+b.getXSIZE())&&(b.getY()+b.getYSIZE()>=Y&&(Y+30)>=b.getY()+b.getYSIZE())&&b.getDx()<0)
        {
            b.setDy(-1*b.getDy());
            isDestroyed=true;
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setVisible()
    {
        rectangle2D.setRect(0,0,0,0);
        x=0;Y=0;XSize=0;
    }

    public Rectangle2D getShape()
    {
        return (rectangle2D=new Rectangle2D.Double(x,Y,XSize,30.0));
    }
}
