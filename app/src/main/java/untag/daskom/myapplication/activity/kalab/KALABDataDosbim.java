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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.kalab.DataDosbimAdapter;
import untag.daskom.myapplication.adapter.kalab.DataLaboranAdapter;
import untag.daskom.myapplication.model.DataPraktikumList;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.model.PraktikumList;
import untag.daskom.myapplication.my_interface.FilterDataService;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;
import untag.daskom.myapplication.session.SessionManager;

public class KALABDataDosbim extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DataDosbimAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    String nama_kalab;

    private Spinner spPraktikum;
    private Spinner spSemester;
    private Spinner spKelas;
    private Spinner spTahunPel;

    private String[] Praktikum;
    private String[] Semester = {
            "Semua Semester",
            "Genap",
            "Ganjil",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_dosbim_kalab);

        spPraktikum = findViewById(R.id.spPraktikumKalab_dosbim);
        spSemester = findViewById(R.id.spSemesterKalab_dosbim);
        spKelas = findViewById(R.id.spKelasKalab_dosbim);
        spTahunPel = findViewById(R.id.spTPKalab_dosbim);

        nama_kalab = getIntent().getStringExtra("nama");

        //mulai dari sini untuk layout drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_data_dosbim_kalab);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_dosbim_kalab);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_data_dosbim_kalab);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //untuk inisialisasi isi spinner semester
        final ArrayAdapter<String> adapterSpSemester = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Semester);

        //set isi ke Spinner Semester
        spSemester.setAdapter(adapterSpSemester);

        //set ketika item spinner semester dipilih
        spSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //aksi ketika item semester dipilih
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //get Praktikum
        FilterDataService servicePraktikum = RetrofitInstance.getRetrofitInstance().create(FilterDataService.class);

        Call<PraktikumList> callPraktikum = servicePraktikum.getPraktikum("Bearer "+session);

        callPraktikum.enqueue(new Callback<PraktikumList>() {
            @Override
            public void onResponse(Call<PraktikumList> call, Response<PraktikumList> response) {
                Log.d("respon", ""+response.body().getDataPraktikumArrayList().get(0).getNama_praktikum());
               generateDataPraktikumList(response.body().getDataPraktikumArrayList());
//                Praktikum = response.body().getDataPraktikumArrayList().toArray(Praktikum);
//                Toast.makeText(KALABDataDosbim.this,"DATA "+Praktikum,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PraktikumList> call, Throwable t) {
                Toast.makeText(KALABDataDosbim.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
//        //mulai dari sini untuk menangkap data dari API dengan retrofit
//        /** Create handle for the RetrofitInstance interface*/
//        GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);
//
//        /** Call the method with parameter in the interface to get the notice data*/
//        Call<DataUserList> call = service.getLaboranDataKalab("Bearer "+session);
//
//        call.enqueue(new Callback<DataUserList>() {
//            @Override
//            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
//                generateDataUserList(response.body().getDataUserArrayList());
//            }
//
//            @Override
//            public void onFailure(Call<DataUserList> call, Throwable t) {
//                Toast.makeText(KALABDataDosbim.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        //sampai sini
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_dosbim_kalab);
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

        if (id == R.id.nav_history_kalab) {
            // Handle the camera action
//            Intent intent = new Intent(MainActivityStruktur.this, HomeScreen.class);
//            startActivity(intent);

        } else if (id == R.id.nav_home_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, HomeKalab.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datalaboran_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABDataLaboran.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_dataaslab_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABDataAslab.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datadosbim_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABDataDosbim.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datamhs_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABDataMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABNilaiMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_absensi_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABAbsensiMahasiswa.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABDataSurat.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_profil_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABHomeProfil.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABStrukturOrganisasi.class);
            intent.putExtra("nama", nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABPengumuman.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABHomeUnduhan.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_kalab) {
            Intent intent = new Intent(KALABDataDosbim.this, KALABHomeGaleri.class);
            intent.putExtra("nama",nama_kalab);
            startActivity(intent);

        } else if (id == R.id.nav_logout_kalab) {
            new LogOut(KALABDataDosbim.this);

            Intent intent = new Intent(KALABDataDosbim.this, MainActivityLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_data_dosbim_kalab);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    //untuk set data dari API yang sudah diambil tadi ke dalam recycler view data laboran(kalab)
//    /** Method to generate List of notice using RecyclerView with custom adapter*/
//    private void generateDataUserList(ArrayList<DataUser> dataUserArrayList) {
//        recyclerView = findViewById(R.id.rv_data_dosbim_kalab);
//        adapter = new DataDosbimAdapter(dataUserArrayList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KALABDataDosbim.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//    }

    private void generateDataPraktikumList(ArrayList<DataPraktikumList> dataPraktikumArrayList) {
//        dataPraktikumArrayList.toArray(Praktikum);
//        Log.d("praktikum", "isi: "+Praktikum);

    }

}