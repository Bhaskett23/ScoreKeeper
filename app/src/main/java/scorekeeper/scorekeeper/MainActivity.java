package scorekeeper.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mButtonClicks = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button turnBtn = findViewById(R.id.TurnBtn);
        turnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView turn = findViewById(R.id.TurnId);
                String currentTurn = turn.getText().toString();
                turn.setText("Turn " + ++mButtonClicks);
            }
        });
    }
}
