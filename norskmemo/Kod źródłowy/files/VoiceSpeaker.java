package files;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import us.monoid.web.BinaryResource;
import us.monoid.web.Resty;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by MO on 2017-07-10.
 */
public class VoiceSpeaker
{
    private static String Base_URL;
    private double volume;
    private double balance;
    public VoiceSpeaker(String text,double v,double b) throws IOException, URISyntaxException, JavaLayerException
    {
        volume=v;
        balance=b;
        Base_URL="https://code.responsivevoice.org/getvoice.php?t="+text.replaceAll(" ","%20").toLowerCase()+"&tl=no";
        try {
            textToSpeach("Siema");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void textToSpeach(String text) throws IOException, URISyntaxException, JavaLayerException, InterruptedException {
        File file=new File("translate.mp3");
        BinaryResource res=new Resty().bytes(new URI(Base_URL).toASCIIString());
        res.save(file);
        files.Player.main(new String[0],file,volume,balance);
        //file.delete();
    }

}
