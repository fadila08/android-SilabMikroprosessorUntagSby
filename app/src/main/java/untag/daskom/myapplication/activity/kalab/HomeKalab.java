package untag.daskom.myapplication.activity.kalab;

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

public class HomeKalab extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btdatalaboran, btdataaslab, btdatadosbim, btdatamhs, btnilaimhs, btabsmhs, btdatasurat;
    TextView tvNamaDrawer, tvNama;
    SessionManager sessionManager;
    String nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_kalab);

        //mendefinisikan text view dan button yang digunakan
        tvNama = (TextView)findViewById(R.id.tvnamakalab);
//        tvNamaDrawer = (TextView) findViewById(R.id.tv_namaKalab);

        btdatalaboran = (Button)findViewById(R.id.btdatalaborankalab);
        btdataaslab = (Button)findViewById(R.id.btdataaslabkalab);
        btdatadosbim = (Button)findViewById(R.id.btdatadosbimkalab);
        btdatamhs = (Button)findViewById(R.id.btdatamhskalab);
        btnilaimhs = (Button)findViewById(R.id.btnilaimhskalab);
        btabsmhs = (Button)findViewById(R.id.btabsmhskalab);
        btdatasurat = (Button)findViewById(R.id.btdatasurat);

        //menangkap data nama kalab dari login
        nama = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home_kalab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_kalab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home_kalab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk menggambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk set isi dari textview dan button yang digunakan
        tvNama.setText(nama);
//        tvNamaDrawer.setText(nama);

        btdatalaboran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatalaboran = new Intent(HomeKalab.this, KALABDataLaboran.class);
                btdatalaboran.putExtra("nama",nama);
                startActivity(btdatalaboran);
            }
        });

        btdataaslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdataaslab = new Intent(HomeKalab.this, KALABDataAslab.class);
                btdataaslab.putExtra("nama",nama);
                startActivity(btdataaslab);
            }
        });

        btdatadosbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatadosbim = new Intent(HomeKalab.this, KALABDataDosbim.class);
                btdatadosbim.putExtra("nama",nama);
                startActivity(btdatadosbim);
            }
        });

        btdatamhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatamhs = new Intent(HomeKalab.this, KALABDataMahasiswa.class);
                btdatamhs.putExtra("nama",nama);
                startActivity(btdatamhs);
            }
        });

        btnilaimhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnilaimhs = new Intent(HomeKalab.this, KALABNilaiMahasiswa.class);
                btnilaimhs.putExtra("nama",nama);
                startActivity(btnilaimhs);
            }
        });

        btabsmhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btabsmhs = new Intent(HomeKalab.this, KALABAbsensiMahasiswa.class);
                btabsmhs.putExtra("nama",nama);
                startActivity(btabsmhs);
            }
        });

        btdatasurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatasurat = new Intent(HomeKalab.this, KALABDataSurat.class);
                btdatasurat.putExtra("nama",nama);
                startActivity(btdatasurat);
            }
        });
        //sampai sini
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_kalab);
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
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_kalab) {
            Intent intent = new Intent(HomeKalab.this, HomeKalab.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABDataLaboran.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABDataAslab.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABDataDosbim.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABDataMahasiswa.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABNilaiMahasiswa.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_absensi_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABAbsensiMahasiswa.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABDataSurat.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_profil_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_struktur_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABPengumuman.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_kalab) {
            Intent intent = new Intent(HomeKalab.this, KALABHomeUnduhan.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_kalab) {
            new LogOut(HomeKalab.this);

            Intent intent = new Intent(HomeKalab.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_kalab);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
