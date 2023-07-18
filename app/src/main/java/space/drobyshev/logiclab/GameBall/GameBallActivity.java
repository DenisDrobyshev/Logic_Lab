package space.drobyshev.logiclab.GameBall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

import space.drobyshev.logiclab.DBHelper;
import space.drobyshev.logiclab.MenuActivity;
import space.drobyshev.logiclab.R;

public class GameBallActivity extends AppCompatActivity {

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ball);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DB = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();

        List<Boolean> gameResults =DB.getAllGameResultsByEmail(email);

        DB.updateUserGameProgress(email,gameResults.get(0), gameResults.get(1),gameResults.get(2),gameResults.get(3),true);

    }

    public void startGame(View view){
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();
        intent.putExtra("email","" + email);
        startActivity(intent);
        super.onBackPressed();
    }
}
