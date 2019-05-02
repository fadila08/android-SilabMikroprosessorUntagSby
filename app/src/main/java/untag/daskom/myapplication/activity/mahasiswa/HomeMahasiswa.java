package untag.daskom.myapplication.activity.mahasiswa;

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
import android.widget.TextView;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class HomeMahasiswa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button bttugaspraktikummhs, btdatasuratmhs, btnilaimhsmhs;
    TextView tvNamaDrawer, tvNama;
    SessionManager sessionManager;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_mhs);

        //mendefinisikan textview dan button yang digunakan
        tvNama = (TextView)findViewById(R.id.tvnamamahasiswaheader);
//        tvNamaDrawer = (TextView) findViewById(R.id.tv_namaKalab);

        bttugaspraktikummhs = (Button)findViewById(R.id.bttugaspraktikummhs);
        btdatasuratmhs = (Button)findViewById(R.id.btdatasuratmhs);
        btnilaimhsmhs = (Button)findViewById(R.id.btnilaimhsmhs);

        //menangkap data nama mhs dari login
        nama = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home_mhs);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_mhs);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home_mhs);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //untuk set isi dari text view dan masing masing button
        tvNama.setText(nama);
//        tvNamaDrawer.setText(nama);

        bttugaspraktikummhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bttugaspraktikum = new Intent(HomeMahasiswa.this, MHSTugasPraktikum.class);
                bttugaspraktikum.putExtra("nama", nama);
                startActivity(bttugaspraktikum);
            }
        });

        btdatasuratmhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatasuratmhs = new Intent(HomeMahasiswa.this, MHSSuratMahasiswa.class);
                btdatasuratmhs.putExtra("nama", nama);
                startActivity(btdatasuratmhs);
            }
        });

        btnilaimhsmhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent btnilaimhsmhs = new Intent(HomeMahasiswa.this, .class);
//                startActivity(btnilaimhsmhs);
            }
        });

    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_mhs);
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
            Intent intent = new Intent(HomeMahasiswa.this, HomeMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_mhs) {
            Intent intent = new Intent(HomeMahasiswa.this, MHSTugasPraktikum.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_mhs) {
            Intent intent = new Intent(HomeMahasiswa.this, MHSSuratMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_mhs) {
//            Intent intent = new Intent(HomeMahasiswa.this, .class);
//            startActivity(intent);

        } else if (id == R.id.nav_profil_mhs) {
            Intent intent = new Intent(HomeMahasiswa.this, MHSHomeProfil.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_mhs) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

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
            new LogOut(HomeMahasiswa.this);

            Intent intent = new Intent(HomeMahasiswa.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_mhs);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
