package untag.daskom.myapplication.activity.aslab;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.aslab.ASLAB_DataDosbimAdapter;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class ASLABDataDosbim extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ASLAB_DataDosbimAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    String nama_aslab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_dosbim_aslab);

        nama_aslab = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_data_dosbim_aslab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_dosbim_aslab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_data_dosbim_aslab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk menangkap data dari API dengan retrofit
        /** Create handle for the RetrofitInstance interface*/
        GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<DataUserList> call = service.getLaboranDataKalab("Bearer "+session);

        call.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList(response.body().getDataUserArrayList());
            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(ASLABDataDosbim.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //sampai sini
    }


    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_dosbim_aslab);
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

        if (id == R.id.nav_history_aslab) {
            // Handle the camera action
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, HomeAslab.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABDataMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABDataDosbim.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABDataLaboran.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABTugasMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABNilaiMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABAbsensiMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABDataSurat.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_profil_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABHomeProfil.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABStrukturOrganisasi.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABPengumuman.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABHomeUnduhan.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_aslab) {
            Intent intent = new Intent(ASLABDataDosbim.this, ASLABHomeGaleri.class);
            intent.putExtra("nama", nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_logout_aslab) {
            new LogOut(ASLABDataDosbim.this);

            Intent intent = new Intent(ASLABDataDosbim.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_dosbim_aslab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //untuk set data dari API yang sudah diambil tadi ke dalam recycler view data laboran(kalab)
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataUser> dataUserArrayList) {
        recyclerView = findViewById(R.id.rv_data_dosbim_aslab);
        adapter = new ASLAB_DataDosbimAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ASLABDataDosbim.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}