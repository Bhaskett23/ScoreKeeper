package scorekeeper.scorekeeper;

public class PlayerModel
{
    private int mPoints;
    private int mCommandPoints;
    private boolean mFirstBlood;
    private boolean mWarlord;
    private boolean mBehindLines;

    public int getPoints(){return mPoints;}
    public int getCommandPoints(){return mCommandPoints;}
    public boolean getFirstBlood(){return mFirstBlood;}
    public boolean getWarlord(){return mWarlord;}
    public boolean getBehindLines(){return mBehindLines;}

    @Override
    public String toString() {
        return super.toString();
    }
}
