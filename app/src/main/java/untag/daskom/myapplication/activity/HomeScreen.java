package untag.daskom.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.noAuth.MainActivityGaleri;
import untag.daskom.myapplication.activity.noAuth.MainActivityPengumuman;
import untag.daskom.myapplication.activity.noAuth.MainActivityProfil;
import untag.daskom.myapplication.activity.noAuth.MainActivityStruktur;
import untag.daskom.myapplication.activity.noAuth.MainActivityUnduhan;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }
    public void LOGIN(View view) {
        Intent intent = new Intent(view.getContext(), MainActivityLogin.class);
        startActivity(intent);
    }
    public void PROFIL(View view) {
        Intent intent = new Intent(view.getContext(), MainActivityProfil.class);
        startActivity(intent);
    }
    public void STRUKTURORG(View view) {
        Intent intent = new Intent(view.getContext(), MainActivityStruktur.class);
        startActivity(intent);
    }
    public void PENGUMUMAN(View view) {
        Intent intent = new Intent(view.getContext(), MainActivityPengumuman.class);
        startActivity(intent);
    }
    public void UNDUHAN(View view) {
        Intent intent = new Intent(view.getContext(), MainActivityUnduhan.class);
        startActivity(intent);
    }
    public void GALERI(View view) {
        Intent intent = new Intent(view.getContext(), MainActivityGaleri.class);
        startActivity(intent);
    }
}
