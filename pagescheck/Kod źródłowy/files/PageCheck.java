package files;


import javafx.scene.Parent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.text.html.parser.DTD;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by MO on 2017-07-14.
 */
public class PageCheck extends JFrame
{
    private JMenuBar menuBar=new JMenuBar();
    private LoadingBar loadingBar=new LoadingBar();
    private Icon bookIcon;
    private JLabel image;
    private JMenuItem appItem=new JMenuItem("O programie");
    private JMenuItem helpItem=new JMenuItem("Instrukcja");
    private JMenuItem backGItem=new JMenuItem("Zmień kolor tła");
    private JMenuItem sizeFItem=new JMenuItem("Zmień rozmiar czcionki");
    private JMenu fileMenu=new JMenu("Plik");
    private JMenu helpMenu=new JMenu("Pomoc");
    private JMenu editMenu=new JMenu("Edycja");
    private JMenuItem saveFileMenu=new JMenuItem("Zapisz do pliku");
    private JMenu optionsMenu=new JMenu("Opcje");
    private JCheckBox onlyToRead=new JCheckBox("Tylko do odczytu");
    private JPanel panelSouth=new JPanel();
    private JPanel panelNorth=new JPanel(new GridBagLayout());
    private JPanel panelCenter=new JPanel(new GridBagLayout());
    private JLabel currentTime=new JLabel();
    private JLabel bookNotFound=new JLabel("");
    private JLabel text=new JLabel("Wpisz termin                ");
    private JLabel text1=new JLabel("Wpisz nr strony              ");
    private JLabel text2=new JLabel("Wpisz ilość stron książki lub je dodaj znakiem '+'");
    private JLabel subject=new JLabel("<-----  Tytuł książki/Autor");
    private JLabel answer=new JLabel();
    private JTextField pagesSText=new JTextField();
    private JTextField pagesFText=new JTextField();
    private JTextField dateText=new JTextField("31-08-2017");
    private JTextField titleText=new JTextField("Java");
    private JComboBox<String> bookBox=new JComboBox<>();
    private JTextField authorText=new JTextField("Horstmann");
    private JButton newDateButton=new JButton("Wczytaj ");
    private JButton newBookButton=new JButton("Załaduj dane");
    private File propertiesFile;
    private String path = PageCheck.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/files/data.txt";
    private BufferedReader bufferedReader;
    private File file;
    private FileWriter fileWriter;
    private URI pathUri;
    private Properties settings;
    private int DEFAULT_WIDTH=1200;
    private int DEFAULT_HEIGHT=550;
    private Calendar currentDate;
    private Calendar departDate;
    private long difference;
    private JComboBox <String> comboBox=new JComboBox<>();
    private boolean onlySNumbers=false;
    private boolean onlyFNumbers=false;
    private int sizeFont=16;
    private boolean isLoaded=false;
    private List<Book> bookList=new ArrayList<>();
    public PageCheck(ImageIcon i) throws IOException {
        URL pathURL=new URL("file:"+path);
        bookBox.setPreferredSize(new Dimension(350,sizeFont+5));
        newBookButton.setEnabled(false);
        bookIcon=i;
        setLayout(new BorderLayout());
        titleText.setColumns(550);
        pagesSText.setColumns(25);
        pagesFText.setColumns(15);
        dateText.setColumns(10);
        authorText.setColumns(35);
        bookNotFound.setFont(new Font("New Font",Font.CENTER_BASELINE, 12));
        JDialog aboutApp=new AboutDialog(this);
        appItem.setMnemonic('O');
        appItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        loadingBar.setVisible(false);

        // create menubar
        fileMenu.add(saveFileMenu);
        helpMenu.add(helpItem);
        helpMenu.add(appItem);
        editMenu.add(sizeFItem);
        editMenu.add(backGItem);
        editMenu.addSeparator();
        editMenu.add(optionsMenu);
        optionsMenu.add(onlyToRead);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        bufferedReader = new BufferedReader(new InputStreamReader(pathURL.openStream(),"UTF-8"));
        String line;
        String lines[]=new String[7];
        while((line=bufferedReader.readLine())!=null)
            {
                comboBox.addItem(line);
            }
        //adding date
        SimpleDateFormat DateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        //setting proporties
        String userDir=System.getProperty("user.home");
        File proportiesDir=new File(userDir,".pagescheck");
        if(!proportiesDir.exists())proportiesDir.mkdir();
        propertiesFile=new File(proportiesDir,"program.proporties");

        Properties defaultSettings=new Properties();
        defaultSettings.put("left","50");
        defaultSettings.put("top","200");
        defaultSettings.put("width",""+DEFAULT_WIDTH);
        defaultSettings.put("height",""+DEFAULT_HEIGHT);
        defaultSettings.put("backColor","255,255,255");
        defaultSettings.put("fontSize","16");
        defaultSettings.put("title","Java Podstawy");
        defaultSettings.put("author","Cay S. Horstmann");

        settings=new Properties(defaultSettings);

        if(propertiesFile.exists())
        {
            try(InputStream in=new FileInputStream(propertiesFile))
            {
                settings.load(in);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

            int left=Integer.valueOf(settings.getProperty("left"));
            int top=Integer.valueOf(settings.getProperty("top"));
            int width=Integer.valueOf(settings.getProperty("width"));
            int height=Integer.valueOf(settings.getProperty("height"));
            setBounds(left,top,width,height);
            String[] strColor=settings.getProperty("backColor").split(",");
            Color color=new Color(Integer.valueOf(strColor[0]),Integer.valueOf(strColor[1]),Integer.valueOf(strColor[2]));
            panelCenter.setBackground(color);
            panelSouth.setBackground(color);

            sizeFont=Integer.valueOf(settings.getProperty("fontSize"));
            titleText.setFont(new Font("New Font",Font.BOLD, sizeFont));
            authorText.setFont(new Font("New Font",Font.BOLD, sizeFont));
            text.setFont(new Font("New Font",Font.BOLD, sizeFont));
            text1.setFont(new Font("New Font",Font.BOLD, sizeFont));
            text2.setFont(new Font("New Font",Font.BOLD, sizeFont-4));
            pagesSText.setFont(new Font("New Font",Font.PLAIN, sizeFont));
            pagesFText.setFont(new Font("New Font",Font.PLAIN, sizeFont));
            dateText.setFont(new Font("New Font",Font.ITALIC, sizeFont));
            titleText.setFont(new Font("New Font",Font.ITALIC, sizeFont));
            authorText.setFont(new Font("New Font",Font.ITALIC, sizeFont));
            answer.setFont(new Font("New Font",Font.CENTER_BASELINE, sizeFont));
        }

        //setting actions
        backGItem.addActionListener(l->{
            Color chooser=JColorChooser.showDialog(getParent(),"Wybierz kolor tła",Color.WHITE);
            panelCenter.setBackground(chooser);
            panelSouth.setBackground(chooser);
        });
        sizeFItem.addActionListener(l->{
            String s=(String)JOptionPane.showInputDialog(getParent(),"Wpisz rozmiar czcionki: ","Zmiana rozmiaru czcionki",JOptionPane.PLAIN_MESSAGE,null,null,null);
            Integer integer=Integer.valueOf(s);
            if(integer>2&&integer<50)
            {
                titleText.setFont(new Font("New Font",Font.BOLD, integer));
                authorText.setFont(new Font("New Font",Font.BOLD, integer));
                text.setFont(new Font("New Font",Font.BOLD, integer));
                text1.setFont(new Font("New Font",Font.BOLD, integer));
                text2.setFont(new Font("New Font",Font.BOLD, integer));
                pagesSText.setFont(new Font("New Font",Font.PLAIN, integer));
                pagesFText.setFont(new Font("New Font",Font.PLAIN, integer));
                dateText.setFont(new Font("New Font",Font.ITALIC, integer));
                titleText.setFont(new Font("New Font",Font.ITALIC, integer));
                authorText.setFont(new Font("New Font",Font.ITALIC, integer));
                answer.setFont(new Font("New Font",Font.CENTER_BASELINE, integer));
                pagesSText.setColumns(5);
                pagesFText.setColumns(15);
                dateText.setColumns(10);
                authorText.setColumns(25);
            }
        });
        onlyToRead.addActionListener(l->{
            if(onlyToRead.isSelected())saveFileMenu.setEnabled(false);
            else saveFileMenu.setEnabled(true);
        });
        appItem.addActionListener(a->{
            //JOptionPane.showMessageDialog(null,"Wersja Alpha 1.0.0 \nCreated by: Michał Olech","O programie",JOptionPane.INFORMATION_MESSAGE);
            aboutApp.setVisible(true);
        });
        newDateButton.setEnabled(false);
        newDateButton.addActionListener(event->
                {
                    currentDate=GregorianCalendar.getInstance();
                    currentTime.setText(DateFormat.format(currentDate.getTime()));
                    departDate=new GregorianCalendar(Integer.valueOf(dateText.getText().substring(6,10)),Integer.valueOf(dateText.getText().substring(3,5)),Integer.valueOf(dateText.getText().substring(0,2)));
                    difference=(departDate.getTimeInMillis()/60000)-(currentDate.getTimeInMillis()/60000)-30*24*60;
                    Float numberPages=pagesInput(pagesFText.getText());
                    answer.setText("Liczba dni: "+Long.toString(difference/(24*60))+"       Ilość stron do przeczytania dziennie: "+Math.ceil((numberPages-Long.valueOf(pagesSText.getText()))/(difference/(24*60))));
                    if(pagesSText.getText()!=null&&pagesFText.getText()!=null)
                    {
                        for(int j=0;j<6;j++)
                        {
                            lines[j]=comboBox.getItemAt(j);
                        }
                        comboBox.removeAllItems();
                        String str=("Dni: "+difference/(24*60)+" "+"Ilość stron dziennie: "+Math.ceil((numberPages-Long.valueOf(pagesSText.getText()))/(difference/(24*60)))+" "+"Tematyka: "+titleText.getText()+"Autor: "+authorText.getText()+" "+DateFormat.format(currentDate.getTime()));
                        comboBox.insertItemAt(str,0);
                        for(int j=0;j<5;j++)
                        {
                            comboBox.insertItemAt(lines[j],j+1);
                        }
                    }
                }
        );
        pagesSText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                char list[]=pagesSText.getText().toString().toCharArray();
                if(list.length==0){onlySNumbers=false;newDateButton.setEnabled(false);return;}
                for(int i=0;i<list.length;i++)
                {
                    if(list[i]<48||list[i]>57){onlySNumbers=false;newDateButton.setEnabled(false);break;}
                    else onlySNumbers=true;
                }
                if(onlySNumbers==true&&onlyFNumbers==true)newDateButton.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                char list[]=pagesSText.getText().toString().toCharArray();
                if(list.length==0){onlySNumbers=false;newDateButton.setEnabled(false);return;}
                for(int i=0;i<list.length;i++)
                {
                    if(list[i]<48||list[i]>57){onlySNumbers=false;newDateButton.setEnabled(false);break;}
                    else onlySNumbers=true;
                }
                if(onlySNumbers==true&&onlyFNumbers==true)newDateButton.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                char list[]=pagesSText.getText().toString().toCharArray();
                if(list.length==0){onlySNumbers=false;newDateButton.setEnabled(false);return;}
                for(int i=0;i<list.length;i++)
                {
                    if(list[i]<48||list[i]>57){onlySNumbers=false;newDateButton.setEnabled(false);break;}
                    else onlySNumbers=true;
                }
                if(onlySNumbers==true&&onlyFNumbers==true)newDateButton.setEnabled(true);
            }
        });
        pagesFText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                char list[]=pagesFText.getText().toString().toCharArray();
                if(list.length==0){onlyFNumbers=false;newDateButton.setEnabled(false);return;}
                else if(list[list.length-1]=='+'||list[0]=='+'){onlyFNumbers=false;newDateButton.setEnabled(false);return;}
                else
                {
                    for(int i=0;i<list.length;i++)
                    {
                        if((list[i]!=43&&list[i]<48)||list[i]>57){onlyFNumbers=false;newDateButton.setEnabled(false);break;}
                        else onlyFNumbers=true;
                    }
                    if(onlySNumbers==true&&onlyFNumbers==true)newDateButton.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                char list[]=pagesFText.getText().toString().toCharArray();
                if(list.length==0){onlyFNumbers=false;newDateButton.setEnabled(false);return;}
                else if(list[list.length-1]=='+'||list[0]=='+'){onlyFNumbers=false;newDateButton.setEnabled(false);return;}
                else
                {
                    for(int i=0;i<list.length;i++)
                    {
                        if((list[i]!=43&&list[i]<48)||list[i]>57){onlyFNumbers=false;newDateButton.setEnabled(false);break;}
                        else onlyFNumbers=true;
                    }
                    if(onlySNumbers==true&&onlyFNumbers==true)newDateButton.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                char list[]=pagesFText.getText().toString().toCharArray();
                if(list.length==0){onlyFNumbers=false;newDateButton.setEnabled(false);return;}
                else if(list[list.length-1]=='+'||list[0]=='+'){onlyFNumbers=false;newDateButton.setEnabled(false);return;}
                else
                {
                    for(int i=0;i<list.length;i++)
                    {
                        if((list[i]!=43&&list[i]<48)||list[i]>57){onlyFNumbers=false;newDateButton.setEnabled(false);break;}
                        else onlyFNumbers=true;
                    }
                    if(onlySNumbers==true&&onlyFNumbers==true)newDateButton.setEnabled(true);
                }
            }
        });
        helpItem.addActionListener(l->{
            JOptionPane.showMessageDialog(null,"1. Pierwsze pole edycyjne służy do wpisania daty wyjazdu\n2. Drugie pole edycyjne służy do wpisania ilości aktualnie przeczytanych stron\n3. Trzecie pole edycyjne służy do wpisania ilości stron jednej lub więcej książek ze znakiem '+'\n" +
                    "Aby zapisać do pliku należy najpierw wczytać dane do listy za pomocą przycisku 'wczytaj'.\n" +
                    "W celu wczytania bazy książek do listy należy najpierw wpisać sam tytuł bądź samego autora.\n" +
                    "Baza zostanie automatycznie wczytana po wyjściu z pola edycyjnego z nazwą autora","Komunikat",JOptionPane.INFORMATION_MESSAGE);
        });
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int selection=JOptionPane.showConfirmDialog(null,"Czy zamknąć program? ","Wiadomość",JOptionPane.INFORMATION_MESSAGE);
                if(selection==JOptionPane.YES_OPTION){
                    settings.put("left",String.valueOf(getX()));
                    settings.put("top",String.valueOf(getY()));
                    settings.put("width",String.valueOf(getWidth()));
                    settings.put("height",String.valueOf(getHeight()));
                    settings.put("backColor",String.valueOf(panelCenter.getBackground().getRed())+","+String.valueOf(panelCenter.getBackground().getGreen())+","+String.valueOf(panelCenter.getBackground().getBlue()));
                    settings.put("fontSize",String.valueOf(titleText.getFont().getSize()));
                    settings.put("title",titleText.getText());
                    settings.put("author",authorText.getText());

                    try(OutputStream out=new FileOutputStream(propertiesFile))
                    {
                        settings.store(out,"Program Proporties");
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                    e.getWindow().dispose();
                }
            }
        });
        saveFileMenu.addActionListener(l->{
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<6;j++)
            {
                sb.append(comboBox.getItemAt(j));
                sb.append(String.format("%n"));
            }
            try {
                fileWriter=new FileWriter("data.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.append(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Zapis wartości do pliku wykonany pomyślnie","Zapis do pliku",JOptionPane.INFORMATION_MESSAGE);
        });
        SwingUtilities.invokeLater(() -> authorText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                bookBox.removeAllItems();
                bookList.removeAll(bookList);
                String[] title=titleText.getText().split(" ");
                StringBuilder sb=new StringBuilder();
                StringBuilder search=new StringBuilder();
                sb.append("https://helion.pl/search?amp;qa=&amp;serwisyall=0&amp;szukaj=");
                String Base_URL;
                String[] author=authorText.getText().split(" ");
                for(int s=0;s<author.length;s++)
                {
                    author[s]=author[s].toLowerCase();
                    if(author[s].contains("ę")==true)author[s]=author[s].replaceAll("ę","e");
                    if(author[s].contains("ó")==true)author[s]=author[s].replaceAll("ó","o");
                    if(author[s].contains("ą")==true)author[s]=author[s].replaceAll("ą","a");
                    if(author[s].contains("ś")==true)author[s]=author[s].replaceAll("ś","s");
                    if(author[s].contains("ł")==true)author[s]=author[s].replaceAll("ł","l");
                    if(author[s].contains("ż")==true)author[s]=author[s].replaceAll("ż","z");
                    if(author[s].contains("ź")==true)author[s]=author[s].replaceAll("ź","z");
                    if(author[s].contains("ć")==true)author[s]=author[s].replaceAll("ć","c");
                    if(author[s].contains("ń")==true)author[s]=author[s].replaceAll("ń","n");
                    if(author[s].contains("."))author[s]=author[s].substring(0,author[s].length()-1);
                }
                for(int s=0;s<title.length;s++)
                {
                    title[s]=title[s].toLowerCase();
                    if(title[s].contains("ę"))title[s]=title[s].replaceAll("ę","e");
                    if(title[s].contains("ó"))title[s]=title[s].replaceAll("ó","o");
                    if(title[s].contains("ą"))title[s]=title[s].replaceAll("ą","a");
                    if(title[s].contains("ś"))title[s]=title[s].replaceAll("ś","s");
                    if(title[s].contains("ł"))title[s]=title[s].replaceAll("ł","l");
                    if(title[s].contains("ż"))title[s]=title[s].replaceAll("ż","z");
                    if(title[s].contains("ź"))title[s]=title[s].replaceAll("ź","z");
                    if(title[s].contains("ć"))title[s]=title[s].replaceAll("ć","c");
                    if(title[s].contains("ń"))title[s]=title[s].replaceAll("ń","n");
                    if(title[s].contains("."))title[s]=title[s].substring(0,title[s].length()-1);
                    if(title[s].contains("++"))title[s]=title[s].replace("++","%252B%252B");
                }

                boolean czy=false;
                if(title[0].toCharArray().length>1)
                {
                    for(int s=0; s<title.length; s++)
                    {
                        if(title[s].length()>1||((title[s].toCharArray()[0]>96&&title[s].toCharArray()[0]<123)||(title[s].toCharArray()[0]>47&&title[s].toCharArray()[0]<58))||title[s].toCharArray()[0]==37)
                        {
                            sb.append(title[s]);
                            sb.append("+");
                            search.append(title[s]);
                            search.append("-");
                            if(s==5)break;
                        }
                    }
                }

                    for (String s : author)
                    {
                        sb.append(s);
                        sb.append("+");
                        search.append(s);
                        search.append("-");
                    }
                    search.deleteCharAt(search.length()-1);

                String searchUrl=search.toString();
                if(searchUrl.contains("%252B%252B"))searchUrl=searchUrl.replace("%252B%252B-","-");
                sb.append("&amp;wprzyg=&amp;wsprzed=&amp;wyczerp=&formaty=d-em-p&ceny=-");
                Base_URL = sb.toString();
                try {
                    URL u = new URL(Base_URL);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(u.openStream(), "ISO-8859-2"));
                    String inputLine ="";
                    String Url_Look="";
                    String Url_Inside="";
                    String titleT="";
                    String url="";
                    String authorT="";
                    String urlIcon="";
                    int t=0;
                    while ((in.readLine()) != null){t++;if(t==850)break;}
                    while ((inputLine = in.readLine()) != null){
                        if(inputLine.contains("<li>")){t=0;}
                            if(inputLine.contains("img class"))
                            {
                                char[] temp= inputLine.toCharArray();
                                for(int w=146;w<temp.length;w++)
                                {
                                    if(temp[w]=='"'){urlIcon= inputLine.substring(144,w);break;}
                                }
                            }
                            if(t>35&&inputLine.contains("link"))
                            {
                                char[] temp= inputLine.toCharArray();

                                for(int w=34,c=0;w<temp.length;w++)
                                {
                                    if(temp[w]=='"'){url= inputLine.substring(33,w);c=w+2;}
                                    if(temp[w]=='<'){titleT= inputLine.substring(c,w);break;}
                                }
                                if(url.charAt(0)!='h')url="h"+url;
                                url="https:"+url.substring(5);
                            }
                            if(t>35&&inputLine.contains("author"))
                            {
                                char[] temp= inputLine.toCharArray();

                                for(int w=62,f=0;w<temp.length;w++)
                                {
                                    if(temp[w]=='>')f=w+1;
                                    if(temp[w]=='<'){authorT= inputLine.substring(f,w);break;}
                                }
                                bookList.add(new Book(titleT,authorT,url,urlIcon));
                            }
                            if(t==100)break;
                            t++;
                    }
                    in.close();
                    for(Book book:bookList)
                        bookBox.addItem(book.getTitle());


                    isLoaded=false;
                    if(bookBox.getItemCount()==0)newBookButton.setEnabled(false);
                    else newBookButton.setEnabled(true);

                } catch (Exception de) {
                    bookNotFound.setText("<html><font color=\"red\">Nie znaleziono książki o tym tytule i/lub autorze :(</font></html>");
                }

            }
        }));
        newBookButton.addActionListener(l->
        {
                if(isLoaded==false)
                {
                    String urlList[]=new String[bookList.size()];
                    for(Book book:bookList)
                        urlList[bookList.indexOf(book)]=book.getPageUrl();
                    Runnable r=()->{
                        newBookButton.setEnabled(false);
                        loadingBar.setVisible(true);
                    for(int a=0;a<urlList.length;a++)
                    {
                        int te=a;


                            setLoadingBar(te);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        ExecutorService pool=Executors.newCachedThreadPool();
                        PagesSearch search1=new PagesSearch(urlList[a]);
                        Future<Integer> task=pool.submit(search1);

                        try
                        {
                            bookList.get(a).setPages(task.get());
                        }catch (ExecutionException ex)
                        {
                        } catch (InterruptedException e) {
                        }
                        pool.shutdown();
                    }
                        newBookButton.setEnabled(true);
                        loadingBar.setVisible(false);
                        titleText.setText(bookList.get(bookBox.getSelectedIndex()).getTitle());
                        authorText.setText(bookList.get(bookBox.getSelectedIndex()).getAuthor());
                        pagesFText.setText(String.valueOf(bookList.get(bookBox.getSelectedIndex()).getPages()));
                        URL url2 = null;
                        try {
                            url2 = new URL(bookList.get(bookBox.getSelectedIndex()).getCoverUrl());
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        }
                        BufferedImage c = null;
                        try {
                            c = ImageIO.read(url2);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        image.setIcon(new ImageIcon(c));
                        image.revalidate();
                        image.repaint();
                        image.update(image.getGraphics());
                    };
                    Thread thread=new Thread(r);
                    thread.start();
                    isLoaded=true;
                }
                else
                {
                    URL url2 = null;
                    try {
                        url2 = new URL(bookList.get(bookBox.getSelectedIndex()).getCoverUrl());
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                    BufferedImage c = null;
                    try {
                        c = ImageIO.read(url2);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    image.setIcon(new ImageIcon(c));
                    image.revalidate();
                    image.repaint();
                    image.update(image.getGraphics());
                }
        });

        //adding to panels
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        panelCenter.add(text,c);
        c.insets = new Insets(0,0,0,0);
        c.gridx = 1;
        c.gridy = 0;
        panelCenter.add(text1,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,0);
        c.gridx = 2;
        c.gridy = 0;
        panelCenter.add(text2,c);
        panelSouth.add(newDateButton);
        panelSouth.add(comboBox);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,10,5);
        c.gridx = 0;
        c.gridy = 1;
        panelCenter.add(dateText,c);
        c.gridx = 1;
        c.gridy = 1;
        panelCenter.add(pagesSText,c);
        c.gridx = 2;
        c.gridy = 1;
        panelCenter.add(pagesFText,c);
        c.gridx = 0;
        c.gridy = 2;
        panelCenter.add(titleText,c);
        c.gridx = 1;
        c.gridy = 2;
        panelCenter.add(authorText,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,30);
        c.gridx = 2;
        c.gridy = 2;
        panelCenter.add(subject,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,5);
        c.gridx = 3;
        c.gridy = 2;
        panelCenter.add(answer,c);
        panelSouth.add(currentTime);
        URL url=PageCheck.class.getResource("tlo.jpg");
        Image tlo=new ImageIcon(url).getImage();
        bookIcon=new ImageIcon(tlo);
        image=new JLabel("",bookIcon,JLabel.CENTER);
        c.insets = new Insets(10,0,0,5);
        c.gridx = 3;
        c.gridy = 3;
        panelCenter.add(image,c);
        c.insets = new Insets(10,0,0,5);
        c.gridx = 0;
        c.gridy = 3;
        panelCenter.add(bookNotFound,c);
        c.insets = new Insets(10,0,0,5);
        c.gridx = 0;
        c.gridy = 4;
        panelCenter.add(bookBox,c);
        c.insets = new Insets(10,0,0,5);
        c.gridx = 1;
        c.gridy = 4;
        panelCenter.add(newBookButton,c);
        c.insets = new Insets(10,0,0,5);
        c.gridx = 2;
        c.gridy = 4;
        panelCenter.add(loadingBar,c);
        add(panelNorth,BorderLayout.NORTH);
        add(panelCenter,BorderLayout.CENTER);
        add(panelSouth,BorderLayout.SOUTH);
        setJMenuBar(menuBar);
        invalidate();
        validate();
        repaint();
    }
    public Float pagesInput(String str)
    {
        char ch[]=str.toCharArray();
            Float num=new Float(0);
        int j=0;
            for(int i=0;i<ch.length;i++)
            {
                if(ch[i]=='+'){
                    num=num+Long.valueOf(str.substring(j,i));
                    j=i+1;
                }
            }
            num=num+Float.valueOf(str.substring(j));
                return num;
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

    public class LoadingBar extends JPanel
    {
        private Rectangle2D rec=new Rectangle2D.Double(0,0,0,20.0);
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2=(Graphics2D)g;
            g2.fill(rec);
        }
        public void setRec(int i)
        {
            rec.setRect(0,0,i,20.0);
        }
    }
    public void setLoadingBar(int much)
    {
        int temp=loadingBar.getWidth()/bookList.size();
        loadingBar.setRec(temp*(much+1));
        loadingBar.repaint();
    }

}
