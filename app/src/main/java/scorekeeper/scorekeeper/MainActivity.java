package scorekeeper.scorekeeper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import static scorekeeper.scorekeeper.ScoreKeeperDatabaseContract.*;

public class MainActivity extends AppCompatActivity {
    private static final String PLAYER_ONE_POINTS = "scoreKeeper.PLAYER_ONE_POINTS";
    private static final String PLAYER_TWO_POINTS = "scoreKeeper.PLAYER_TWO_POINTS";
    private static final String PLAYER_ONE_COMMAND_POINTS = "scoreKeeper.PLAYER_ONE_COMMAND_POINTS";
    private static final String PLAYER_TWO_COMMAND_POINTS = "scoreKeeper.PLAYER_TWO_COMMAND_POINTS";
    private static final String CURRENT_TURN = "scoreKeeper.CURRENT_TURN";
    private static final String SHOW_END_GAME = "scoreKeeper.SHOW_END_GAME";
    private static final String THE_WINNER_IS = "scoreKeeper.THE_WINNER_IS";

    private int mButtonClicks = 1;
    private String mPlayer1Score = "0";
    private String mPlayer2Score = "0";
    private String mPlayer1CP = "0";
    private String mPlayer2CP = "0";
    private String mResult = "";

    private GameModel game;

    ImageButton addP1;
    ImageButton subP1;
    ImageButton addP2;
    ImageButton subP2;
    Button btnEndGame;
    TextView commandPoints1;
    TextView commandPoints2;
    TextView points1;
    TextView points2;
    TextView turn;
    TextView winner;
    Switch swP1Warlord;
    Switch swP2Warlord;
    Switch swP1Blood;
    Switch swP2Blood;
    Switch swP1Behind;
    Switch swP2Behind;

    ScoreKeeperOpenHelper mDBOpenHelper;
    private Cursor mGameCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBOpenHelper = new ScoreKeeperOpenHelper(this);

        loadGameData(-1);

        points1 = findViewById(R.id.txtPointsP1);
        points2 = findViewById(R.id.txtPointsP2);

        commandPoints1 = findViewById(R.id.txtP1CP);
        commandPoints2 = findViewById(R.id.txtP2CP);

        turn = findViewById(R.id.lblTurn);

        swP1Warlord = findViewById(R.id.swP1Warlord);
        swP2Warlord = findViewById(R.id.swP2Warlord);
        swP1Blood   = findViewById(R.id.swP1Blood);
        swP2Blood   = findViewById(R.id.swP2Blood);
        swP1Behind  = findViewById(R.id.swBehindP1);
        swP2Behind  = findViewById(R.id.swBehindP2);
        btnEndGame = findViewById(R.id.btnEndGame);

        Button turnBtn = findViewById(R.id.btnTurn);
        Button restart = findViewById(R.id.btnRestart);

