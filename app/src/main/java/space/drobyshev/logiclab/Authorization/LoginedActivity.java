package space.drobyshev.logiclab.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import space.drobyshev.logiclab.DBHelper;
import space.drobyshev.logiclab.MainActivity;
import space.drobyshev.logiclab.databinding.ActivityLoginedBinding;

public class LoginedActivity extends AppCompatActivity {

    ActivityLoginedBinding binding;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = new DBHelper(this);

        binding.signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.signinEmail.getText().toString();
                String password = binding.signinPassword.getText().toString();

                if (email.equals("") || password.equals(""))
                    Toast.makeText(LoginedActivity.this, "Все поля обязательны для заполнения", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkCredentials = DB.checkEmailPassword(email, password);
                    if (checkCredentials == true) {
                        Toast.makeText(LoginedActivity.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("email","" + email);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginedActivity.this, "Похоже у вас нет аккаунта, так зарегистрируйтесь!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginedActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}