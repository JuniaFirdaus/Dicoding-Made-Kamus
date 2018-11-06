package juniafirdaus.com.dicodingkamus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button button;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.button);

        sp = getSharedPreferences("login", MODE_PRIVATE);


        if (sp.contains("username") && sp.contains("password")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });
    }

    void loginCheck() {
        if (username.getText().toString().equals("narenda") && password.getText().toString().equals("dicoding")) {
            SharedPreferences.Editor e = sp.edit();
            e.putString("username", "narenda");
            e.putString("password", "dicoding");
            e.apply();

            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Incorrect Login", Toast.LENGTH_SHORT).show();
        }
    }
}