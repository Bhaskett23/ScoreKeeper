package scorekeeper.scorekeeper;

public class GameWorker {

    public static String AddPoints(int currentPoints)
    {
        if (currentPoints == 0)
        {
            return "0";
        }
        int newPointValue = currentPoints + 1;
        return Integer.toString(newPointValue);
    }

    public static String SubtractPoints(int currentPoints)
    {
        if (currentPoints == 0)
        {
            return "0";
        }
        int newPointValue = currentPoints - 1;
        return Integer.toString(newPointValue);
    }
}
