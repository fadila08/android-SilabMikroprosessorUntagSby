package untag.daskom.myapplication.activity.mahasiswa;

import android.content.Intent;
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
import untag.daskom.myapplication.adapter.mahasiswa.MHS_UnduhanAdapter;
import untag.daskom.myapplication.model.UnduhanList;
import untag.daskom.myapplication.model.UnduhanModel;
import untag.daskom.myapplication.my_interface.UnduhanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class MHSHomeUnduhan extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private MHS_UnduhanAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    String nama_mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainunduhan_dosbim);

        nama_mhs = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_unduhan_mhs);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_mhs);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_unduhan_mhs);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");


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
                Toast.makeText(MHSHomeUnduhan.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_mhs);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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
            Intent intent = new Intent(MHSHomeUnduhan.this, HomeMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_mhs) {
            Intent intent = new Intent(MHSHomeUnduhan.this, MHSTugasPraktikum.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_mhs) {
            Intent intent = new Intent(MHSHomeUnduhan.this, MHSSuratMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_mhs) {
//            Intent intent = new Intent(HomeMahasiswa.this, .class);
//            startActivity(intent);

        } else if (id == R.id.nav_profil_mhs) {
            Intent intent = new Intent(MHSHomeUnduhan.this, MHSHomeProfil.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_mhs) {
            Intent intent = new Intent(MHSHomeUnduhan.this, MHSStrukturOrganisasi.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_mhs) {
            Intent intent = new Intent(MHSHomeUnduhan.this, MHSPengumuman.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_mhs) {
            Intent intent = new Intent(MHSHomeUnduhan.this, MHSHomeUnduhan.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_mhs) {
            Intent intent = new Intent(MHSHomeUnduhan.this, MHSHomeGaleri.class);
            intent.putExtra("nama",nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_logout_mhs) {
            new LogOut(MHSHomeUnduhan.this);

            Intent intent = new Intent(MHSHomeUnduhan.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_mhs);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateUnduhanList(ArrayList<UnduhanModel> unduhanArrayList) {
        recyclerView = findViewById(R.id.rvunduhan_mhs);

        adapter = new MHS_UnduhanAdapter(unduhanArrayList,MHSHomeUnduhan.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MHSHomeUnduhan.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}
