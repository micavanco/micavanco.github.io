package files;


import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
import java.util.prefs.Preferences;

/**
 * Created by MO on 2017-07-08.
 */
public class MemoFrame extends JFrame
{
    private JToolBar toolBar=new JToolBar("Syntezator");
    private JMenuBar menuToolBar=new JMenuBar();
    private JMenuBar menuBar=new JMenuBar();
    private JPopupMenu popupMenu=new JPopupMenu();
    private JMenuItem cutItem=new JMenuItem("Wytnij");
    private JMenuItem pasteItem=new JMenuItem("Wklej");
    private JMenuItem helpItem=new JMenuItem("Instrukcja");
    private JMenuItem appItem=new JMenuItem("O programie");
    private JMenuItem startItem=new JMenuItem("Zaloguj się");
    private JCheckBox eqItem=new JCheckBox("Balans stereo");
    private JCheckBox volumeItem=new JCheckBox("Natężenie dźwięku");
    private JCheckBox soundItem=new JCheckBox("Panel syntezatora mowy");
    private JMenu fileMenu=new JMenu("Plik");
    private JMenu helpMenu=new JMenu("Pomoc");
    private JMenu editMenu=new JMenu("Edycja");
    private JMenuItem saveFileMenu=new JMenuItem("Zapisz wynik do pliku");
    private JMenu optionsMenu=new JMenu("Opcje");
    private JCheckBox onlyToRead=new JCheckBox("Tylko do odczytu");
    private Object[] Keys = new Object[0];
    private String filename="verbs";
    private JPanel buttonPanel;
    private JPanel panel;
    private JPanel textPanel;
    private JPanel westPanel;
    private JPanel radioPanel=new JPanel();
    private static final int DEFAULT_WIDTH=1050;
    private static final int DEFAULT_HEIGHT=450;
    private int Q_COUNT=1;
    private int CORRECT=0;
    private int WRONG=0;
    private JButton buttonVocabs=new JButton("Wczytaj bibliotekę");
    private JButton button=new JButton();
    private JButton buttonSpeaker=new JButton();
    private JButton buttonCheck=new JButton("Sprawdź odpowiedź");
    private JTextField textField = new JTextField(20);
    private Map<String,String> vocabList=new HashMap<>();
    private JLabel answer=new JLabel();
    private JLabel questionCount=new JLabel();
    private JLabel answerCorrect=new JLabel();
    private JLabel correctCount=new JLabel();
    private JLabel wrongCount=new JLabel();
    private JLabel volumetext=new JLabel();
    private JLabel balancetext=new JLabel();
    private ButtonGroup buttonGroup=new ButtonGroup();
    private JRadioButton verbButton=new JRadioButton("Verbs",true);
    private JRadioButton adverbButton=new JRadioButton("Adverbs",false);
    private Integer i=0;
    private File file1;
    private double volume=0.7;
    private double balance=0.0;
    private JSlider volumeSlider=new JSlider(SwingConstants.VERTICAL);
    private JSlider balanceSlider=new JSlider(SwingConstants.HORIZONTAL,-100,100,0);
    private FileWriter file;
    private String CutText;
    private PasswordChooser dialog;
    private JLabel user=new JLabel();
    private Preferences root=Preferences.userRoot();
    private Preferences node=root.node("/src/files");
    public MemoFrame()
    {

        //setting user default preferences
        int left=node.getInt("left",100);
        int top=node.getInt("top",300);
        int width=node.getInt("width",DEFAULT_WIDTH);
        int height=node.getInt("height",DEFAULT_HEIGHT);
        setBounds(left,top,width,height);

        int sizeFont=node.getInt("fontSize",16);
        //preferences actions
        final JFileChooser chooser=new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new FileNameExtensionFilter("Pliki XML","xml"));

