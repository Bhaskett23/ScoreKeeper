package scorekeeper.scorekeeper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {

    private List<GameModel> mGames;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView gameTextView;
        public Button loadGameButton;

        public MyViewHolder(View itemView) {
            super(itemView);

            gameTextView = itemView.findViewById(R.id.game_name);
            loadGameButton = itemView.findViewById(R.id.gameLoad_button);
        }
    }

    public GameAdapter(List<GameModel> games)
    {
        mGames = games;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View gameView = inflater.inflate(R.layout.item_game, parent, false);

        MyViewHolder holder = new MyViewHolder(gameView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GameModel game = mGames.get(position);

        TextView textView = holder.gameTextView;
        textView.setText(game.getPlayer1().getName() + " vs " + game.getPlayer2().getName());
        Button button = holder.loadGameButton;
        button.setText("Load game");
        button.setEnabled(true);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }
}
