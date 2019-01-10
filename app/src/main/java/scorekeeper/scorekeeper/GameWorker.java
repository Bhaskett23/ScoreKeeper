package scorekeeper.scorekeeper;

public class GameWorker {

    public static String AddPoints(int currentPoints)
    {
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

    public static String DecideTheWinner(int player1Score, int player2Score)
    {
        if(player1Score > player2Score)
        {
            return "Player 1 is the victor";
        }
        else if(player1Score == player2Score)
        {
            return "Game ends in a draw";
        }

        return "Player 1 is the victor";
    }
}
