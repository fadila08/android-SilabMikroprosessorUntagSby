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
import untag.daskom.myapplication.adapter.laboran.LaboranDataAslabAdapter;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANDataAslab extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private LaboranDataAslabAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    TextView tambahDataAslab;
    String nama_laboran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_aslab_laboran);

        nama_laboran = getIntent().getStringExtra("nama");

        //pendefinisian tambah data laboran
        tambahDataAslab = (TextView) findViewById(R.id.add_data_aslab_laboran);

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_data_aslab_laboran);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_aslab_laboran);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_data_aslab_laboran);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk menangkap data dari API dengan retrofit
        /** Create handle for the RetrofitInstance interface*/
        GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<DataUserList> call = service.getAslabDataLaboran("Bearer "+session);

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList(response.body().getDataUserArrayList());
                Log.d("data id", String.valueOf(response.body().getDataUserArrayList()));

            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(LABORANDataAslab.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //sampai sini

        //ke halaman tambah data laboran
        tambahDataAslab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent
                Intent intent = new Intent(LABORANDataAslab.this, LABORANMasukkanDataAslab.class);
                startActivity(intent);
            }
        });

    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_aslab_laboran);
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
            Intent intent = new Intent(LABORANDataAslab.this, HomeLaboran.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datalmhs_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANDataMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANDataDosbim.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANDataAslab.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANNilaiMahasiswa.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_inventaris_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANInventaris.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_profil_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANHomeProfil.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_laboran) {
//            Intent intent = new Intent(MainActivityStruktur.this, MainActivityGaleri.class);
//            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANPengumuman.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANHomeUnduhan.class);
            intent.putExtra("nama", nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_laboran) {
            Intent intent = new Intent(LABORANDataAslab.this, LABORANHomeGaleri.class);
            intent.putExtra("nama",nama_laboran);
            startActivity(intent);

        } else if (id == R.id.nav_logout_laboran) {
            new LogOut(LABORANDataAslab.this);

            Intent intent = new Intent(LABORANDataAslab.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_aslab_laboran);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //untuk set data dari API yang sudah diambil tadi ke dalam recycler view data laboran(kalab)
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataUser> dataUserArrayList) {
        recyclerView = findViewById(R.id.rv_data_aslab_laboran);
        adapter = new LaboranDataAslabAdapter(dataUserArrayList,LABORANDataAslab.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LABORANDataAslab.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
