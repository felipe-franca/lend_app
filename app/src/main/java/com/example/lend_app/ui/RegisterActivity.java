package com.example.lend_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lend_app.R;

public class RegisterActivity extends AppCompatActivity {
  private final String TITLE = "Novo Cadastro";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    setTitle(TITLE);
  }
}