package untag.daskom.myapplication.activity.aslab;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import untag.daskom.myapplication.adapter.aslab.ASLAB_GaleriAdapter;
import untag.daskom.myapplication.model.GaleriList;
import untag.daskom.myapplication.model.GaleriModel;
import untag.daskom.myapplication.my_interface.GaleriDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class ASLABHomeGaleri extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private ASLAB_GaleriAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    FloatingActionButton fbTambahGaleri;
    String nama_aslab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_galeri_aslab);

        fbTambahGaleri = findViewById(R.id.fab_tambah_galeri_aslab);

        nama_aslab = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_galeri_aslab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_aslab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_galeri_aslab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        /** Create handle for the RetrofitInstance interface*/
        GaleriDataService service = RetrofitInstance.getRetrofitInstance().create(GaleriDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<GaleriList> call = service.getGaleri();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url()+"");

        call.enqueue(new Callback<GaleriList>() {
            @Override
            public void onResponse(Call<GaleriList> call, Response<GaleriList> response) {
                generateGaleriList((response.body().getGaleriArrayList()));
            }

            @Override
            public void onFailure(Call<GaleriList> call, Throwable t) {
                Toast.makeText(ASLABHomeGaleri.this,"Something went wrong....Error ,essage : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        fbTambahGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASLABHomeGaleri.this, ASLABMasukkanGaleri.class);
                startActivity(intent);
            }
        });
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_aslab);
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
            // Handle the camera action
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, HomeAslab.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABDataMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABDataDosbim.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABDataLaboran.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABTugasMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABNilaiMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABAbsensiMahasiswa.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABDataSurat.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_profil_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABHomeProfil.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABStrukturOrganisasi.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABPengumuman.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABHomeUnduhan.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_aslab) {
            Intent intent = new Intent(ASLABHomeGaleri.this, ASLABHomeGaleri.class);
            intent.putExtra("nama",nama_aslab);
            startActivity(intent);

        } else if (id == R.id.nav_logout_aslab) {
            new LogOut(ASLABHomeGaleri.this);

            Intent intent = new Intent(ASLABHomeGaleri.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_aslab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void generateGaleriList(ArrayList<GaleriModel> galeriArrayList) {
        recyclerView = findViewById(R.id.rv_galeri_aslab);
        recyclerView.setHasFixedSize(true);

        adapter = new ASLAB_GaleriAdapter(galeriArrayList,ASLABHomeGaleri.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ASLABHomeGaleri.this,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}
