package com.example.tuprak7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button registerButton, loginButton;
    EditText usernameText, passwordText;

    UserPreference mUserPreference;
    User user;

    boolean darkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);

        mUserPreference = new UserPreference(this);

        checkUser();

        darkMode = mUserPreference.getDarkMode();
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        registerButton.setOnClickListener(e -> {
            initUser();
            if (user == null) {
                Toast.makeText(this, "Isi Field terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                mUserPreference.setUser(user);
                usernameText.getText().clear();
                passwordText.getText().clear();
                Toast.makeText(this, "Berhasil Registrasi User", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(e -> {
            initUser();
            User appUser = mUserPreference.getUser();
            if (appUser == null) {
                Toast.makeText(this, "Belum ada user yang terdaftar, silakan buat user baru", Toast.LENGTH_SHORT).show();
            } else if (user != null && user.checkUser(appUser)) {
                appUser.setLogin(false);
                appUser.setRememberMe(true);
                mUserPreference.setUser(appUser);
                Intent toSetting = new Intent(this, SettingActivity.class);
                startActivity(toSetting);
                finish();
            } else {
                Toast.makeText(this, "Gagal Login, Silahkan Buat User Baru", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUser() {
        if (!usernameText.getText().toString().isEmpty() && !passwordText.getText().toString().isEmpty()) {
            user = new User(usernameText.getText().toString(), passwordText.getText().toString());
        } else {
            user = null;
        }
    }

    private void checkUser() {
        User mUser = mUserPreference.getUser();
        if (mUser != null && !mUser.isLogin() && mUser.isRememberMe()) {
            Intent toSetting = new Intent(this, SettingActivity.class);
            startActivity(toSetting);
            finish();
        }
    }
}
