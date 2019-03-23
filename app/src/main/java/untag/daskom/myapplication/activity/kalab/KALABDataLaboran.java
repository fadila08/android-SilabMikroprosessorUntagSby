package untag.daskom.myapplication.activity.kalab;

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
import untag.daskom.myapplication.KALABAbsensiMahasiswa;
import untag.daskom.myapplication.KALABDataAslab;
import untag.daskom.myapplication.KALABDataDosbim;
import untag.daskom.myapplication.KALABDataMahasiswa;
import untag.daskom.myapplication.KALABDataSurat;
import untag.daskom.myapplication.KALABNilaiMahasiswa;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.DataUserAdapter;
import untag.daskom.myapplication.adapter.kalab.DataLaboranAdapter;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class KALABDataLaboran extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DataLaboranAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_laboran_kalab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_data_laboran_kalab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_laboran_kalab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_data_laboran_kalab);
        navigationView.setNavigationItemSelectedListener(this);

        sessionManager = new SessionManager(this);

        String session = sessionManager.getSessionData().get("ID");

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
                Toast.makeText(KALABDataLaboran.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_laboran_kalab);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_kalab, menu);
        return true;
    }

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
            Intent intent = new Intent(KALABDataLaboran.this, HomeKalab.class);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_kalab) {
            Intent intent = new Intent(KALABDataLaboran.this, KALABDataLaboran.class);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_kalab) {
            Intent intent = new Intent(KALABDataLaboran.this, KALABDataAslab.class);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_kalab) {
            Intent intent = new Intent(KALABDataLaboran.this, KALABDataDosbim.class);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_kalab) {
            Intent intent = new Intent(KALABDataLaboran.this, KALABDataMahasiswa.class);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_kalab) {
            Intent intent = new Intent(KALABDataLaboran.this, KALABNilaiMahasiswa.class);
            startActivity(intent);

        } else if (id == R.id.nav_absensi_kalab) {
            Intent intent = new Intent(KALABDataLaboran.this, KALABAbsensiMahasiswa.class);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_kalab) {
            Intent intent = new Intent(KALABDataLaboran.this, KALABDataSurat.class);
            startActivity(intent);

        } else if (id == R.id.nav_profil_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_struktur_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_unduhan_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_galeri_kalab) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_logout_kalab) {
            new LogOut(KALABDataLaboran.this);

            Intent intent = new Intent(KALABDataLaboran.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_laboran_kalab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataUser> dataUserArrayList) {
        recyclerView = findViewById(R.id.rv_data_laboran_kalab);
        adapter = new DataLaboranAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KALABDataLaboran.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
