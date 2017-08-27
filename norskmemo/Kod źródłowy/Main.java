import files.MemoFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException
    {
        EventQueue.invokeLater(()->
        {
            MemoFrame frame=new MemoFrame();
            frame.setTitle("Memory Frame");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}
