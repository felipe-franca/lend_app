package com.example.lend_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;
import com.example.lend_app.model.ExtraIntentKeys;
import com.example.lend_app.model.User;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.retrofit.service.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements ExtraIntentKeys {
  private EditText email;
  private EditText password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    configView();
  }

  private void configView() {
    Button button = findViewById(R.id.activity_login_sign_in_button);

    button.setOnClickListener(view -> {
      if (!checkFields()) {
        Toast.makeText(this, "Necessário preencher todos os campos !", Toast.LENGTH_SHORT).show();
        return;
      }

      AuthService service = new ApiClient().getAuthService();
      Call<User> call = service.signIn(new User("", email.getText().toString(), password.getText().toString()));

      call.enqueue(new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
          User user = response.body();

          Toast.makeText(LoginActivity.this, "Bem vindo " + user.getName(), Toast.LENGTH_SHORT).show();
          next();
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
          Toast.makeText(LoginActivity.this, "Email ou senha inválidos !", Toast.LENGTH_SHORT).show();
        }
      });

    });
  }

  private Boolean checkFields() {
    email = findViewById(R.id.activity_login_email_input);
    password = findViewById(R.id.activity_login_password_input);

    return !email.getText().toString().equals("") && !password.getText().toString().equals("");
  }

  private void next() {
    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
    startActivity(intent);
  }
}