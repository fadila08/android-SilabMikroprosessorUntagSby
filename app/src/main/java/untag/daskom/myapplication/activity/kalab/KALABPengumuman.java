package untag.daskom.myapplication.activity.kalab;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.session.LogOut;

public class KALABPengumuman extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fbTambahPengumuman;
    String nama_kalab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pengumuman_kalab);

        fbTambahPengumuman = findViewById(R.id.fab_tambah_pengumuman_kalab);

        nama_kalab = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pengumuman_kalab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman_kalab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_pengumuman_kalab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        fbTambahPengumuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KALABPengumuman.this, KALABMasukkanPengumuman.class);
                startActivity(intent);
            }
        });
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman_kalab);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //untuk layout drawer
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_kalab, menu);
        return true;
    }

    //untuk layout drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history_kalab) {
            // Handle the camera action
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, HomeKalab.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABDataLaboran.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABDataAslab.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABDataDosbim.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABDataMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABNilaiMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_absensi_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABAbsensiMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABDataSurat.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_profil_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_struktur_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABPengumuman.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABHomeUnduhan.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_kalab) {
            new LogOut(KALABPengumuman.this);

            Intent intent = new Intent(KALABPengumuman.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman_kalab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
