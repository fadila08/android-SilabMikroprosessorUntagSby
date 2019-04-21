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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class HomeDosbim extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btdatamhsdosbim, btdatalaborandosbim, btdataaslabdosbim, btnilaimhsdosbim, btabsmhsdosbim;
    TextView tvNamaDrawer, tvNama;
    SessionManager sessionManager;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_dosbim);

        //mendefinisikan text view dan button yang digunakan
        tvNama = (TextView)findViewById(R.id.tvnamadosbim);
//        tvNamaDrawer = (TextView) findViewById(R.id.tv_namaKalab);

        btdatamhsdosbim = (Button)findViewById(R.id.btdatamhsdosbim);
        btdatalaborandosbim = (Button)findViewById(R.id.btdatalaborandosbim);
        btdataaslabdosbim = (Button)findViewById(R.id.btdataaslabdosbim);
        btnilaimhsdosbim = (Button)findViewById(R.id.btnilaimhsdosbim);
        btabsmhsdosbim = (Button)findViewById(R.id.btabsmhsdosbim);

        //menangkap data nama dosbim dari login
        nama = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home_dosbim);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_dosbim);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home_dosbim);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk menggambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk set isi dari textview dan button yang digunakan
        tvNama.setText(nama);
//        tvNamaDrawer.setText(nama);

        btdatamhsdosbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatamhsdosbim = new Intent(HomeDosbim.this, DOSBIMDataMahasiswa.class);
                startActivity(btdatamhsdosbim);
            }
        });

        btdatalaborandosbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatalaborandosbim = new Intent(HomeDosbim.this, DOSBIMDataLaboran.class);
                startActivity(btdatalaborandosbim);
            }
        });

        btdataaslabdosbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdataaslabdosbim = new Intent(HomeDosbim.this, DOSBIMDataAslab.class);
                startActivity(btdataaslabdosbim);
            }
        });

        btnilaimhsdosbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnilaimhsdosbim = new Intent(HomeDosbim.this, DOSBIMNilaiMahasiswa.class);
                startActivity(btnilaimhsdosbim);
            }
        });

        btabsmhsdosbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btabsmhsdosbim = new Intent(HomeDosbim.this, DOSBIMAbsensiMahasiswa.class);
                startActivity(btabsmhsdosbim);
            }
        });
        //sampai sini
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_dosbim);
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
            Intent intent = new Intent(HomeDosbim.this, HomeDosbim.class);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_dosbim) {
            Intent intent = new Intent(HomeDosbim.this, DOSBIMDataMahasiswa.class);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_dosbim) {
            Intent intent = new Intent(HomeDosbim.this, DOSBIMDataLaboran.class);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_dosbim) {
            Intent intent = new Intent(HomeDosbim.this, DOSBIMDataAslab.class);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_dosbim) {
            Intent intent = new Intent(HomeDosbim.this, DOSBIMNilaiMahasiswa.class);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_dosbim) {
            Intent intent = new Intent(HomeDosbim.this, DOSBIMAbsensiMahasiswa.class);
            startActivity(intent);

        } else if (id == R.id.nav_profil_dosbim) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_struktur_dosbim) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_dosbim) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_unduhan_dosbim) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_galeri_dosbim) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_dosbim) {
            new LogOut(HomeDosbim.this);

            Intent intent = new Intent(HomeDosbim.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_dosbim);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
