package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class PagesSearch implements Callable<Integer>
{
    private String url;
    private Integer pages;
    private ExecutorService pool;
    public PagesSearch(String url)
    {
        this.url=url;
    }

    @Override
    public Integer call() throws Exception
    {
                URL u=new URL(url);
                search(new Scanner(u.openStream(), "ISO-8859-2"));
        return pages;
    }

    public void search(Scanner in)
    {
        String inputLine="";
            int t=0;
            while (in.hasNextLine()){t++;if(t==870)break;}
            while ((inputLine = in.nextLine()) != null)
            {
                if(inputLine.contains("numberOfPages")){
                    char[] temp= inputLine.toCharArray();
                    for(int w=142;w<temp.length;w++)
                    {
                        if(temp[w]=='<'){pages=Integer.valueOf(inputLine.substring(141,w));break;}
                    }
                    break;
                }
            }
    }
}
