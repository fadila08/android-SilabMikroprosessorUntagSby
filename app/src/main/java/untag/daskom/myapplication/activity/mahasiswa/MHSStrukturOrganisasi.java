package untag.daskom.myapplication.activity.mahasiswa;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.session.LogOut;

public class MHSStrukturOrganisasi extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    String nama_mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_struktur_mhs);

        nama_mhs = getIntent().getStringExtra("nama");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_struktur_mhs);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur_mhs);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_struktur_mhs);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur_mhs);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_mhs, menu);
        return true;
    }

    //untuk layout drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home_mhs) {
            Intent intent = new Intent(MHSStrukturOrganisasi.this, HomeMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_mhs) {
            Intent intent = new Intent(MHSStrukturOrganisasi.this, MHSTugasPraktikum.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_mhs) {
            Intent intent = new Intent(MHSStrukturOrganisasi.this, MHSSuratMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_mhs) {
//            Intent intent = new Intent(HomeMahasiswa.this, .class);
//            startActivity(intent);

        } else if (id == R.id.nav_profil_mhs) {
            Intent intent = new Intent(MHSStrukturOrganisasi.this, MHSHomeProfil.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_mhs) {
            Intent intent = new Intent(MHSStrukturOrganisasi.this, MHSStrukturOrganisasi.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_mhs) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_unduhan_mhs) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_galeri_mhs) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_mhs) {
            new LogOut(MHSStrukturOrganisasi.this);

            Intent intent = new Intent(MHSStrukturOrganisasi.this, MainActivityLogin.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur_mhs);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    }
