package scorekeeper.scorekeeper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

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

        TextView points1 = findViewById(R.id.txtPointsP1);
        TextView points2 = findViewById(R.id.txtPointsP2);
        TextView turn = findViewById(R.id.lblTurn);

        Switch swP1Warlord = findViewById(R.id.swP1Warlord);
        Switch swP2Warlord = findViewById(R.id.swP2Warlord);
        Switch swP1Blood = findViewById(R.id.swP1Blood);
        Switch swP2Blood = findViewById(R.id.swP2Blood);

        Button turnBtn = findViewById(R.id.btnTurn);
        Button restart = findViewById(R.id.btnRestart);
        Button btnEndGame = findViewById(R.id.btnEndGame);

        TextView winner = findViewById(R.id.lblWinner);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turn.setText("Turn 1");
                btnEndGame.setVisibility(View.INVISIBLE);
                points1.setText("0");
                points2.setText("0");
                mButtonClicks = 1;
                swP1Blood.setClickable(true);
                swP1Blood.setChecked(false);
                swP2Blood.setClickable(true);
                swP2Blood.setChecked(false);

                swP1Warlord.setClickable(true);
                swP1Warlord.setChecked(false);
                swP2Warlord.setClickable(true);
                swP2Warlord.setChecked(false);

                winner.setText("");
            }
        });

        turnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                turn.setText("Turn " + ++mButtonClicks);
                if (mButtonClicks >= 5)
                {
                    Button endBtn = findViewById(R.id.btnEndGame);
                    endBtn.setVisibility(View.VISIBLE);
                    endBtn.setOnClickListener(new View.OnClickListener()
                    {
                        public void onClick(View v)
                        {
                            int player1Points = Integer.parseInt(points1.getText().toString());
                            int player2Points = Integer.parseInt(points2.getText().toString());
                            String result = GameWorker.DecideTheWinner(player1Points, player2Points);

                            winner.setVisibility(View.VISIBLE);
                            winner.setText(result);
                        }
                    });
                }
            }
        });

        swP1Warlord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int player1Points = Integer.parseInt(points1.getText().toString());

                if (b)
                {
                    points1.setText(GameWorker.AddPoints(player1Points));
                }
                else
                {
                    points1.setText(GameWorker.SubtractPoints(player1Points));
                }
            }
        });

        swP2Warlord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int player2Points = Integer.parseInt(points2.getText().toString());

                if (b)
                {
                    points2.setText(GameWorker.AddPoints(player2Points));
                }
                else
                {
                    points2.setText(GameWorker.SubtractPoints(player2Points));
                }
            }
        });

        swP1Blood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int player1Points = Integer.parseInt(points1.getText().toString());

                if (b)
                {
                    swP2Blood.setClickable(false);
                    swP2Blood.setTextColor(Color.GRAY);
                    points1.setText(GameWorker.AddPoints(player1Points));
                }
                else
                {
                    swP2Blood.setClickable(true);
                    swP2Blood.setTextColor(Color.BLACK);
                    points1.setText(GameWorker.SubtractPoints(player1Points));
                }
            }
        });

        swP2Blood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int player2Points = Integer.parseInt(points2.getText().toString());

                if (b)
                {
                    swP1Blood.setClickable(false);
                    swP1Blood.setTextColor(Color.GRAY);
                    points2.setText(GameWorker.AddPoints(player2Points));
                }
                else
                {
                    swP1Blood.setClickable(true);
                    swP1Blood.setTextColor(Color.BLACK);
                    points2.setText(GameWorker.SubtractPoints(player2Points));
                }
            }
        });

        setUpClicks();
    }

    public void setUpClicks()
    {
        addP2 = findViewById(R.id.btnAddP2);
        addP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points2 = findViewById(R.id.txtPointsP2);
                String currentPoints = points2.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.AddPoints(d);
                points2.setText(newPoints);
            }
        });

        subP2 = findViewById(R.id.btnSubP2);
        subP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points2 = findViewById(R.id.txtPointsP2);
                String currentPoints = points2.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.SubtractPoints(d);
                points2.setText(newPoints);
            }
        });

        addP1 = findViewById(R.id.btnAddP1);
        addP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points1 = findViewById(R.id.txtPointsP1);
                String currentPoints = points1.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.AddPoints(d);
                points1.setText(newPoints);
            }
        });

        subP1 = findViewById(R.id.btnSubP1);
        subP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView points1 = findViewById(R.id.txtPointsP1);
                String currentPoints = points1.getText().toString();
                int d = Integer.parseInt(currentPoints);
                String newPoints = GameWorker.SubtractPoints(d);
                points1.setText(newPoints);
            }
        });

    }
}
