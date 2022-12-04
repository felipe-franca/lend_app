package com.example.lend_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;
import com.example.lend_app.model.User;
import com.example.lend_app.retrofit.ApiClient;
import com.example.lend_app.retrofit.service.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
  private EditText name;
  private EditText email;
  private EditText password;
  private EditText passwordConfirm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    configView();
  }

  private void configView() {
    Button button = findViewById(R.id.register_activity_register_button);

    button.setOnClickListener(view -> {
      if (!checkFields()) {
        Toast.makeText(
          this,
          "Por favor, preencha todos os campos",
          Toast.LENGTH_SHORT).show();

        return;
      }

      if (checkPassword()) {
        Toast.makeText(
          this,
          "As senhas não conferem !",
          Toast.LENGTH_SHORT).show();

        return;
      }

      AuthService service = new ApiClient().getAuthService();
      Call<User> call = service.register(new User(
        name.getText().toString(),
        email.getText().toString(),
        password.getText().toString())
      );

      call.enqueue(new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
          User user = response.body();
          Toast.makeText(
            RegisterActivity.this,
            user.getName() + " cadastrado com sucesso !",
            Toast.LENGTH_SHORT).show();

          Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
          startActivity(intent);
          finish();
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
          Toast.makeText(
            RegisterActivity.this,
            "Erro ao cadastrar novo usuário! Tente novamente em instantes.",
            Toast.LENGTH_SHORT).show();
        }
      });
    });
  }

  private Boolean checkFields() {
    name = findViewById(R.id.activity_register_name_input);
    email = findViewById(R.id.activity_register_email_input);
    password = findViewById(R.id.activity_register_password_input);
    passwordConfirm = findViewById(R.id.activity_register_password_confirm);

    return !email.getText().toString().equals("")
      && !password.getText().toString().equals("")
      && !name.getText().toString().equals("")
      && !passwordConfirm.getText().toString().equals("");
  }

  private Boolean checkPassword() {
    return !password.getText().toString().equals(passwordConfirm.getText().toString());
  }
}