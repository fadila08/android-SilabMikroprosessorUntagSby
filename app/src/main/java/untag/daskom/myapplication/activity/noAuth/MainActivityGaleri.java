package untag.daskom.myapplication.activity.noAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import untag.daskom.myapplication.adapter.GaleriAdapter;
import untag.daskom.myapplication.model.GaleriList;
import untag.daskom.myapplication.model.GaleriModel;
import untag.daskom.myapplication.my_interface.GaleriDataService;
import untag.daskom.myapplication.network.RetrofitInstance;

public class MainActivityGaleri extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private GaleriAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main_galeri);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_galeri);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_galeri);
        navigationView.setNavigationItemSelectedListener(this);

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
                Toast.makeText(MainActivityGaleri.this,"Something went wrong....Error ,essage : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_without_login, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(MainActivityGaleri.this, HomeScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivityGaleri.this, MainActivityLogin.class);
            startActivity(intent);

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MainActivityGaleri.this, MainActivityProfil.class);
            startActivity(intent);

        } else if (id == R.id.nav_struktur) {
            Intent intent = new Intent(MainActivityGaleri.this, MainActivityStruktur.class);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman) {
            Intent intent = new Intent(MainActivityGaleri.this, MainActivityPengumuman.class);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan) {
            Intent intent = new Intent(MainActivityGaleri.this, MainActivityUnduhan.class);
            startActivity(intent);

        } else if (id == R.id.nav_galeri) {
            Intent intent = new Intent(MainActivityGaleri.this, MainActivityGaleri.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_galeri);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void generateGaleriList(ArrayList<GaleriModel> galeriArrayList) {
        recyclerView = findViewById(R.id.rvgaleri);

        adapter = new GaleriAdapter(galeriArrayList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivityGaleri.this,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
