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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.laboran.InventarisAdapter;
import untag.daskom.myapplication.model.DataInventaris;
import untag.daskom.myapplication.model.DataInventarisList;
import untag.daskom.myapplication.my_interface.InventarisDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANInventaris extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private InventarisAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    TextView downloadInv;
    String nama_laboran;
    FloatingActionButton fbTambahInventaris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inventaris_laborat_laboran);

        nama_laboran = getIntent().getStringExtra("nama");

        //pendefinisian tambah data laboran
        downloadInv = (TextView) findViewById(R.id.printinventaris);
        fbTambahInventaris = findViewById(R.id.fab_tambah_inventaris_laborat_laboran);

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_inventaris_laborat_laboran);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_inventaris_laborat_laboran);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_inventaris_laborat_laboran);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk menangkap data dari API dengan retrofit
        /** Create handle for the RetrofitInstance interface*/
        InventarisDataService service = RetrofitInstance.getRetrofitInstance().create(InventarisDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<DataInventarisList> call = service.getInventaris("Bearer "+session);

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<DataInventarisList>() {
            @Override
            public void onResponse(Call<DataInventarisList> call, Response<DataInventarisList> response) {
                generateDataUserList(response.body().getDataInventarisArrayList());
                Log.d("data id", String.valueOf(response.body().getDataInventarisArrayList()));

            }

            @Override
            public void onFailure(Call<DataInventarisList> call, Throwable t) {
                Toast.makeText(LABORANInventaris.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //sampai sini

        //ke halaman tambah data laboran
        fbTambahInventaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LABORANInventaris.this, LABORANMasukkanInventarisLab.class);
                startActivity(intent);
            }
        });
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_inventaris_laborat_laboran);
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

        if (id == R.id.nav_home_laboran) {
            // Handle the camera action
            Intent intent = new Intent(LABORANInventaris.this, HomeLaboran.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datalmhs_laboran) {
            Intent intent = new Intent(LABORANInventaris.this, LABORANDataMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_laboran) {
            Intent intent = new Intent(LABORANInventaris.this, LABORANDataDosbim.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_laboran) {
            Intent intent = new Intent(LABORANInventaris.this, LABORANDataAslab.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_laboran) {
            Intent intent = new Intent(LABORANInventaris.this, LABORANNilaiMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_inventaris_laboran) {
            Intent intent = new Intent(LABORANInventaris.this, LABORANInventaris.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_profil_laboran) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_struktur_laboran) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_laboran) {
            Intent intent = new Intent(LABORANInventaris.this, LABORANPengumuman.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_laboran) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_galeri_laboran) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_laboran) {
            new LogOut(LABORANInventaris.this);

            Intent intent = new Intent(LABORANInventaris.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_inventaris_laborat_laboran);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //untuk set data dari API yang sudah diambil tadi ke dalam recycler view data laboran(kalab)
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataInventaris> dataUserArrayList) {
        recyclerView = findViewById(R.id.rv_inventaris);
        adapter = new InventarisAdapter(dataUserArrayList,LABORANInventaris.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LABORANInventaris.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
