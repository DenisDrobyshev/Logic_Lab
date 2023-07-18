package space.drobyshev.logiclab.GameMath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Array;
import java.util.List;

import space.drobyshev.logiclab.DBHelper;
import space.drobyshev.logiclab.GameMath.MathGame;
import space.drobyshev.logiclab.MenuActivity;
import space.drobyshev.logiclab.R;

public class MathGameActivity extends AppCompatActivity {

    DBHelper DB;
    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    TextView tv_score, tv_questions, tv_timer, tv_botmessage;
    ProgressBar prog_timer;

    MathGame g = new MathGame();
    int secondsRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long l) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining) + " секунд");
            prog_timer.setProgress(30 - secondsRemaining);
        }

        @Override
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            tv_botmessage.setText("Время вышло! " + g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                }
            }, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_math);

        DB = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();

        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_answer0);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);

        tv_score = findViewById(R.id.tv_score);
        tv_botmessage = findViewById(R.id.tv_botmessage);
        tv_questions = findViewById(R.id.tv_questions);
        tv_timer = findViewById(R.id.tv_timer);

        prog_timer = findViewById(R.id.prog_timer);



        tv_timer.setText("30 секунд");
        tv_questions.setText("");
        tv_botmessage.setText("Нажми Начать");
        tv_score.setText("0");
        prog_timer.setProgress(0);
        List<Boolean> gameResults =DB.getAllGameResultsByEmail(email);

        DB.updateUserGameProgress(email,gameResults.get(0),true,gameResults.get(2),gameResults.get(3),gameResults.get(4));

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;

                start_button.setVisibility(View.INVISIBLE);
                secondsRemaining = 30;
                g = new MathGame();
                nextTurn();
                timer.start();
            }
        };

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());

                g.checkAnswer(answerSelected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextTurn();
            }
        };

        btn_start.setOnClickListener(startButtonClickListener);

        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);
    }

    private void nextTurn() {

        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerArray();

        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_botmessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
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