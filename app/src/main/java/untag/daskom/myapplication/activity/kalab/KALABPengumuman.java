package untag.daskom.myapplication.activity.kalab;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.kalab.KALAB_PengumumanAdapter;
import untag.daskom.myapplication.model.PengumumanList;
import untag.daskom.myapplication.model.PengumumanModel;
import untag.daskom.myapplication.my_interface.PengumumanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class KALABPengumuman extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private KALAB_PengumumanAdapter adapter;
    private RecyclerView recyclerView;
    FloatingActionButton fbTambahPengumuman;
    String nama_kalab;
    SessionManager sessionManager;

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

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        /** Create handle for the RetrofitInstance interface*/
        PengumumanDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<PengumumanList> call = service.getPengumumanAuth("Bearer "+session);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<PengumumanList>() {
            @Override
            public void onResponse(Call<PengumumanList> call, Response<PengumumanList> response) {
                generatePengumumanList((response.body().getPengumumanArrayList()));
            }

            @Override
            public void onFailure(Call<PengumumanList> call, Throwable t) {
                Toast.makeText(KALABPengumuman.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


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
            Intent intent = new Intent(KALABPengumuman.this, KALABHomeProfil.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABStrukturOrganisasi.class);
            intent.putExtra("nama", nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABPengumuman.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABHomeUnduhan.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_kalab) {
            Intent intent = new Intent(KALABPengumuman.this, KALABHomeGaleri.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_logout_kalab) {
            new LogOut(KALABPengumuman.this);

            Intent intent = new Intent(KALABPengumuman.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman_kalab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generatePengumumanList(ArrayList<PengumumanModel> pengumumanArrayList) {

        recyclerView = findViewById(R.id.rv_pengumuman_kalab);

        adapter = new KALAB_PengumumanAdapter(pengumumanArrayList,KALABPengumuman.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KALABPengumuman.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }





}
