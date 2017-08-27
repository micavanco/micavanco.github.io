package files;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by MO on 2017-07-15.
 */
public class VocabInput
{

    public VocabInput(){}
    public Map<String,String> readFile(String filename)
    {
        String csvFile = filename+".csv";
        String line = "";
        String cvsSplitBy = ";";
        Map<String,String>vocabList=new HashMap<>();
        InputStream fileReader=VocabInput.class.getResourceAsStream(csvFile);
        Scanner br = new Scanner(fileReader,"UTF-8");
            while (br.hasNextLine()) {
                line=br.nextLine();
                String[] Vocab = line.split(cvsSplitBy);
                vocabList.put(Vocab[0],Vocab[1]);
            }
        return vocabList;
    }
}
