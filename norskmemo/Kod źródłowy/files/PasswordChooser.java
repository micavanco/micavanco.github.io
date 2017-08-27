package files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class PasswordChooser extends JPanel
{
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private boolean ok;
    private JDialog dialog;
    private JDialog wrong;
    private FileWriter fileWriter;
    private InputStream fileReader;
    private User user;

    public PasswordChooser()
    {
        setLayout(new BorderLayout());

        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(new JLabel("Nazwa Użytkownika: "));
        panel.add(username=new JTextField(""));
        panel.add(new JLabel("Hasło: "));
        panel.add(password=new JPasswordField(""));
        add(panel,BorderLayout.CENTER);

        okButton=new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                if(LoadUser((user=new User(username.getText(),password.getPassword())))!=null)
                {
                    ok=true;
                    dialog.setVisible(false);
                }else
                {
                    wrong.setVisible(true);
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        JButton cancelButton=new JButton("Anuluj");
        cancelButton.addActionListener(e -> dialog.setVisible(false));

        JPanel buttonPanel=new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel,BorderLayout.SOUTH);
    }

    public User getUser()
    {
        return user;
    }

    public User LoadUser(User u) throws IOException {
        fileReader=PasswordChooser.class.getResourceAsStream("usersdata.txt");
        Scanner br=new Scanner(fileReader);
        String line="";
        while ((line=br.nextLine())!=null)
        {
            String[] data=line.split(";");
            if(data[0].equals(u.getName()))
            {
                return new User(data[0],data[1].toCharArray());
            }
        }
        return null;
    }

    public void SaveUser(User u) throws IOException {
        fileWriter=new FileWriter("usersdata.txt",true);
        StringBuilder sb=new StringBuilder();
        sb.append(u.getName());
        sb.append(";");
        sb.append(u.getPassword());
        fileWriter.write(sb.toString());
        fileWriter.close();
    }


    public boolean showDialog(Component parent,String title) throws IOException {
        ok=false;

        Frame owner=null;
        if(parent instanceof Frame)
            owner=(Frame)parent;
        else
            owner=(Frame)SwingUtilities.getAncestorOfClass(Frame.class,parent);

        if(dialog==null || dialog.getOwner()!=owner)
        {
            dialog=new JDialog(owner,true);
            dialog.setLocation(500,500);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();

            wrong=new JDialog(owner,true);
            wrong.add(new JLabel("<html><h1><i>Błąd!</i></h1><hr>Podano niepoprawne dane!</html>"),BorderLayout.CENTER);
            JButton ok=new JButton("OK");
            ok.addActionListener(e -> wrong.setVisible(false));
            wrong.add(ok,BorderLayout.SOUTH);
            wrong.setSize(250,150);
            wrong.setLocation(500,350);
        }

        dialog.setTitle(title);
        dialog.setVisible(true);

        return ok;
    }

}
