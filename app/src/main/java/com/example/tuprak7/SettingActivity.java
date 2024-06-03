package com.example.tuprak7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    Button logoutButton;
    TextView welcomeText;
    Switch toggleMode;
    UserPreference mUserPreference;
    User user;

    static final int RESULT_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        welcomeText = findViewById(R.id.welcome_text);
        logoutButton = findViewById(R.id.logout_button);
        toggleMode = findViewById(R.id.toggle_mode);

        mUserPreference = new UserPreference(this);
        user = mUserPreference.getUser();

        boolean isDarkMode = mUserPreference.getDarkMode();
        toggleMode.setChecked(isDarkMode);

        toggleMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                mUserPreference.setDarkMode(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                mUserPreference.setDarkMode(false);
            }
        });

        setLayoutValue();

        logoutButton.setOnClickListener(e -> {
            user.setLogin(true);
            mUserPreference.setUser(user);
            Intent toMain = new Intent(this, MainActivity.class);
            startActivity(toMain);
            finish();
        });
    }

    void setLayoutValue() {
        welcomeText.setText("Welcome User " + user.getUsername());
    }
}
