package files;

import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball {
    private int XSIZE=15;
    private int YSIZE=15;
    private double x=0;
    private double y=0;
    private double dx=1;
    private double dy=1;

    public boolean move(Rectangle2D bounds,double tablePos,double tableH,double tableSize)
    {
        x+=dx;
        y+=dy;
        if(x<bounds.getMinX())
        {
            x=bounds.getMinX();
            dx=-dx;
        }
        if(x+XSIZE>=bounds.getMaxX())
        {
            x=bounds.getMaxX()-XSIZE;
            dx=-dx;
        }
        if(y<bounds.getMinY())
        {
            y=bounds.getMinY();
            dy=-dy;
        }
        if(y+YSIZE>=tableH&&(x<=tablePos+tableSize&&x+XSIZE>=tablePos+tableSize*0.9)&&dx<0)
        {
            y=tableH-YSIZE;
            dy=-dy;
            dx=-dx;
        }
        if(y+YSIZE>=tableH&&(x+XSIZE<=tablePos+tableSize*0.9&&x+XSIZE>=tablePos)&&dx<0)
        {
            y=tableH-YSIZE;
            dy=-dy;
        }
        if(y+YSIZE>=tableH&&(x+XSIZE<=tablePos+tableSize&&x+XSIZE>=tablePos+tableSize*0.25)&&dx>0)
        {
            y=tableH-YSIZE;
            dy=-dy;
        }
        if(y+YSIZE>=tableH&&(x+XSIZE<=tablePos+tableSize*0.25&&x+XSIZE>=tablePos)&&dx>0)
        {
            y=tableH-YSIZE;
            dy=-dy;
            dx=-dx;
        }
        if(y+YSIZE>=bounds.getMaxY())
        {
            JOptionPane.showMessageDialog(null,"Koniec gry!","Informacja",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getXSIZE() {
        return XSIZE;
    }

    public int getYSIZE() {
        return YSIZE;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Ellipse2D getShape()
    {
        return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }
}
