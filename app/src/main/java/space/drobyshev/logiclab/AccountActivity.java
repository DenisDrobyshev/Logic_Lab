package space.drobyshev.logiclab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AccountActivity extends AppCompatActivity {


    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        DB = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();

        String userName = DB.getNameByEmail(email);

        TextView name = findViewById(R.id.user_name);
        TextView emailTextView = findViewById(R.id.UserEmail);
        emailTextView.setText(email);

        name.setText(userName);


        ImageButton button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountActivity(email);
            }
        });

        ImageButton button_settings = findViewById(R.id.button_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingActivity();
            }
        });
        TextView user_name = findViewById(R.id.user_name);
    }

    public void openSettingActivity() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void openAccountActivity(String email) {
        Intent intent = new Intent(this, MainActivity.class);
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
