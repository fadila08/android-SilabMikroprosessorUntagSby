package untag.daskom.myapplication.activity.noAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import untag.daskom.myapplication.activity.HomeScreen;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.adapter.PengumumanAdapter;
import untag.daskom.myapplication.model.PengumumanList;
import untag.daskom.myapplication.model.PengumumanModel;
import untag.daskom.myapplication.my_interface.PengumumanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;

public class MainActivityPengumuman extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private PengumumanAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate (Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main_pengumuman);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pengumuman);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_pengumuman);
        navigationView.setNavigationItemSelectedListener(this);

        /** Create handle for the RetrofitInstance interface*/
        PengumumanDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<PengumumanList> call = service.getPengumuman();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<PengumumanList>() {
            @Override
            public void onResponse(Call<PengumumanList> call, Response<PengumumanList> response) {
                generatePengumumanList((response.body().getPengumumanArrayList()));
            }

            @Override
            public void onFailure(Call<PengumumanList> call, Throwable t) {
                Toast.makeText(MainActivityPengumuman.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_without_login, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(MainActivityPengumuman.this, HomeScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivityPengumuman.this, MainActivityLogin.class);
            startActivity(intent);

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MainActivityPengumuman.this, MainActivityProfil.class);
            startActivity(intent);

        } else if (id == R.id.nav_struktur) {
            Intent intent = new Intent(MainActivityPengumuman.this, MainActivityStruktur.class);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman) {
            Intent intent = new Intent(MainActivityPengumuman.this, MainActivityPengumuman.class);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan) {
            Intent intent = new Intent(MainActivityPengumuman.this, MainActivityUnduhan.class);
            startActivity(intent);

        } else if (id == R.id.nav_galeri) {
            Intent intent = new Intent(MainActivityPengumuman.this, MainActivityGaleri.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_pengumuman);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generatePengumumanList(ArrayList<PengumumanModel> pengumumanArrayList) {

        recyclerView = findViewById(R.id.rvpengumuman);

        adapter = new PengumumanAdapter(pengumumanArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivityPengumuman.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}