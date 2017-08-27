import files.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args)
    {
        EventQueue.invokeLater(()->{
            JFrame frame=new GameFrame();
            frame.setLocation(200,100);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
