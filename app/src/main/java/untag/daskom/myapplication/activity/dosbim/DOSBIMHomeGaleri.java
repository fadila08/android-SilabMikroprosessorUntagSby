package untag.daskom.myapplication.activity.dosbim;

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
import untag.daskom.myapplication.adapter.GaleriAdapter;
import untag.daskom.myapplication.adapter.dosbim.DOSBIM_GaleriAdapter;
import untag.daskom.myapplication.model.GaleriList;
import untag.daskom.myapplication.model.GaleriModel;
import untag.daskom.myapplication.my_interface.GaleriDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class DOSBIMHomeGaleri extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private DOSBIM_GaleriAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    String nama_dosbim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_galeri_dosbim);

        nama_dosbim = getIntent().getStringExtra("nama");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_galeri_dosbim);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_dosbim);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_galeri_dosbim);
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
                Toast.makeText(DOSBIMHomeGaleri.this,"Something went wrong....Error ,essage : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_dosbim);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_dosbim, menu);
        return true;
    }

    //untuk layout drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home_dosbim) {
            // Handle the camera action
            Intent intent = new Intent(DOSBIMHomeGaleri.this, HomeDosbim.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMDataMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMDataLaboran.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMDataAslab.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMNilaiMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMAbsensiMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_profil_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMHomeProfil.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMStrukturOrganisasi.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMPengumuman.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMHomeUnduhan.class);
            intent.putExtra("nama",nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_dosbim) {
            Intent intent = new Intent(DOSBIMHomeGaleri.this, DOSBIMHomeGaleri.class);
            intent.putExtra("nama", nama_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_logout_dosbim) {
            new LogOut(DOSBIMHomeGaleri.this);

            Intent intent = new Intent(DOSBIMHomeGaleri.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri_dosbim);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void generateGaleriList(ArrayList<GaleriModel> galeriArrayList) {
        recyclerView = findViewById(R.id.rvgaleri_dosbim);
        recyclerView.setHasFixedSize(true);

        adapter = new DOSBIM_GaleriAdapter(galeriArrayList,DOSBIMHomeGaleri.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(DOSBIMHomeGaleri.this,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
