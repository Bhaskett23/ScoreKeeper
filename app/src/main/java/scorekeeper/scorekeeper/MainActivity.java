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
    public static final String PLAYER_ONE_POINTS = "scoreKeeper.PLAYER_ONE_POINTS";
    public static final String PLAYER_TWO_POINTS = "scoreKeeper.PLAYER_TWO_POINTS";
    public static final String PLAYER_ONE_COMMAND_POINTS = "scoreKeeper.PLAYER_ONE_COMMAND_POINTS";
    public static final String PLAYER_TWO_COMMAND_POINTS = "scoreKeeper.PLAYER_TWO_COMMAND_POINTS";
    public static final String CURRENT_TURN = "scoreKeeper.CURRENT_TURN";
    //public static final String CURRENT_TURN_TEXT = "scoreKeeper.CURRENT_TURN_TEXT";

    private int mButtonClicks = 1;
    private String mPlayer1Score = "0";
    private String mPlayer2Score = "0";
    private String mPlayer1CP = "0";
    private String mPlayer2CP = "0";

    ImageButton addP1;
    ImageButton subP1;
    ImageButton addP2;
    ImageButton subP2;
    TextView commandPoints1;
    TextView commandPoints2;
    TextView points1;
    TextView points2;
    Switch swP1Warlord;
    Switch swP2Warlord;
    Switch swP1Blood;
    Switch swP2Blood;
    Switch swP1Behind;
    Switch swP2Behind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points1 = findViewById(R.id.txtPointsP1);
        points2 = findViewById(R.id.txtPointsP2);

        commandPoints1 = findViewById(R.id.txtP1CP);
        commandPoints2 = findViewById(R.id.txtP2CP);

        TextView turn = findViewById(R.id.lblTurn);

        swP1Warlord = findViewById(R.id.swP1Warlord);
        swP2Warlord = findViewById(R.id.swP2Warlord);
        swP1Blood   = findViewById(R.id.swP1Blood);
        swP2Blood   = findViewById(R.id.swP2Blood);
        swP1Behind  = findViewById(R.id.swBehindP1);
        swP2Behind  = findViewById(R.id.swBehindP2);

        Button turnBtn = findViewById(R.id.btnTurn);
        Button restart = findViewById(R.id.btnRestart);
        Button btnEndGame = findViewById(R.id.btnEndGame);

        TextView winner = findViewById(R.id.lblWinner);

        if(savedInstanceState != null)
        {
            restoreGame(savedInstanceState);
        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turn.setText("Turn 1");
                btnEndGame.setVisibility(View.INVISIBLE);

                points1.setText("0");
                points2.setText("0");

                commandPoints1.setText("0");
                commandPoints2.setText("0");

                mButtonClicks = 1;

                swP1Blood.setClickable(true);
                swP1Blood.setChecked(false);
                swP2Blood.setClickable(true);
                swP2Blood.setChecked(false);

                swP1Warlord.setChecked(false);
                swP2Warlord.setChecked(false);

                swP1Behind.setChecked(false);
                swP2Behind.setChecked(false);

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
                saveScore();
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
                saveScore();
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
                saveScore();
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
                saveScore();
            }
        });

        swP1Behind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                saveScore();
            }
        });

        swP2Behind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                saveScore();
            }
        });

        setUpClicks();
    }

    public void setUpClicks()
    {
        addP2 = findViewById(R.id.btnAddP2);
        subP2 = findViewById(R.id.btnSubP2);

        addP1 = findViewById(R.id.btnAddP1);
        subP1 = findViewById(R.id.btnSubP1);

        ImageButton addCPP1 = findViewById(R.id.btnP1CPInc);
        ImageButton subCPP1 = findViewById(R.id.btnP1CPDec);

        ImageButton addCPP2 = findViewById(R.id.btnP2CPInc);
        ImageButton subCPP2 = findViewById(R.id.btnP2CPDec);

        TextView points2 = findViewById(R.id.txtPointsP2);
        TextView points1 = findViewById(R.id.txtPointsP1);


        addCPP1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String currentCommandPointsP1 = commandPoints1.getText().toString();
                int d = Integer.parseInt(currentCommandPointsP1);
                String newPoints = GameWorker.AddPoints(d);
                commandPoints1.setText(newPoints);
                saveScore();
            }
        });

        subCPP1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String currentCommandPointsP1 = commandPoints1.getText().toString();
                int d = Integer.parseInt(currentCommandPointsP1);
                String newPoints = GameWorker.SubtractPoints(d);
                commandPoints1.setText(newPoints);
                saveScore();
            }
        });

        addCPP2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String currentCommandPointsP2 = commandPoints2.getText().toString();
                int d = Integer.parseInt(currentCommandPointsP2);
                String newPoints = GameWorker.AddPoints(d);
                commandPoints2.setText(newPoints);
                saveScore();
            }
        });

        subCPP2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String currentCommandPointsP2 = commandPoints2.getText().toString();
                int d = Integer.parseInt(currentCommandPointsP2);
                String newPoints = GameWorker.SubtractPoints(d);
                commandPoints2.setText(newPoints);
                saveScore();
            }
        });

        addP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP2 = points2.getText().toString();
                int d = Integer.parseInt(currentPointsP2);
                String newPoints = GameWorker.AddPoints(d);
                points2.setText(newPoints);
                saveScore();
            }
        });

        subP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP2 = points2.getText().toString();
                int d = Integer.parseInt(currentPointsP2);
                String newPoints = GameWorker.SubtractPoints(d);
                points2.setText(newPoints);
                saveScore();
            }
        });

        addP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP1 = points1.getText().toString();
                int d = Integer.parseInt(currentPointsP1);
                String newPoints = GameWorker.AddPoints(d);
                points1.setText(newPoints);
                saveScore();
            }
        });

        subP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP1 = points1.getText().toString();
                int d = Integer.parseInt(currentPointsP1);
                String newPoints = GameWorker.SubtractPoints(d);
                points1.setText(newPoints);
                saveScore();
            }
        });

    }

    private void saveScore()
    {
        int player1TotalScore = Integer.parseInt(points1.getText().toString());
        if(swP1Warlord.isChecked())
        {
            player1TotalScore = player1TotalScore-1;
        }

        if(swP1Blood.isChecked())
        {
            player1TotalScore = player1TotalScore-1;
        }

        if(swP1Behind.isChecked())
        {
            player1TotalScore = player1TotalScore-1;
        }


        int player2TotalScore = Integer.parseInt(points2.getText().toString());
        if(swP2Warlord.isChecked())
        {
            player2TotalScore = player2TotalScore-1;
        }

        if(swP2Blood.isChecked())
        {
            player2TotalScore = player2TotalScore-1;
        }

        if(swP2Behind.isChecked())
        {
            player2TotalScore = player2TotalScore-1;
        }

        mPlayer1Score = Integer.toString(player1TotalScore);
        mPlayer2Score = Integer.toString(player2TotalScore);
        mPlayer1CP = commandPoints1.getText().toString();
        mPlayer2CP = commandPoints2.getText().toString();
    }

    private void restoreGame(Bundle savedInstanceState)
    {
        mPlayer1Score = savedInstanceState.getString(PLAYER_ONE_POINTS);
        mPlayer2Score = savedInstanceState.getString(PLAYER_TWO_POINTS);
        mPlayer1CP = savedInstanceState.getString(PLAYER_ONE_COMMAND_POINTS);
        mPlayer2CP = savedInstanceState.getString(PLAYER_TWO_COMMAND_POINTS);
        mButtonClicks = savedInstanceState.getInt(CURRENT_TURN);

        points1.setText(mPlayer1Score);
        points2.setText(mPlayer2Score);
        commandPoints1.setText(mPlayer1CP);
        commandPoints2.setText(mPlayer2CP);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putString(PLAYER_ONE_POINTS, mPlayer1Score);
        outState.putString(PLAYER_TWO_POINTS, mPlayer2Score);
        outState.putString(PLAYER_ONE_COMMAND_POINTS, mPlayer1CP);
        outState.putString(PLAYER_TWO_COMMAND_POINTS, mPlayer2CP);
        outState.putInt(CURRENT_TURN, mButtonClicks);
    }
}
