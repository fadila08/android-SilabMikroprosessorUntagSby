package untag.daskom.myapplication.session;

import android.content.Context;
import android.content.Intent;

import untag.daskom.myapplication.activity.MainActivityLogin;

public class LogOut {

    private SessionManager sessionManager;

    public LogOut(Context context) {
        sessionManager = new SessionManager(context);
        sessionManager.logout();

        Intent intent = new Intent(context, MainActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
