package scorekeeper.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int mButtonClicks = 1;
    ImageButton addP1;
    ImageButton subP1;
    ImageButton addP2;
    ImageButton subP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button turnBtn = findViewById(R.id.TurnBtn);
        turnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView turn = findViewById(R.id.TurnId);
                turn.setText("Turn " + ++mButtonClicks);
            }
        });

        setUpClicks();
    }

    public void setUpClicks()
    {
        addP2 = findViewById(R.id.AddP2);
        addP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points2 = findViewById(R.id.txtPointsP2);
                String currentPoints = points2.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.AddPoints(d);//GameWorker.AddPoints(d);
                points2.setText(newPoints);
            }
        });

        subP2 = findViewById(R.id.SubP2);
        subP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points2 = findViewById(R.id.txtPointsP2);
                String currentPoints = points2.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.SubtractPoints(d);//GameWorker.AddPoints(d);
                points2.setText(newPoints);
            }
        });

        addP1 = findViewById(R.id.AddP1);
        addP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points2 = findViewById(R.id.txtPointsP1);
                String currentPoints = points2.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.AddPoints(d);//GameWorker.AddPoints(d);
                points2.setText(newPoints);
            }
        });

        subP1 = findViewById(R.id.SubP1);
        subP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points2 = findViewById(R.id.txtPointsP1);
                String currentPoints = points2.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.SubtractPoints(d);//GameWorker.AddPoints(d);
                points2.setText(newPoints);
            }
        });

    }
}
