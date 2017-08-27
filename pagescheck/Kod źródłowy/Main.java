import files.PageCheck;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            JFrame pageCheck = null;
            try {
                pageCheck = new PageCheck(new ImageIcon());
            } catch (IOException e) {
                e.printStackTrace();
            }
            pageCheck.setVisible(true);
            pageCheck.setLocation(50,200);
            pageCheck.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        });
    }
}
