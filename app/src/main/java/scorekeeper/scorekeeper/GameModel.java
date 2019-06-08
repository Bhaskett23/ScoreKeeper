package scorekeeper.scorekeeper;

public class GameModel {

    public GameModel()
    {
        mPlayer1 = new PlayerModel();
        mPlayer2 = new PlayerModel();
    }

    public GameModel(int id, String name, PlayerModel player1, PlayerModel player2, int turnNum)
    {
        mID = id;
        mName = name;
        mPlayer1 = player1;
        mPlayer2 = player2;
        mTurnNum = turnNum;
    }

    private int mID;
    private String mName;
    private PlayerModel mPlayer1;
    private PlayerModel mPlayer2;
    private int mTurnNum;

    public int getID(){return mID;}

    public String getName(){ return mName;}

    public PlayerModel getPlayer1(){return mPlayer1;}

    public PlayerModel getPlayer2(){return  mPlayer2;}

    public int getTurnNum(){return mTurnNum;}

}
