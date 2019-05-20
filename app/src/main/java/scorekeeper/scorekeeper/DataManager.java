package scorekeeper.scorekeeper;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<GameModel> mGames = new ArrayList<>();

    public static DataManager getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new DataManager();
            ourInstance.initializeGames();
        }
        return ourInstance;
    }

    public List<GameModel> getGames(){return mGames;}

    private void initializeGames() {
        mGames.add(initializeGame1());
        mGames.add(initializeGame2());
        mGames.add(initializeGame3());
    }

    private GameModel initializeGame1()
    {
        int victoryPointsP1 = 4;
        int commandPointsP1 = 3;
        boolean firstBloodP1 = false;
        boolean warlordP1 = true;
        boolean behindLinesP1 = false;
        String nameP1 = "Brandon";

        PlayerModel player1 = new PlayerModel(victoryPointsP1, commandPointsP1, firstBloodP1, warlordP1, behindLinesP1, nameP1);

        int victoryPointsP2 = 1;
        int commandPointsP2 = 2;
        boolean firstBloodP2 = true;
        boolean warlordP2 = false;
        boolean behindLinesP2 = false;
        String nameP2 = "Dan";

        PlayerModel player2 = new PlayerModel(victoryPointsP2, commandPointsP2, firstBloodP2, warlordP2, behindLinesP2, nameP2);

        int turn = 2;
        return new GameModel(player1, player2, turn);
    }

    private GameModel initializeGame2()
    {
        int victoryPointsP1 = 6;
        int commandPointsP1 = 2;
        boolean firstBloodP1 = true;
        boolean warlordP1 = true;
        boolean behindLinesP1 = true;
        String nameP1 = "Mike";

        PlayerModel player1 = new PlayerModel(victoryPointsP1, commandPointsP1, firstBloodP1, warlordP1, behindLinesP1, nameP1);

        int victoryPointsP2 = 1;
        int commandPointsP2 = 2;
        boolean firstBloodP2 = false;
        boolean warlordP2 = false;
        boolean behindLinesP2 = false;
        String nameP2 = "Bob";

        PlayerModel player2 = new PlayerModel(victoryPointsP2, commandPointsP2, firstBloodP2, warlordP2, behindLinesP2, nameP2);

        int turn = 1;
        return new GameModel(player1, player2, turn);
    }

    private GameModel initializeGame3()
    {
        int victoryPointsP1 = 1;
        int commandPointsP1 = 8;
        boolean firstBloodP1 = false;
        boolean warlordP1 = false;
        boolean behindLinesP1 = false;
        String nameP1 = "Jack";

        PlayerModel player1 = new PlayerModel(victoryPointsP1, commandPointsP1, firstBloodP1, warlordP1, behindLinesP1, nameP1);

        int victoryPointsP2 = 7;
        int commandPointsP2 = 3;
        boolean firstBloodP2 = false;
        boolean warlordP2 = false;
        boolean behindLinesP2 = false;
        String nameP2 = "Craig";

        PlayerModel player2 = new PlayerModel(victoryPointsP2, commandPointsP2, firstBloodP2, warlordP2, behindLinesP2, nameP2);

        int turn = 5;
        return new GameModel(player1, player2, turn);
    }
}
