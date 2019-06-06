package scorekeeper.scorekeeper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import scorekeeper.scorekeeper.ScoreKeeperDatabaseContract.GamesEntry;
import scorekeeper.scorekeeper.ScoreKeeperDatabaseContract.PlayerEntry;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<GameModel> mGames = new ArrayList<>();
    private static List<PlayerModel> mPlayers = new ArrayList<>();

//TODO Later on look into loading off both tables at one time
    public static DataManager getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new DataManager();
           // ourInstance.initializeGames();
        }
        return ourInstance;
    }

    public static void loadFromDatabase(ScoreKeeperOpenHelper dbHelper)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        final String[] playerColumns = {PlayerEntry._ID,
                PlayerEntry.COLUMN_PLAYER_NAME,
                PlayerEntry.COLUMN_PLAYER_VICTORY_POINTS,
                PlayerEntry.COLUMN_PLAYER_COMMAND_POINTS,
                PlayerEntry.COLUMN_PLAYER_BEHIND_LINES,
                PlayerEntry.COLUMN_PLAYER_FIRST_BLOOD,
                PlayerEntry.COLUMN_PLAYER_WARLORD};
        final Cursor playerCursor = db.query(PlayerEntry.TABLE_NAME, playerColumns, null, null, null, null, null);
        loadPlayerFromDatabase(playerCursor);

        final String[] gamesColumns = {GamesEntry.COLUMN_GAME_NAME, GamesEntry.COLUMN_PLAYER1_ID, GamesEntry.COLUMN_PLAYER2_ID, GamesEntry.COLUMN_TURN};
        final Cursor gameCursor = db.query(GamesEntry.TABLE_NAME, gamesColumns, null, null, null, null, null);
        loadGamesFromDatabase(gameCursor);
    }

    private static void loadPlayerFromDatabase(Cursor playerCursor) {
        int playerIdPos = playerCursor.getColumnIndex(PlayerEntry._ID);
        int playerNamePos = playerCursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_NAME);
        int playerVictoryPos = playerCursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_VICTORY_POINTS);
        int playerCommandPos = playerCursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_COMMAND_POINTS);
        int playerBehindLinesPos = playerCursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_BEHIND_LINES);
        int playerFirstBloodPos = playerCursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_FIRST_BLOOD);
        int playerWarlordPos = playerCursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_WARLORD);

        while (playerCursor.moveToNext())
        {
            int playerID = playerCursor.getInt(playerIdPos);
            String playerName = playerCursor.getString(playerNamePos);
            int playerVictoryPoints = playerCursor.getInt(playerVictoryPos);
            int playerCommandPoints = playerCursor.getInt(playerCommandPos);
            int playerBehindLines = playerCursor.getInt(playerBehindLinesPos);
            boolean behind = playerBehindLines == 1;
            int playerFirstBlood = playerCursor.getInt(playerFirstBloodPos);
            boolean firstBlood = playerFirstBlood == 1;
            int playerWarlord = playerCursor.getInt(playerWarlordPos);
            boolean warlord = playerWarlord == 1;

            PlayerModel player = new PlayerModel(playerID, playerName, playerVictoryPoints, playerCommandPoints, firstBlood, warlord, behind);

            mPlayers.add(player);
        }
        playerCursor.close();
    }

    private static void loadGamesFromDatabase(Cursor gameCursor) {
        int gameIdPos = gameCursor.getColumnIndex(GamesEntry._ID);
        int gameNamePos = gameCursor.getColumnIndex(GamesEntry.COLUMN_GAME_NAME);
        int gameTurnPos = gameCursor.getColumnIndex(GamesEntry.COLUMN_TURN);
        int gamePlayer1Pos = gameCursor.getColumnIndex(GamesEntry.COLUMN_PLAYER1_ID);
        int gamePlayer2Pos = gameCursor.getColumnIndex(GamesEntry.COLUMN_PLAYER2_ID);

        DataManager dm = getInstance();
        dm.mGames.clear();

        while(gameCursor.moveToNext())
        {
            int gameID = gameCursor.getInt(gameIdPos);
            String gameName = gameCursor.getString(gameNamePos);
            int gameTurn = gameCursor.getInt(gameTurnPos);
            int player1ID = gameCursor.getInt(gamePlayer1Pos);
            int player2ID = gameCursor.getInt(gamePlayer2Pos);

            //TODO Find a better way of finding the correct players will not work once we implement having players go across multiple games
            PlayerModel player1 = mPlayers.get(player1ID - 1);
            PlayerModel player2 = mPlayers.get(player2ID - 1);

            GameModel game = new GameModel(gameID, gameName, player1, player2, gameTurn);

            dm.mGames.add(game);
        }
        gameCursor.close();
    }

    public List<GameModel> getGames(){return mGames;}

    private void initializeGames() {
        mGames.add(initializeGame1());
        mGames.add(initializeGame2());
        mGames.add(initializeGame3());
    }

    private GameModel initializeGame1()
    {
        int id = 1;
        int victoryPointsP1 = 4;
        int commandPointsP1 = 3;
        boolean firstBloodP1 = false;
        boolean warlordP1 = true;
        boolean behindLinesP1 = false;
        String nameP1 = "Brandon";

        PlayerModel player1 = new PlayerModel(id, nameP1, victoryPointsP1, commandPointsP1, firstBloodP1, warlordP1, behindLinesP1);

        int id2 = 2;
        int victoryPointsP2 = 1;
        int commandPointsP2 = 2;
        boolean firstBloodP2 = true;
        boolean warlordP2 = false;
        boolean behindLinesP2 = false;
        String nameP2 = "Dan";

        PlayerModel player2 = new PlayerModel(id2, nameP2, victoryPointsP2, commandPointsP2, firstBloodP2, warlordP2, behindLinesP2);

        int turn = 2;
        int gameID = 1;
        String gameName = "Titans";
        return new GameModel(gameID, gameName,player1, player2, turn);
    }

    private GameModel initializeGame2()
    {
        int id = 3;
        int victoryPointsP1 = 6;
        int commandPointsP1 = 2;
        boolean firstBloodP1 = true;
        boolean warlordP1 = true;
        boolean behindLinesP1 = true;
        String nameP1 = "Mike";

        PlayerModel player1 = new PlayerModel(id, nameP1, victoryPointsP1, commandPointsP1, firstBloodP1, warlordP1, behindLinesP1);

        int id2 = 4;
        int victoryPointsP2 = 1;
        int commandPointsP2 = 2;
        boolean firstBloodP2 = false;
        boolean warlordP2 = false;
        boolean behindLinesP2 = false;
        String nameP2 = "Bob";

        PlayerModel player2 = new PlayerModel(id2, nameP2, victoryPointsP2 , commandPointsP2, firstBloodP2, warlordP2, behindLinesP2);

        int turn = 1;
        int gameID = 2;
        String gameName = "Marines";
        return new GameModel(gameID, gameName, player1, player2, turn);
    }

    private GameModel initializeGame3()
    {
        int id = 5;
        int victoryPointsP1 = 1;
        int commandPointsP1 = 8;
        boolean firstBloodP1 = false;
        boolean warlordP1 = false;
        boolean behindLinesP1 = false;
        String nameP1 = "Jack";

        PlayerModel player1 = new PlayerModel(id, nameP1, victoryPointsP1, commandPointsP1, firstBloodP1, warlordP1, behindLinesP1);

        int id2 = 6;
        int victoryPointsP2 = 7;
        int commandPointsP2 = 3;
        boolean firstBloodP2 = false;
        boolean warlordP2 = false;
        boolean behindLinesP2 = false;
        String nameP2 = "Craig";

        PlayerModel player2 = new PlayerModel(id2, nameP2, victoryPointsP2, commandPointsP2, firstBloodP2, warlordP2, behindLinesP2);

        int turn = 5;
        int gameID = 2;
        String gameName = "Marines";
        return new GameModel(gameID, gameName, player1, player2, turn);
    }
}
