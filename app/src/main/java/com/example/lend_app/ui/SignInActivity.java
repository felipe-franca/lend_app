package com.example.lend_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lend_app.R;

public class SignInActivity extends AppCompatActivity {
//  private final Button LOGIN_BUTTON = findViewById(R.id.sign_in_activity_sign_in_button);
//  private final Button REGISTER_BUTTON = findViewById(R.id.sign_in_activity_register_button);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    newRegisterActivity();
    newSignInActivity();
  }

  private void newRegisterActivity() {
    Button button = findViewById(R.id.sign_in_activity_register_button);
    button.setOnClickListener(view -> {
      Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
      startActivity(intent);
    });
  }

  private void newSignInActivity() {
    Button button = findViewById(R.id.sign_in_activity_sign_in_button);
    button.setOnClickListener(view -> {
      Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
      startActivity(intent);
    });
  }
}