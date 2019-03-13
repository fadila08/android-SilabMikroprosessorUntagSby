package untag.daskom.myapplication.activity.noAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;

import untag.daskom.myapplication.activity.HomeScreen;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;

public class MainActivityStruktur extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main_struktur);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_struktur);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_struktur);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur);
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
            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivityStruktur.this, MainActivityLogin.class);
            startActivity(intent);

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MainActivityStruktur.this, MainActivityProfil.class);
            startActivity(intent);

        } else if (id == R.id.nav_struktur) {
            Intent intent = new Intent(MainActivityStruktur.this, MainActivityStruktur.class);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman) {
            Intent intent = new Intent(MainActivityStruktur.this, MainActivityPengumuman.class);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan) {
            Intent intent = new Intent(MainActivityStruktur.this, MainActivityUnduhan.class);
            startActivity(intent);

        } else if (id == R.id.nav_galeri) {
            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


}
