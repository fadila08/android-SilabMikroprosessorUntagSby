package untag.daskom.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import untag.daskom.myapplication.activity.Galeri;
import untag.daskom.myapplication.activity.Pengumuman;
import untag.daskom.myapplication.activity.Profile;
import untag.daskom.myapplication.activity.Unduhan;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }
    public void LOGIN(View view) {
        Intent intent = new Intent(view.getContext(), Login.class);
        startActivity(intent);
    }
    public void PROFIL(View view) {
        Intent intent = new Intent(view.getContext(), Profile.class);
        startActivity(intent);
    }
    public void STRUKTURORG(View view) {
        Intent intent = new Intent(view.getContext(), StrukturOrganisasi.class);
        startActivity(intent);
    }
    public void PENGUMUMAN(View view) {
        Intent intent = new Intent(view.getContext(), Pengumuman.class);
        startActivity(intent);
    }
    public void UNDUHAN(View view) {
        Intent intent = new Intent(view.getContext(), Unduhan.class);
        startActivity(intent);
    }
    public void GALERI(View view) {
        Intent intent = new Intent(view.getContext(), Galeri.class);
        startActivity(intent);
    }
}
