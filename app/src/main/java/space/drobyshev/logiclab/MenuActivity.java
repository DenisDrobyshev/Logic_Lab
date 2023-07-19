package space.drobyshev.logiclab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import space.drobyshev.logiclab.GameAsteroid.GameAsteroidActivity;
import space.drobyshev.logiclab.GameBall.GameBallActivity;
import space.drobyshev.logiclab.GameMath.MathGameActivity;
import space.drobyshev.logiclab.GameMemory.MemoryGame;
import space.drobyshev.logiclab.GamePlusMinus.PlusMinusGameActivity;

public class MenuActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuu);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();

        DB = new DBHelper(this);

        List<Boolean> gameResults = DB.getAllGameResultsByEmail(email);


            ImageView image1 = findViewById(R.id.complete_game1);
            ImageView image2 = findViewById(R.id.complete_game2);
            ImageView image3 = findViewById(R.id.complete_game3);
            ImageView image4 = findViewById(R.id.complete_game4);
            ImageView image5 = findViewById(R.id.complete_game5);

            if (gameResults.get(0) == true){
                image1.setVisibility(View.VISIBLE);
            }

            if (gameResults.get(1) == true){
                image2.setVisibility(View.VISIBLE);
            }

            if (gameResults.get(2) == true){
                image3.setVisibility(View.VISIBLE);
            }

            if (gameResults.get(3) == true){
                image4.setVisibility(View.VISIBLE);
            }

            if (gameResults.get(4) == true){
                image5.setVisibility(View.VISIBLE);
            }


       Button memory_game = findViewById(R.id.memory_game);
        memory_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMemoryGameActivity(email);
            }
        });

        Button math_game = findViewById(R.id.math_game);
        math_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMathGameActivity(email);
            }
        });

        Button plusminus_game = findViewById(R.id.plusminus_game);
        plusminus_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlusMinusGameActivity(email);
            }
        });

        Button asteroid_game = findViewById(R.id.asteroid_game);
        asteroid_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameAsteroidActivity(email);
            }
        });

        Button game_ball = findViewById(R.id.game_ball);
        game_ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameBallActivity(email);
            }
        });
    }

    public void openMemoryGameActivity(String email) {
        Intent intent = new Intent(this, MemoryGame.class);
        intent.putExtra("email","" + email);
        startActivity(intent);
    }

    public void openMathGameActivity(String email) {
        Intent intent = new Intent(this, MathGameActivity.class);
        intent.putExtra("email","" + email);
        startActivity(intent);
    }

    public void openPlusMinusGameActivity(String email) {
        Intent intent = new Intent(this, PlusMinusGameActivity.class);
        intent.putExtra("email","" + email);
        startActivity(intent);
    }

    public void openGameAsteroidActivity(String email) {
        Intent intent = new Intent(this, GameAsteroidActivity.class);
        intent.putExtra("email","" + email);
        startActivity(intent);
    }

    public void openGameBallActivity(String email) {
        Intent intent = new Intent(this, GameBallActivity.class);
        intent.putExtra("email","" + email);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email","" + email);
        startActivity(intent);
        super.onBackPressed();
    }

}