package untag.daskom.myapplication.activity.dosbim;

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
import untag.daskom.myapplication.adapter.dosbim.DosbimDataLaboranAdapter;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class DOSBIMDataLaboran extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DosbimDataLaboranAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    String nama_dosbim, id_dosbim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_laboran_dosbim);

        nama_dosbim = getIntent().getStringExtra("nama");
        id_dosbim = getIntent().getStringExtra("id");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_data_laboran_dosbim);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_laboran_dosbim);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_data_laboran_dosbim);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk menangkap data dari API dengan retrofit
        /** Create handle for the RetrofitInstance interface*/
        GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<DataUserList> call = service.getLaboranDataDosbim("Bearer "+session);

        call.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList(response.body().getDataUserArrayList());
            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(DOSBIMDataLaboran.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //sampai sini
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_laboran_dosbim);
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
            Intent intent = new Intent(DOSBIMDataLaboran.this, HomeDosbim.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMDataMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMDataLaboran.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMDataAslab.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMNilaiMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_absprt_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMAbsensiMahasiswa.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_profil_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMHomeProfil.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMStrukturOrganisasi.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMPengumuman.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMHomeUnduhan.class);
            intent.putExtra("nama",nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_dosbim) {
            Intent intent = new Intent(DOSBIMDataLaboran.this, DOSBIMHomeGaleri.class);
            intent.putExtra("nama", nama_dosbim);
            intent.putExtra("id",id_dosbim);
            startActivity(intent);

        } else if (id == R.id.nav_logout_dosbim) {
            new LogOut(DOSBIMDataLaboran.this);

            Intent intent = new Intent(DOSBIMDataLaboran.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_laboran_dosbim);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //untuk set data dari API yang sudah diambil tadi ke dalam recycler view data laboran(kalab)
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataUser> dataUserArrayList) {
        recyclerView = findViewById(R.id.rv_data_laboran_dosbim);
        adapter = new DosbimDataLaboranAdapter(dataUserArrayList, DOSBIMDataLaboran.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DOSBIMDataLaboran.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
