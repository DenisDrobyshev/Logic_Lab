package space.drobyshev.logiclab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();


        ImageButton person_account = findViewById(R.id.person_account);
        person_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountActivity();
            }
        });

        Button main_setting = findViewById(R.id.main_setting);
        main_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingActivity();
            }
        });

        Button main_play = findViewById(R.id.main_play);
        main_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity(email);
            }
        });
    }

    public void openAccountActivity() {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void openSettingActivity() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void openMenuActivity(String email) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("email","" + email);
        startActivity(intent);
    }
}