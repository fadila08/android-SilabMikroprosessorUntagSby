package untag.daskom.myapplication.activity.mahasiswa;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.mahasiswa.MHS_GaleriAdapter;
import untag.daskom.myapplication.model.GaleriList;
import untag.daskom.myapplication.model.GaleriModel;
import untag.daskom.myapplication.my_interface.GaleriDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class MHSHomeGaleri extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private MHS_GaleriAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    String nama_mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_galeri_mhs);

        nama_mhs = getIntent().getStringExtra("nama");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_galeri_mhs);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_mhs);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_galeri_mhs);
        navigationView.setNavigationItemSelectedListener(this);

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
                Toast.makeText(MHSHomeGaleri.this,"Something went wrong....Error ,essage : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_mhs);
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
            Intent intent = new Intent(MHSHomeGaleri.this, HomeMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_mhs) {
            Intent intent = new Intent(MHSHomeGaleri.this, MHSTugasPraktikum.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_mhs) {
            Intent intent = new Intent(MHSHomeGaleri.this, MHSSuratMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_mhs) {
//            Intent intent = new Intent(HomeMahasiswa.this, .class);
//            startActivity(intent);

        } else if (id == R.id.nav_profil_mhs) {
            Intent intent = new Intent(MHSHomeGaleri.this, MHSHomeProfil.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_mhs) {
            Intent intent = new Intent(MHSHomeGaleri.this, MHSStrukturOrganisasi.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_mhs) {
            Intent intent = new Intent(MHSHomeGaleri.this, MHSPengumuman.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_mhs) {
            Intent intent = new Intent(MHSHomeGaleri.this, MHSHomeUnduhan.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_mhs) {
            Intent intent = new Intent(MHSHomeGaleri.this, MHSHomeGaleri.class);
            intent.putExtra("nama",nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_logout_mhs) {
            new LogOut(MHSHomeGaleri.this);

            Intent intent = new Intent(MHSHomeGaleri.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_mhs);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void generateGaleriList(ArrayList<GaleriModel> galeriArrayList) {
        recyclerView = findViewById(R.id.rvgaleri_mhs);
        recyclerView.setHasFixedSize(true);

        adapter = new MHS_GaleriAdapter(galeriArrayList,MHSHomeGaleri.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MHSHomeGaleri.this,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
