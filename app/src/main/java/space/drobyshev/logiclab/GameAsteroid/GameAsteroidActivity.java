package space.drobyshev.logiclab.GameAsteroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import space.drobyshev.logiclab.DBHelper;
import space.drobyshev.logiclab.MenuActivity;
import space.drobyshev.logiclab.R;

public class GameAsteroidActivity extends AppCompatActivity implements View.OnTouchListener {
    public static boolean isLeftPressed = false; // нажата левая кнопка
    public static boolean isRightPressed = false; // нажата правая кнопка

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_asteroid);
        GameView gameView = new GameView(this); // создаём gameView
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView
        Button leftButton = (Button) findViewById(R.id.leftButton); // находим кнопки
        Button rightButton = (Button) findViewById(R.id.rightButton);
        leftButton.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)
        rightButton.setOnTouchListener(this);

        DB = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();

        List<Boolean> gameResults =DB.getAllGameResultsByEmail(email);

        DB.updateUserGameProgress(email,gameResults.get(0), gameResults.get(1),gameResults.get(2),true,gameResults.get(4));

    }

    @SuppressLint("NonConstantResourceId")
    public boolean onTouch(View button, MotionEvent motion) {
        if (button.getId() == R.id.leftButton) { // определяем какая кнопка
            switch (motion.getAction()) { // определяем нажата или отпущена
                case MotionEvent.ACTION_DOWN:
                    isLeftPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isLeftPressed = false;
                    break;
            }
        } else {
            switch (motion.getAction()) { // определяем нажата или отпущена
                case MotionEvent.ACTION_DOWN:
                    isRightPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isRightPressed = false;
                    break;
            }
        }
        return true;
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