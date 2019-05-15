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
import untag.daskom.myapplication.adapter.kalab.KALAB_UnduhanAdapter;
import untag.daskom.myapplication.model.UnduhanList;
import untag.daskom.myapplication.model.UnduhanModel;
import untag.daskom.myapplication.my_interface.UnduhanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class KALABHomeUnduhan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private KALAB_UnduhanAdapter adapter;
    private RecyclerView recyclerView;
    FloatingActionButton fbTambahUnduhan;
    String nama_kalab;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_unduhan_kalab);

        fbTambahUnduhan = findViewById(R.id.fab_tambah_unduhan_kalab);

        nama_kalab = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_unduhan_kalab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_kalab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_unduhan_kalab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        fbTambahUnduhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KALABHomeUnduhan.this, KALABMasukkanUnduhan.class);
                startActivity(intent);
            }
        });

        /** Create handle for the RetrofitInstance interface*/
        UnduhanDataService service = RetrofitInstance.getRetrofitInstance().create(UnduhanDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<UnduhanList> call = service.getUnduhan();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<UnduhanList>() {
            @Override
            public void onResponse(Call<UnduhanList> call, Response<UnduhanList> response) {
                generateUnduhanList((response.body().getUnduhanArrayList()));
            }

            @Override
            public void onFailure(Call<UnduhanList> call, Throwable t) {
                Toast.makeText(KALABHomeUnduhan.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_kalab);
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
            Intent intent = new Intent(KALABHomeUnduhan.this, HomeKalab.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABDataLaboran.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABDataAslab.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABDataDosbim.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABDataMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABNilaiMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_absensi_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABAbsensiMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABDataSurat.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_profil_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABHomeProfil.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABStrukturOrganisasi.class);
            intent.putExtra("nama", nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABPengumuman.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABHomeUnduhan.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_kalab) {
            Intent intent = new Intent(KALABHomeUnduhan.this, KALABHomeGaleri.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_logout_kalab) {
            new LogOut(KALABHomeUnduhan.this);

            Intent intent = new Intent(KALABHomeUnduhan.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_kalab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateUnduhanList(ArrayList<UnduhanModel> unduhanArrayList) {
        recyclerView = findViewById(R.id.rv_unduhan_kalab);

        adapter = new KALAB_UnduhanAdapter(unduhanArrayList,KALABHomeUnduhan.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KALABHomeUnduhan.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}