        JMenuItem exportItem=new JMenuItem("Eksportuj preferencje");
        editMenu.add(exportItem);
        JMenuItem importItem=new JMenuItem("Importuj preferencje");
        editMenu.add(importItem);
        editMenu.addSeparator();
        JMenuItem fontItem=new JMenuItem("Zmień rozmiar czcionki");
        editMenu.add(fontItem);
        editMenu.addSeparator();

        //preferences actions
        exportItem.addActionListener(l->{
            if(chooser.showSaveDialog(MemoFrame.this)==JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    savePreferences();
                    OutputStream out=new FileOutputStream(chooser.getSelectedFile());
                    node.exportSubtree(out);
                    out.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        importItem.addActionListener(l->{
            if(chooser.showOpenDialog(MemoFrame.this)==JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    InputStream in=new FileInputStream(chooser.getSelectedFile());
                    Preferences.importPreferences(in);
                    in.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        //Adding items to popupMenu
        popupMenu.addSeparator();
        popupMenu.add(cutItem);
        popupMenu.add(pasteItem);
        textField.setComponentPopupMenu(popupMenu);

        //PopupMenu Actions
        cutItem.addActionListener(l->{
            CutText=textField.getSelectedText();
            textField.setText(textField.getText().replaceAll(CutText,""));
            pasteItem.setEnabled(true);
        });
        pasteItem.addActionListener(l->{
            int p=textField.getCaretPosition();
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(textField.getText());
            stringBuilder.insert(p,CutText);
            textField.setText(stringBuilder.toString());
            pasteItem.setEnabled(false);
        });
        //Adding items to menubar
        fileMenu.add(saveFileMenu);
        fileMenu.add(startItem);
        helpMenu.add(helpItem);
        helpMenu.add(appItem);
        editMenu.add(volumeItem);
        editMenu.add(eqItem);
        editMenu.add(soundItem);
        editMenu.addSeparator();
        editMenu.add(optionsMenu);
        optionsMenu.add(onlyToRead);
        volumeItem.setSelected(false);
        eqItem.setSelected(false);
        saveFileMenu.setEnabled(false);
        soundItem.setSelected(false);
        onlyToRead.setSelected(true);
        appItem.setMnemonic('O');
        appItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));

        JDialog aboutApp=new AboutDialog(this);


        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);


        //MenuBar actions
        startItem.addActionListener(new ConnectAction());
        helpItem.addActionListener(l->{
            JOptionPane.showMessageDialog(null,"W pierwszej kolejności należy się zalogować.\nNastępnie wczytać daną bibliotekę.\n" +
                    "Przetłumaczone wyrażenie na język angielski należy wpisać w oknie edycyjnym.\n" +
                    "W celu przejścia do następnego wyrażenia należy wpierw sprawdzić poprawność, a następnie kliknąć klawisz z danym wyrażeniem","Instrukcja",JOptionPane.INFORMATION_MESSAGE);
        });
        volumeItem.addActionListener(l->{
            if(volumeItem.isSelected())volumeSlider.setVisible(true);
            else volumeSlider.setVisible(false);
        });
        eqItem.addActionListener(l->{
            if(eqItem.isSelected())balanceSlider.setVisible(true);
            else balanceSlider.setVisible(false);
        });
        onlyToRead.addActionListener(l->{
            if(onlyToRead.isSelected())saveFileMenu.setEnabled(false);
            else saveFileMenu.setEnabled(true);
        });
        saveFileMenu.addActionListener(l->{
            try {
                file=new FileWriter("wynik.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder sb=new StringBuilder();
            sb.append("Błędne: "+WRONG+" Poprawne: "+CORRECT+" Biblioteka: "+getSelectedButtonText(buttonGroup));
            sb.append(String.format("%n"));
            try {
                file.append(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Zapis wartości do pliku wykonany pomyślnie","Zapis do pliku",JOptionPane.INFORMATION_MESSAGE);
        });
        appItem.addActionListener(a->{
            //JOptionPane.showMessageDialog(null,"Wersja Alpha 1.0.0 \nCreated by: Michał Olech","O programie",JOptionPane.INFORMATION_MESSAGE);
            aboutApp.setVisible(true);
        });


        //Creating components
        panel=new JPanel();
        buttonPanel=new JPanel();
        textPanel=new JPanel();
        westPanel=new JPanel();
        button.setText("Quiz");

        //Setting Icon
        URL url=getClass().getResource("speaker-icon.png");
        Image speaker=new ImageIcon(url).getImage();
        Icon icon=new ImageIcon(speaker);
        buttonSpeaker.setIcon(icon);
        buttonSpeaker.setEnabled(false);

        //Setting Font

        Font labelFont = answer.getFont();
        correctCount.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        wrongCount.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        wrongCount.setForeground(Color.RED);
        correctCount.setForeground(Color.BLUE);
        correctCount.setVisible(false);
        wrongCount.setVisible(false);
        questionCount.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        buttonCheck.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        button.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        answerCorrect.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        answer.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        textField.setFont(new Font(labelFont.getName(), Font.PLAIN, sizeFont));
        textField.setColumns(25);
        buttonCheck.setEnabled(false);

        fontItem.addActionListener(l->{
            String s=(String)JOptionPane.showInputDialog(getParent(),"Wpisz rozmiar czcionki: ","Zmiana rozmiaru czcionki",JOptionPane.PLAIN_MESSAGE,null,null,null);
            Integer integer=Integer.valueOf(s);
            if(integer>2&&integer<50)
            {
                questionCount.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
                buttonCheck.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
                button.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
                answerCorrect.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
                answer.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
                textField.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
                correctCount.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
                wrongCount.setFont(new Font(labelFont.getName(), Font.PLAIN, integer));
            }
        });
        //Adding slider

        JPanel sliderPanel=new JPanel();
        JPanel volumePanel=new JPanel();
        JPanel balancePanel=new JPanel();
        volumePanel.add(volumeSlider);
        balancePanel.add(balanceSlider);
        volumePanel.add(volumetext,BorderLayout.SOUTH);
        balancePanel.add(balancetext,BorderLayout.SOUTH);
        sliderPanel.add(volumePanel);
        sliderPanel.add(balancePanel);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setSnapToTicks(true);
        volumeSlider.setPaintLabels(true);
        balanceSlider.setPaintTicks(true);
        balanceSlider.setMajorTickSpacing(25);
        balanceSlider.setMinorTickSpacing(5);
        balanceSlider.setSnapToTicks(true);
        balanceSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(event->{
            JSlider source=(JSlider) event.getSource();
            volume=(double)source.getValue()*0.01;
            volumetext.setText(source.getValue()+" %");
        });
        balanceSlider.addChangeListener(event->{
            JSlider source=(JSlider) event.getSource();
            balance=(double)source.getValue()*0.01;
            balancetext.setText(source.getValue()+"");
        });

        westPanel.add(sliderPanel);

        //Adding radiobuttons

        verbButton.addActionListener(event->filename="verbs");
        buttonGroup.add(verbButton);
        radioPanel.add(verbButton);

        adverbButton.addActionListener(event->filename="adverbs");
        buttonGroup.add(adverbButton);
        radioPanel.add(adverbButton);

        Border softborder=BorderFactory.createLineBorder(Color.DARK_GRAY,2,true);
        Border buttonborder=BorderFactory.createTitledBorder(softborder,"Wybierz bibliotekę", TitledBorder.LEFT,TitledBorder.TOP,new Font("New Font", Font.ITALIC, 10),Color.BLUE);
        radioPanel.setBorder(buttonborder);


        //Adding to panel
        GroupLayout layout=new GroupLayout(getContentPane());
        setLayout(layout);
        setJMenuBar(menuBar);
        layout.setHorizontalGroup
                (
                        layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup
                                (
                                        layout.createParallelGroup()
                                                .addComponent(button)
                                                .addComponent(questionCount)
                                                .addComponent(user)
                                )
                        .addGroup
                                (
                                        layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
                                                .addComponent(textField)
                                                .addComponent(buttonCheck)
                                                .addComponent(answer)
                                                .addComponent(correctCount)
                                                .addComponent(wrongCount)
                                                .addComponent(answerCorrect)
                                )
                        .addGroup
                                (
                                        layout.createParallelGroup(GroupLayout.Alignment.CENTER,false)
                                        .addComponent(radioPanel)
                                        .addComponent(buttonVocabs)
                                )
                        .addGroup
                                (
                                        layout.createParallelGroup()
                                        .addGap(50)
                                        .addComponent(menuToolBar)
                                )
                );


        layout.setVerticalGroup
                (
                        layout.createSequentialGroup()

                        .addGroup
                                (
                                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE,true)
                                        .addComponent(menuToolBar)
                                        .addGroup
                                                (
                                                        layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup
                                                                (
                                                                       layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                             .addComponent(radioPanel)
                                                                             .addComponent(button)
                                                                             .addComponent(textField)
                                                                 )
                                                        .addGroup
                                                                (
                                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(questionCount)
                                                                                .addComponent(buttonCheck)
                                                                                .addComponent(buttonVocabs)
                                                                )
                                                        .addGroup
                                                                (
                                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(answer)
                                                                )
                                                        .addGroup
                                                                (
                                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(answerCorrect)
                                                                )
                                                        .addGroup
                                                                (
                                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(correctCount)
                                                                )
                                                        .addGroup
                                                                (
                                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(wrongCount)
                                                                        .addComponent(user)
                                                                )
                                                )

                                )
                        .addGroup
                                (
                                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE)

                                )
                        .addContainerGap()
                        .addGroup
                                (
                                        layout.createSequentialGroup()

                                )

                );
            button.setEnabled(false);
        //RadioButtons actions
        buttonVocabs.addActionListener(event->{
                VocabInput vocabInput=new VocabInput();
                vocabList=vocabInput.readFile(filename) ;
                Keys = vocabList.keySet().toArray();
            button.setEnabled(true);
            i=vocabList.size();
        });
        buttonVocabs.setEnabled(false);
        textField.setEnabled(false);
        radioPanel.setVisible(false);
        menuToolBar.setVisible(false);
        Action vocabAction=new VocabAction();
        Action checkAction=new CheckAction();

        //Adding items to toolbar
        toolBar.add(buttonSpeaker);
        toolBar.add(volumePanel);
        toolBar.add(balancePanel);
        menuToolBar.add(toolBar);

        //Show/Hide menuToolbar
        soundItem.addActionListener(l->{
            if(soundItem.isSelected()){
                menuToolBar.setVisible(true);
                if(eqItem.isSelected())balanceSlider.setVisible(true);
                else balanceSlider.setVisible(false);
                if(volumeItem.isSelected())volumeSlider.setVisible(true);
                else volumeSlider.setVisible(false);
            }
            else menuToolBar.setVisible(false);
        });

        //Window close message
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int selection=JOptionPane.showConfirmDialog(null,"Czy zamknąć program? ","Wiadomość",JOptionPane.INFORMATION_MESSAGE);
                if(selection==JOptionPane.YES_OPTION){e.getWindow().dispose();}
            }
        });

        //Linking keys with names
        InputMap imap=panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"panel.textField");

        //Linking names with actions

        ActionMap amap=panel.getActionMap();
        amap.put("panel.textField",checkAction);

        //Setting actions

        button.addActionListener(vocabAction);
        addMouseMotionListener(new MouseMotionHandler());
        buttonCheck.addActionListener(checkAction);
        buttonSpeaker.addActionListener(event -> {
            try {
                if(i==-1)i=0;
                new VoiceSpeaker(Keys[i].toString(),volume,balance);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        });
    }
    private class VocabAction extends AbstractAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(i==vocabList.size()){
                radioPanel.setVisible(false);
                i=-1;
                button.setText("Naciśnij aby rozpocząć");
                buttonCheck.setEnabled(false);
                buttonSpeaker.setEnabled(false);
                textField.setEnabled(false);
                textField.setVisible(false);
                correctCount.setVisible(true);
                wrongCount.setVisible(true);
                Q_COUNT=0;
                CORRECT=0;WRONG=0;
                saveFileMenu.setEnabled(false);
                textField.setText("");
            }
            else {
                textField.setText("");
                saveFileMenu.setEnabled(true);
                radioPanel.setVisible(true);
                Q_COUNT++;
                i++;
                button.setText(Keys[i].toString());
                buttonCheck.setEnabled(true);
                textField.setEnabled(true);
                textField.setVisible(true);
                button.setEnabled(false);
                buttonSpeaker.setEnabled(true);
                questionCount.setText("Numer pytania: "+Integer.toString(Q_COUNT)+"   Pula pytań: "+vocabList.size());
                answer.setText("");
                answerCorrect.setText("");
            }

        }
    }
    private class CheckAction extends AbstractAction implements ActionListener
    {
        private String an;
        CheckAction()
        {
        }

        public void actionPerformed(ActionEvent event)
        {
            an=textField.getText().toLowerCase();
            Object ob=button.getText();
            if(vocabList.get(ob).toLowerCase().equals(an))
            {
                answer.setForeground(Color.BLACK);
                answer.setText("Dobra odpowiedź!");
                CORRECT++;
            }
            else {
                answer.setForeground(Color.RED);
                answer.setText("Zła odpowiedź! Prawidłowa: ");
                answerCorrect.setText(vocabList.get(ob));
                WRONG++;
            }
            buttonCheck.setEnabled(false);
            textField.setEnabled(false);
            button.setEnabled(true);
            button.setForeground(Color.BLACK);
            wrongCount.setText("Błędne: "+WRONG);
            correctCount.setText("Poprawne: "+CORRECT);
        }
    }

    private class MouseMotionHandler implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent event)
        {
            if(button.isEnabled())
            {
                double x = button.getLocation().getX();
                double width = button.getWidth();
                if((x<event.getPoint().getX()&&event.getPoint().getX()<x+width)&&(48.0<event.getPoint().getY()&&event.getPoint().getY()<82.0))
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                else
                    setCursor(Cursor.getDefaultCursor());
            }else
                setCursor(Cursor.getDefaultCursor());

        }
        public void mouseDragged(MouseEvent event)
        {}
    }
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public class AboutDialog extends JDialog
    {
        public AboutDialog(JFrame owner)
        {
            super(owner,"O programie",true);
            add(new JLabel("<html><h1><i>Wersja alpha 1.0.0</i></h1><hr>Created by: Michał Olech</html>"),BorderLayout.CENTER);
            JPanel panelNew=new JPanel();
            JButton ok=new JButton("OK");

            ok.addActionListener(e -> setVisible(false));
            panelNew.add(ok);
            add(panelNew,BorderLayout.SOUTH);
            setSize(250,150);
            setLocation(500,350);
        }
    }

    private class ConnectAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(dialog==null)dialog=new PasswordChooser();
            try {
                if(dialog.showDialog(MemoFrame.this,"Logowanie..."))
                {
                    User u=dialog.getUser();
                    user.setText("Zalogowano jako: "+u.getName());
                    buttonVocabs.setEnabled(true);
                    radioPanel.setVisible(true);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void savePreferences()
    {
        node.putInt("left",getX());
        node.putInt("top",getY());
        node.putInt("width",getWidth());
        node.putInt("height",getHeight());
        node.putInt("fontSize",correctCount.getFont().getSize());
    }
}
