package untag.daskom.myapplication.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import untag.daskom.myapplication.activity.kalab.HomeKalab;
import untag.daskom.myapplication.activity.MainActivityLogin;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID = "ID";
    public static final String role = "ROLE";
    //tambahkan var disini

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String data) {
        editor.putBoolean(LOGIN, true);
        editor.putString(ID, data);
        //tambahkan var disini
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLogin()) {
            Intent intent = new Intent(context, HomeKalab.class);
            context.startActivity(intent);
        }
    }

    public HashMap<String, String> getSessionData() {
        HashMap<String, String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, null));
        //tambahkan var disini
        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, MainActivityLogin.class);
        context.startActivity(intent);
    }
}
