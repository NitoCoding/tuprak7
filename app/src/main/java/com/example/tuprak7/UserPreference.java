package com.example.tuprak7;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    private static final String PREFS_NAME = "pref_name";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String IS_LOGIN = "is_login";
    private static final String REMEMBER_ME = "remember_me";
    private static final String DARK_MODE_KEY = "dark_mode_key";

    SharedPreferences preferences;

    UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    void setUser(User user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());
        editor.putBoolean(IS_LOGIN, user.isLogin());
        editor.putBoolean(REMEMBER_ME, user.isRememberMe());
        editor.apply();
    }

    User getUser() {
        User user = new User();
        user.setUsername(preferences.getString(NAME, null));
        user.setPassword(preferences.getString(PASSWORD, null));
        user.setLogin(preferences.getBoolean(IS_LOGIN, false));
        user.setRememberMe(preferences.getBoolean(REMEMBER_ME, false));
        return user;
    }

    void removeUser() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(NAME);
        editor.remove(PASSWORD);
        editor.remove(IS_LOGIN);
        editor.remove(REMEMBER_ME);
        editor.apply();
    }

    public void setDarkMode(boolean darkMode) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(DARK_MODE_KEY, darkMode);
        editor.apply();
    }

    public boolean getDarkMode() {
        return preferences.getBoolean(DARK_MODE_KEY, false);
    }
}
