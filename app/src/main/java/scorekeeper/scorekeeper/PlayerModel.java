package scorekeeper.scorekeeper;

public class PlayerModel
{
    public PlayerModel()
    {

    }

    public PlayerModel(int victoryPoints,
                       int commandPoints,
                       boolean firstBlood,
                       boolean warlord,
                       boolean behindLines,
                       String name)
    {
        mVictoryPoints = victoryPoints;
        mCommandPoints = commandPoints;
        mFirstBlood = firstBlood;
        mWarlord = warlord;
        mBehindLines = behindLines;
        mName = name;
    }

    private int mVictoryPoints;
    private int mCommandPoints;
    private boolean mFirstBlood;
    private boolean mWarlord;
    private boolean mBehindLines;
    private String mName;

    public int getVictoryPoints(){return mVictoryPoints;}
    public int getCommandPoints(){return mCommandPoints;}
    public boolean getFirstBlood(){return mFirstBlood;}
    public boolean getWarlord(){return mWarlord;}
    public boolean getBehindLines(){return mBehindLines;}
    public String getName(){return mName;}

    @Override
    public String toString() {
        return super.toString();
    }
}
