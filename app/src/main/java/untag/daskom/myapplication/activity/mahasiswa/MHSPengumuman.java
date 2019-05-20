package untag.daskom.myapplication.activity.mahasiswa;

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
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.mahasiswa.MHS_PengumumanAdapter;
import untag.daskom.myapplication.model.PengumumanList;
import untag.daskom.myapplication.model.PengumumanModel;
import untag.daskom.myapplication.my_interface.PengumumanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class MHSPengumuman extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MHS_PengumumanAdapter adapter;
    private RecyclerView recyclerView;
    String nama_mhs;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pengumuman_mahasiswa);

        nama_mhs = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pengumuman_mhs);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman_mhs);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_pengumuman_mhs);
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
                Toast.makeText(MHSPengumuman.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman_mhs);
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
            Intent intent = new Intent(MHSPengumuman.this, HomeMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_mhs) {
            Intent intent = new Intent(MHSPengumuman.this, MHSTugasPraktikum.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_mhs) {
            Intent intent = new Intent(MHSPengumuman.this, MHSSuratMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_mhs) {
//            Intent intent = new Intent(HomeMahasiswa.this, .class);
//            startActivity(intent);

        } else if (id == R.id.nav_profil_mhs) {
            Intent intent = new Intent(MHSPengumuman.this, MHSHomeProfil.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_mhs) {
            Intent intent = new Intent(MHSPengumuman.this, MHSStrukturOrganisasi.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_mhs) {
            Intent intent = new Intent(MHSPengumuman.this, MHSPengumuman.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_mhs) {
            Intent intent = new Intent(MHSPengumuman.this, MHSHomeUnduhan.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_mhs) {
            Intent intent = new Intent(MHSPengumuman.this, MHSHomeGaleri.class);
            intent.putExtra("nama",nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_logout_mhs) {
            new LogOut(MHSPengumuman.this);

            Intent intent = new Intent(MHSPengumuman.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman_mhs);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generatePengumumanList(ArrayList<PengumumanModel> pengumumanArrayList) {

        recyclerView = findViewById(R.id.rv_pengumuman_mhs);

        adapter = new MHS_PengumumanAdapter(pengumumanArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MHSPengumuman.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }



}
