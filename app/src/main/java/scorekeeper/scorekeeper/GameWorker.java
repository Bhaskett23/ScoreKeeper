package scorekeeper.scorekeeper;

import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;


public class GameWorker {

    public static String UpdateTurnCounter(String turn)
    {
        int length = turn.length();
        char currentTurn =(char) turn.charAt(length-1);


        return "Turn " + currentTurn;
    }
}