        winner = findViewById(R.id.lblWinner);

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
                    btnEndGame.setVisibility(View.VISIBLE);
                    SetupEndGameButton();
                }
            }
        });

        swP1Warlord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int player1Points = Integer.parseInt(points1.getText().toString());

                if (b)
                {
                    points1.setText(GameWorker1.AddPoints(player1Points));
                }
                else
                {
                    points1.setText(GameWorker1.SubtractPoints(player1Points));
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
                    points2.setText(GameWorker1.AddPoints(player2Points));
                }
                else
                {
                    points2.setText(GameWorker1.SubtractPoints(player2Points));
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
                    points1.setText(GameWorker1.AddPoints(player1Points));
                }
                else
                {
                    swP2Blood.setClickable(true);
                    swP2Blood.setTextColor(Color.BLACK);
                    points1.setText(GameWorker1.SubtractPoints(player1Points));
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
                    points2.setText(GameWorker1.AddPoints(player2Points));
                }
                else
                {
                    swP1Blood.setClickable(true);
                    swP1Blood.setTextColor(Color.BLACK);
                    points2.setText(GameWorker1.SubtractPoints(player2Points));
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
                    points1.setText(GameWorker1.AddPoints(player1Points));
                }
                else
                {
                    points1.setText(GameWorker1.SubtractPoints(player1Points));
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
                    points2.setText(GameWorker1.AddPoints(player2Points));
                }
                else
                {
                    points2.setText(GameWorker1.SubtractPoints(player2Points));
                }
                saveScore();
            }
        });

        setUpClicks();
    }

    private void loadGameData(int gameID) {

        SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();

        if (gameID == -1)
        {
            game = new GameModel();
        }

        List<GameModel> games = DataManager.getInstance().getGames();
        game = games.get(gameID - 1);

 //       String selection = GamesEntry.COLUMN_GAME_NAME + " = " + gameID;
//
 //       String[] gameColumns = {
 //               GamesEntry.COLUMN_GAME_NAME,
 //               GamesEntry.COLUMN_TURN,
 //               GamesEntry.COLUMN_PLAYER1_ID,
 //               GamesEntry.COLUMN_PLAYER2_ID
 //       };
//
 //       mGameCursor = db.query(GamesEntry.TABLE_NAME, gameColumns, selection, null, null, null, null);
 //       int gameNamePos = mGameCursor.getColumnIndex(GamesEntry.COLUMN_GAME_NAME);
 //       int gameTurnPos = mGameCursor.getColumnIndex(GamesEntry.COLUMN_TURN);
 //       int gamePlayer1IDPos = mGameCursor.getColumnIndex(GamesEntry.COLUMN_PLAYER1_ID);
 //       int gamePlayer2IDPos = mGameCursor.getColumnIndex(GamesEntry.COLUMN_PLAYER2_ID);
//
 //       while(mGameCursor.moveToNext())
 //       {
 //           String gameName = mGameCursor.getString(gameNamePos);
 //           int gameTurn = mGameCursor.getInt(gameTurnPos);
 //           int player1ID = mGameCursor.getInt(gamePlayer1IDPos);
 //           int player2ID = mGameCursor.getInt(gamePlayer2IDPos);
 //           game = new GameModel(gameID, gameName, null, null, gameTurn);
 //       }
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
                String newPoints = GameWorker1.AddPoints(d);
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
                String newPoints = GameWorker1.SubtractPoints(d);
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
                String newPoints = GameWorker1.AddPoints(d);
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
                String newPoints = GameWorker1.SubtractPoints(d);
                commandPoints2.setText(newPoints);
                saveScore();
            }
        });

        addP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP2 = points2.getText().toString();
                int d = Integer.parseInt(currentPointsP2);
                String newPoints = GameWorker1.AddPoints(d);
                points2.setText(newPoints);
                saveScore();
            }
        });

        subP2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP2 = points2.getText().toString();
                int d = Integer.parseInt(currentPointsP2);
                String newPoints = GameWorker1.SubtractPoints(d);
                points2.setText(newPoints);
                saveScore();
            }
        });

        addP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP1 = points1.getText().toString();
                int d = Integer.parseInt(currentPointsP1);
                String newPoints = GameWorker1.AddPoints(d);
                points1.setText(newPoints);
                saveScore();
            }
        });

        subP1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentPointsP1 = points1.getText().toString();
                int d = Integer.parseInt(currentPointsP1);
                String newPoints = GameWorker1.SubtractPoints(d);
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
        int visibleEndGame = savedInstanceState.getInt(SHOW_END_GAME);
        mResult = savedInstanceState.getString(THE_WINNER_IS);

        points1.setText(mPlayer1Score);
        points2.setText(mPlayer2Score);
        commandPoints1.setText(mPlayer1CP);
        commandPoints2.setText(mPlayer2CP);
        turn.setText("Turn " + mButtonClicks);
        btnEndGame.setVisibility(visibleEndGame);

        if(mResult != "")
        {
            winner.setVisibility(View.VISIBLE);
            winner.setText(mResult);
        }
        SetupEndGameButton();
    }

    private void SetupEndGameButton()
    {
        btnEndGame.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int player1Points = Integer.parseInt(points1.getText().toString());
                int player2Points = Integer.parseInt(points2.getText().toString());
                mResult = GameWorker1.DecideTheWinner(player1Points, player2Points);

                winner.setVisibility(View.VISIBLE);
                winner.setText(mResult);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mDBOpenHelper.close();
        super.onDestroy();
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
        if(mButtonClicks >= 5)
        {
            outState.putInt(SHOW_END_GAME, View.VISIBLE);
        }
        else
        {
            outState.putInt(SHOW_END_GAME, View.INVISIBLE);
        }
        outState.putString(THE_WINNER_IS, mResult);
    }
}
