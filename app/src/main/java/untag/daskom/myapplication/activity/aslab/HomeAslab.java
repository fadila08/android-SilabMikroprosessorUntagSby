package untag.daskom.myapplication.activity.aslab;

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

public class HomeAslab extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btdatamhsaslab, btdatadosbimaslab, btdatalaboranaslab, bttugasmhsaslab, btnilaimhsaslab, btabsmhsaslab, btdatasurataslab;
    TextView tvNamaDrawer, tvNama;
    SessionManager sessionManager;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_aslab);

        //mendefinisikan text view dan button yang digunakan
        tvNama = (TextView)findViewById(R.id.tvnamaaslabheader);
//        tvNamaDrawer = (TextView) findViewById(R.id.tv_namaKalab);

        btdatamhsaslab = (Button)findViewById(R.id.btdatamhsaslab);
        btdatadosbimaslab = (Button)findViewById(R.id.btdatadosbimaslab);
        btdatalaboranaslab = (Button)findViewById(R.id.btdatalaboranaslab);
        bttugasmhsaslab = (Button)findViewById(R.id.bttugasmhsaslab);
        btnilaimhsaslab = (Button)findViewById(R.id.btnilaimhsaslab);
        btabsmhsaslab = (Button)findViewById(R.id.btabsmhsaslab);
        btdatasurataslab = (Button)findViewById(R.id.btdatasurataslab);

        //menangkap data nama aslab dari login
        nama = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home_aslab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_aslab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home_aslab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk menggambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk set isi dari textview dan button yang digunakan
        tvNama.setText(nama);
//        tvNamaDrawer.setText(nama);

        btdatamhsaslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatamhsaslab = new Intent(HomeAslab.this, ASLABDataMahasiswa.class);
                btdatamhsaslab.putExtra("nama", nama);
                startActivity(btdatamhsaslab);
            }
        });

        btdatadosbimaslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatadosbimaslab = new Intent(HomeAslab.this, ASLABDataDosbim.class);
                btdatadosbimaslab.putExtra("nama", nama);
                startActivity(btdatadosbimaslab);
            }
        });

        btdatalaboranaslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatalaboranaslab = new Intent(HomeAslab.this, ASLABDataLaboran.class);
                btdatalaboranaslab.putExtra("nama", nama);
                startActivity(btdatalaboranaslab);
            }
        });

        bttugasmhsaslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bttugasmhsaslab = new Intent(HomeAslab.this, ASLABTugasMahasiswa.class);
                bttugasmhsaslab.putExtra("nama", nama);
                startActivity(bttugasmhsaslab);
            }
        });

        btnilaimhsaslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnilaimhsaslab = new Intent(HomeAslab.this, ASLABNilaiMahasiswa.class);
                btnilaimhsaslab.putExtra("nama", nama);
                startActivity(btnilaimhsaslab);
            }
        });

        btabsmhsaslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btabsmhsaslab = new Intent(HomeAslab.this, ASLABAbsensiMahasiswa.class);
                btabsmhsaslab.putExtra("nama", nama);
                startActivity(btabsmhsaslab);
            }
        });

        btdatasurataslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatasurataslab = new Intent(HomeAslab.this, ASLABDataSurat.class);
                btdatasurataslab.putExtra("nama", nama);
                startActivity(btdatasurataslab);
            }
        });
        //sampai sini
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_aslab);
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
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_aslab) {
            Intent intent = new Intent(HomeAslab.this, HomeAslab.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABDataMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABDataDosbim.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABDataLaboran.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABTugasMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABNilaiMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABAbsensiMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABDataSurat.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_profil_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABHomeProfil.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABStrukturOrganisasi.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABPengumuman.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABHomeUnduhan.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_aslab) {
            Intent intent = new Intent(HomeAslab.this, ASLABHomeGaleri.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_logout_aslab) {
            new LogOut(HomeAslab.this);

            Intent intent = new Intent(HomeAslab.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_aslab);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
