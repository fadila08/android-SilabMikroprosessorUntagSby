package untag.daskom.myapplication.activity.laboran;

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
import untag.daskom.myapplication.activity.aslab.HomeAslab;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class HomeLaboran extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button btdatamhslaboran, btdatadosbimlaboran, btdataaslablaboran, btnilaimhslaboran, btinventarislaboran;
    TextView tvNamaDrawer, tvNama;
    SessionManager sessionManager;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_laboran);

        //mendefinisikan textview dan button yang digunakan
        tvNama = (TextView)findViewById(R.id.tvnamalaboranheader);
//        tvNamaDrawer = (TextView) findViewById(R.id.tv_namaKalab);

        btdatamhslaboran = (Button)findViewById(R.id.btdatamhslaboran);
        btdatadosbimlaboran = (Button)findViewById(R.id.btdatadosbimlaboran);
        btdataaslablaboran = (Button)findViewById(R.id.btdataaslablaboran);
        btnilaimhslaboran = (Button)findViewById(R.id.btnilaimhslaboran);
        btinventarislaboran = (Button)findViewById(R.id.btinventarislaboran);

        //menangkap data nama laboran dari login
        nama = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home_laboran);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_laboran);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home_laboran);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //untuk set isi dari text view dan masing masing button
        tvNama.setText(nama);
//        tvNamaDrawer.setText(nama);

        btdatamhslaboran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatamhslaboran = new Intent(HomeLaboran.this, LABORANDataMahasiswa.class);
                btdatamhslaboran.putExtra("nama",nama);
                startActivity(btdatamhslaboran);
            }
        });

        btdatadosbimlaboran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdatadosbimlaboran = new Intent(HomeLaboran.this, LABORANDataDosbim.class);
                btdatadosbimlaboran.putExtra("nama",nama);
                startActivity(btdatadosbimlaboran);
            }
        });

        btdataaslablaboran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btdataaslablaboran = new Intent(HomeLaboran.this, LABORANDataAslab.class);
                btdataaslablaboran.putExtra("nama",nama);
                startActivity(btdataaslablaboran);
            }
        });

        btnilaimhslaboran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnilaimhslaboran = new Intent(HomeLaboran.this, LABORANNilaiMahasiswa.class);
                btnilaimhslaboran.putExtra("nama",nama);
                startActivity(btnilaimhslaboran);
            }
        });

        btinventarislaboran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btinventarislaboran = new Intent(HomeLaboran.this, LABORANInventaris.class);
                btinventarislaboran.putExtra("nama",nama);
                startActivity(btinventarislaboran);
            }
        });

    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_laboran);
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
        getMenuInflater().inflate(R.menu.main_laboran, menu);
        return true;
    }

    //untuk layout drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history_laboran) {
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_laboran) {
            Intent intent = new Intent(HomeLaboran.this, HomeLaboran.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_datalmhs_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANDataMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANDataDosbim.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANDataAslab.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANNilaiMahasiswa.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_inventaris_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANInventaris.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_profil_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANHomeProfil.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANStrukturOrganisasi.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANPengumuman.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANHomeUnduhan.class);
            intent.putExtra("nama", nama);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_laboran) {
            Intent intent = new Intent(HomeLaboran.this, LABORANHomeGaleri.class);
            intent.putExtra("nama",nama);
            startActivity(intent);

        } else if (id == R.id.nav_logout_laboran) {
            new LogOut(HomeLaboran.this);

            Intent intent = new Intent(HomeLaboran.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home_laboran);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
