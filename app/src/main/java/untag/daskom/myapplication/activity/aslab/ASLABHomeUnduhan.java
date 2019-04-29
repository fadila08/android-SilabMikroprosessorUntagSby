package untag.daskom.myapplication.activity.aslab;

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

import untag.daskom.myapplication.ASLABMasukkanGaleri;
import untag.daskom.myapplication.ASLABMasukkanUnduhan;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.session.LogOut;

public class ASLABHomeUnduhan extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fbTambahUnduhan;
    String nama_aslab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_unduhan_aslab);

        fbTambahUnduhan = findViewById(R.id.fab_tambah_unduhan_aslab);

        nama_aslab = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_unduhan_aslab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_aslab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_unduhan_aslab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        fbTambahUnduhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABMasukkanUnduhan.class);
                startActivity(intent);
            }
        });
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_aslab);
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
        getMenuInflater().inflate(R.menu.main_aslab, menu);
        return true;
    }

    //untuk layout drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history_aslab) {
            // Handle the camera action
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, HomeAslab.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABDataMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABDataDosbim.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABDataLaboran.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABTugasMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABNilaiMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABAbsensiMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABDataSurat.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_profil_aslab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_struktur_aslab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABPengumuman.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_aslab) {
            Intent intent = new Intent(ASLABHomeUnduhan.this, ASLABHomeUnduhan.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_aslab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_aslab) {
            new LogOut(ASLABHomeUnduhan.this);

            Intent intent = new Intent(ASLABHomeUnduhan.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_aslab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
