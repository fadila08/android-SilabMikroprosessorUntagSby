package untag.daskom.myapplication.activity.laboran;

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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.laboran.LABORAN_DataMahasiswaAdapter;
import untag.daskom.myapplication.model.DataMahasiswa;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.my_interface.MahasiswaDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANDataMahasiswa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private LABORAN_DataMahasiswaAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    String nama_laboran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_mahasiswa_laboran);

        nama_laboran = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_data_mahasiswa_laboran);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_mahasiswa_laboran);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_data_mahasiswa_laboran);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk menangkap data dari API dengan retrofit
        /** Create handle for the RetrofitInstance interface*/
        MahasiswaDataService service = RetrofitInstance.getRetrofitInstance().create(MahasiswaDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<DataMahasiswa>> call = service.getMhs("Bearer "+session);

        call.enqueue(new Callback<List<DataMahasiswa>>() {
            @Override
            public void onResponse(Call<List<DataMahasiswa>> call, Response<List<DataMahasiswa>> response) {
                generateDataUserList(response.body());
            }

            @Override
            public void onFailure(Call<List<DataMahasiswa>> call, Throwable t) {
                Toast.makeText(LABORANDataMahasiswa.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //sampai sini
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_mahasiswa_laboran);
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

        if (id == R.id.nav_history_laboran) {
            // Handle the camera action
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, HomeLaboran.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datalmhs_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANDataMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANDataDosbim.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANDataAslab.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANNilaiMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_inventaris_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANInventaris.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_profil_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANHomeProfil.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANStrukturOrganisasi.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANPengumuman.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANHomeUnduhan.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_laboran) {
            Intent intent = new Intent(LABORANDataMahasiswa.this, LABORANHomeGaleri.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_logout_laboran) {
            new LogOut(LABORANDataMahasiswa.this);

            Intent intent = new Intent(LABORANDataMahasiswa.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_mahasiswa_laboran);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //untuk set data dari API yang sudah diambil tadi ke dalam recycler view data laboran(kalab)
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(List<DataMahasiswa> dataUserArrayList) {
        recyclerView = findViewById(R.id.rv_data_mahasiswa_laboran);
        adapter = new LABORAN_DataMahasiswaAdapter(dataUserArrayList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LABORANDataMahasiswa.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
