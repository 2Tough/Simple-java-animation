package objectanimation.animate.simplejavaanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private Button startGameAgain;
    private TextView DisplayScore;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score=getIntent().getExtras().get("score").toString();

        startGameAgain=(Button) findViewById(R.id.play_again_btn);
        DisplayScore=(TextView) findViewById(R.id.score_text);

        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });
        DisplayScore.setText("Score: "+ score);
    }
}
