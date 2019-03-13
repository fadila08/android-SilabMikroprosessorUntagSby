package untag.daskom.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import untag.daskom.myapplication.HomeKalab;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.noAuth.MainActivityGaleri;
import untag.daskom.myapplication.activity.noAuth.MainActivityPengumuman;
import untag.daskom.myapplication.activity.noAuth.MainActivityProfil;
import untag.daskom.myapplication.activity.noAuth.MainActivityStruktur;
import untag.daskom.myapplication.activity.noAuth.MainActivityUnduhan;

public class MainActivityLogin extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    Button btlogin;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        btlogin = (Button)findViewById(R.id.btlogin);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_login);
        navigationView.setNavigationItemSelectedListener(this);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btlogin = new Intent(MainActivityLogin.this, HomeKalab.class);
                startActivity(btlogin);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_without_login, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(MainActivityLogin.this, HomeScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityLogin.class);
            startActivity(intent);

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityProfil.class);
            startActivity(intent);

        } else if (id == R.id.nav_struktur) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityStruktur.class);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityPengumuman.class);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityUnduhan.class);
            startActivity(intent);

        } else if (id == R.id.nav_galeri) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityGaleri.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}