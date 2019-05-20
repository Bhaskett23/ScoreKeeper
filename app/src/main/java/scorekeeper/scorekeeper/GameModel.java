package scorekeeper.scorekeeper;

public class GameModel {

    public GameModel(PlayerModel player1, PlayerModel player2, int turnNum)
    {
        mPlayer1 = player1;
        mPlayer2 = player2;
        mTurnNum = turnNum;
    }

    private PlayerModel mPlayer1;
    private PlayerModel mPlayer2;
    private int mTurnNum;

    public PlayerModel getPlayer1(){return mPlayer1;}

    public PlayerModel getPlayer2(){return  mPlayer2;}

    public int getTurnNum(){return mTurnNum;}

}
