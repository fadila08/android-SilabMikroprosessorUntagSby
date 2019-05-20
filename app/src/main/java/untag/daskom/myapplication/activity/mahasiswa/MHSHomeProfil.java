package untag.daskom.myapplication.activity.mahasiswa;

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
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.activity.laboran.LABORANHomeProfil;
import untag.daskom.myapplication.adapter.DataUserAdapter;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.LogOut;

public class MHSHomeProfil extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    ExpandableRelativeLayout ELprofillaborat, ELprofilkepalalaborat, ELprofillaboran, ELprofilaslab;
    private DataUserAdapter adapter, adapter1, adapter2;
    private RecyclerView recyclerView, recyclerView1, recyclerView2;
    String nama_mhs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profil_mhs);

        Toolbar toolbar = findViewById(R.id.toolbar_profil_mhs);
        setSupportActionBar(toolbar);

        nama_mhs = getIntent().getStringExtra("nama");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profil_mhs);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profil_mhs);
        navigationView.setNavigationItemSelectedListener(this);

        /** Create handle for the RetrofitInstance interface*/
        GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);
        GetUserDataService service1 = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);
        GetUserDataService service2 = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<DataUserList> call = service.getKalabData();
        Call<DataUserList> call1 = service1.getLaboranData();
        Call<DataUserList> call2 = service2.getAslabData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");
        Log.wtf("URL Called", call1.request().url() + "");
        Log.wtf("URL Called", call2.request().url() + "");

        call.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList(response.body().getDataUserArrayList());
            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(MHSHomeProfil.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        call1.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList1(response.body().getDataUserArrayList());
            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(MHSHomeProfil.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        call2.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList2(response.body().getDataUserArrayList());
            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(MHSHomeProfil.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profil_mhs);
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
            Intent intent = new Intent(MHSHomeProfil.this, HomeMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_tugasmhs_mhs) {
            Intent intent = new Intent(MHSHomeProfil.this, MHSTugasPraktikum.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_datasurat_mhs) {
            Intent intent = new Intent(MHSHomeProfil.this, MHSSuratMahasiswa.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_nilaimhs_mhs) {
//            Intent intent = new Intent(HomeMahasiswa.this, .class);
//            startActivity(intent);

        } else if (id == R.id.nav_profil_mhs) {
            Intent intent = new Intent(MHSHomeProfil.this, MHSHomeProfil.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_struktur_mhs) {
            Intent intent = new Intent(MHSHomeProfil.this, MHSStrukturOrganisasi.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman_mhs) {
            Intent intent = new Intent(MHSHomeProfil.this, MHSPengumuman.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan_mhs) {
            Intent intent = new Intent(MHSHomeProfil.this, MHSHomeUnduhan.class);
            intent.putExtra("nama", nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_galeri_mhs) {
            Intent intent = new Intent(MHSHomeProfil.this, MHSHomeGaleri.class);
            intent.putExtra("nama",nama_mhs);
            startActivity(intent);

        } else if (id == R.id.nav_logout_mhs) {
            new LogOut(MHSHomeProfil.this);

            Intent intent = new Intent(MHSHomeProfil.this, MainActivityLogin.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profil_mhs);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void PROFILLABORAT(View view) {
        ELprofillaborat = findViewById(R.id.ELprofillaborat_mhs);
        ELprofillaborat.toggle(); // toggle expand and collapse
    }

    public void PROFILKALABORAT(View view) {
        ELprofilkepalalaborat = findViewById(R.id.ELprofilkepalalaborat_mhs);
        ELprofilkepalalaborat.toggle(); // toggle expand and collapse
    }

    public void PROFILLABORAN(View view) {
        ELprofillaboran = findViewById(R.id.ELprofillaboran_mhs);
        ELprofillaboran.toggle(); // toggle expand and collapse
    }

    public void PROFILASLAB(View view) {
        ELprofilaslab = findViewById(R.id.ELprofilaslab_mhs);
        ELprofilaslab.toggle(); // toggle expand and collapse
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataUser> dataUserArrayList) {
        recyclerView = findViewById(R.id.RV_kalab_mhs);
        adapter = new DataUserAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MHSHomeProfil.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void generateDataUserList1(ArrayList<DataUser> dataUserArrayList) {
        recyclerView1 = findViewById(R.id.RV_laboran_mhs);
        adapter1 = new DataUserAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(MHSHomeProfil.this);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(adapter1);
    }

    private void generateDataUserList2(ArrayList<DataUser> dataUserArrayList) {
        recyclerView2 = findViewById(R.id.RV_aslab_mhs);
        adapter2 = new DataUserAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(MHSHomeProfil.this);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(adapter2);

    }



}
