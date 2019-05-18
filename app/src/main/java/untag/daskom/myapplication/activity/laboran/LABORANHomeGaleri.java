package untag.daskom.myapplication.activity.laboran;

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
import untag.daskom.myapplication.adapter.laboran.LABORAN_GaleriAdapter;
import untag.daskom.myapplication.model.GaleriList;
import untag.daskom.myapplication.model.GaleriModel;
import untag.daskom.myapplication.my_interface.GaleriDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANHomeGaleri extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private LABORAN_GaleriAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    FloatingActionButton fbTambahGaleri;
    String nama_laboran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_galeri_laboran);

        fbTambahGaleri = findViewById(R.id.fab_tambah_galeri_laboran);

        nama_laboran = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_galeri_laboran);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_laboran);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_galeri_laboran);
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
                Toast.makeText(LABORANHomeGaleri.this,"Something went wrong....Error ,essage : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        fbTambahGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LABORANHomeGaleri.this, LABORANMasukkanGaleri.class);
                startActivity(intent);
            }
        });
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_laboran);
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
            Intent intent = new Intent(LABORANHomeGaleri.this, HomeLaboran.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datalmhs_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANDataMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANDataDosbim.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANDataAslab.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANNilaiMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_inventaris_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANInventaris.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_profil_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANHomeProfil.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANStrukturOrganisasi.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANPengumuman.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANHomeUnduhan.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_laboran) {
            Intent intent = new Intent(LABORANHomeGaleri.this, LABORANHomeGaleri.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_logout_laboran) {
            new LogOut(LABORANHomeGaleri.this);

            Intent intent = new Intent(LABORANHomeGaleri.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_laboran);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void generateGaleriList(ArrayList<GaleriModel> galeriArrayList) {
        recyclerView = findViewById(R.id.rv_galeri_laboran);
        recyclerView.setHasFixedSize(true);

        adapter = new LABORAN_GaleriAdapter(galeriArrayList,LABORANHomeGaleri.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(LABORANHomeGaleri.this,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
