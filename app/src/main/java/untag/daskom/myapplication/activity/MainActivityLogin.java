package untag.daskom.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.HomeKalab;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.noAuth.MainActivityGaleri;
import untag.daskom.myapplication.activity.noAuth.MainActivityPengumuman;
import untag.daskom.myapplication.activity.noAuth.MainActivityProfil;
import untag.daskom.myapplication.activity.noAuth.MainActivityStruktur;
import untag.daskom.myapplication.activity.noAuth.MainActivityUnduhan;
import untag.daskom.myapplication.model.DataLoginList;
import untag.daskom.myapplication.model.LoginList;
import untag.daskom.myapplication.my_interface.LoginDataService;
import untag.daskom.myapplication.network.RetrofitInstance;

public class MainActivityLogin extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    Button btlogin;
    EditText txtUsername, txtPassword;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        btlogin = (Button)findViewById(R.id.btlogin);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_login);
        navigationView.setNavigationItemSelectedListener(this);



        // aksi btn login

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginDataService service = RetrofitInstance.getRetrofitInstance().create(LoginDataService.class);

                Call<LoginList> call = service.login(txtUsername.getText().toString(),txtPassword.getText().toString());

                Log.wtf("URL Called", call.request().url() + "");


                call.enqueue(new Callback<LoginList>() {
                    @Override
                    public void onResponse(Call<LoginList> call, Response<LoginList> response) {
                        try{

                            DataLoginList dataLoginList = (DataLoginList) response.body().getData();

                            String dataToken = response.body().getAccess_token();

                            String nama = dataLoginList.getNama();

                            Toast.makeText(MainActivityLogin.this,nama,Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivityLogin.this, HomeKalab.class);
                            startActivity(intent);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginList> call, Throwable t) {
                        Toast.makeText(MainActivityLogin.this,"GAGAL : "+t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);
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
            Intent intent = new Intent(MainActivityLogin.this, HomeScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityLogin.class);
            startActivity(intent);

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityProfil.class);
            startActivity(intent);

        } else if (id == R.id.nav_struktur) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityStruktur.class);
            startActivity(intent);

        } else if (id == R.id.nav_pengumuman) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityPengumuman.class);
            startActivity(intent);

        } else if (id == R.id.nav_unduhan) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityUnduhan.class);
            startActivity(intent);

        } else if (id == R.id.nav_galeri) {
            Intent intent = new Intent(MainActivityLogin.this, MainActivityGaleri.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}