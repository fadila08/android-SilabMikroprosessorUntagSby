package untag.daskom.myapplication.activity.dosbim;

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

public class DOSBIMStrukturOrganisasi extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    String nama_dosbim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_struktur_dosbim);

        nama_dosbim = getIntent().getStringExtra("nama");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_struktur_dosbim);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur_dosbim);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_struktur_dosbim);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur_dosbim);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_dosbim, menu);
        return true;
    }

    //untuk layout drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home_dosbim) {
            // Handle the camera action
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, HomeDosbim.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMDataMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMDataLaboran.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMDataAslab.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMNilaiMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMAbsensiMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_profil_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMHomeProfil.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMStrukturOrganisasi.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_dosbim) {
            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, DOSBIMPengumuman.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_dosbim) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_galeri_dosbim) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_dosbim) {
            new LogOut(DOSBIMStrukturOrganisasi.this);

            Intent intent = new Intent(DOSBIMStrukturOrganisasi.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_struktur_dosbim);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
