package untag.daskom.myapplication.activity.laboran;

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
import untag.daskom.myapplication.adapter.laboran.LABORAN_UnduhanAdapter;
import untag.daskom.myapplication.model.UnduhanList;
import untag.daskom.myapplication.model.UnduhanModel;
import untag.daskom.myapplication.my_interface.UnduhanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANHomeUnduhan extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private LABORAN_UnduhanAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    FloatingActionButton fbTambahUnduhan;
    String nama_laboran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_unduhan_laboran);

        fbTambahUnduhan = findViewById(R.id.fab_tambah_unduhan_laboran);

        nama_laboran = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_unduhan_laboran);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_laboran);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_unduhan_laboran);
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
                Toast.makeText(LABORANHomeUnduhan.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        fbTambahUnduhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANMasukkanUnduhan.class);
                startActivity(intent);
            }
        });
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_laboran);
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
            Intent intent = new Intent(LABORANHomeUnduhan.this, HomeLaboran.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datalmhs_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANDataMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANDataDosbim.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANDataAslab.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANNilaiMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_inventaris_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANInventaris.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_profil_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANHomeProfil.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANStrukturOrganisasi.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANPengumuman.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANHomeUnduhan.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_laboran) {
            Intent intent = new Intent(LABORANHomeUnduhan.this, LABORANHomeGaleri.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_logout_laboran) {
            new LogOut(LABORANHomeUnduhan.this);

            Intent intent = new Intent(LABORANHomeUnduhan.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_unduhan_laboran);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateUnduhanList(ArrayList<UnduhanModel> unduhanArrayList) {
        recyclerView = findViewById(R.id.rv_unduhan_laboran);

        adapter = new LABORAN_UnduhanAdapter(unduhanArrayList,LABORANHomeUnduhan.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LABORANHomeUnduhan.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}
