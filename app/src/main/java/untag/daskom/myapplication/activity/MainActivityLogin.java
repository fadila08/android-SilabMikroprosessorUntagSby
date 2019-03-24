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
import untag.daskom.myapplication.activity.aslab.HomeAslab;
import untag.daskom.myapplication.activity.dosbim.HomeDosbim;
import untag.daskom.myapplication.activity.kalab.HomeKalab;
import untag.daskom.myapplication.activity.mahasiswa.HomeMahasiswa;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.laboran.HomeLaboran;
import untag.daskom.myapplication.activity.noAuth.MainActivityGaleri;
import untag.daskom.myapplication.activity.noAuth.MainActivityPengumuman;
import untag.daskom.myapplication.activity.noAuth.MainActivityProfil;
import untag.daskom.myapplication.activity.noAuth.MainActivityStruktur;
import untag.daskom.myapplication.activity.noAuth.MainActivityUnduhan;
import untag.daskom.myapplication.model.DataLoginList;
import untag.daskom.myapplication.model.LoginList;
import untag.daskom.myapplication.my_interface.LoginDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class MainActivityLogin extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;
    Button btlogin;
    EditText txtUsername, txtPassword;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main_login);

        //pendefinisian toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        //pendefinisian button dan textview
        btlogin = (Button)findViewById(R.id.btlogin);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);

        //untuk memulai session
        sessionManager = new SessionManager(this);

        //mulai dari sini untuk layout drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_login);
        navigationView.setNavigationItemSelectedListener(this);
        //sampai sini


        // aksi btn login
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //untuk mengirim data ke API login dengan retrofit
                LoginDataService service = RetrofitInstance.getRetrofitInstance().create(LoginDataService.class);

                Call<LoginList> call = RetrofitInstance
                        .getInstance()
                        .getApiLogin()
                        .login(txtUsername.getText().toString(),txtPassword.getText().toString());

                call.enqueue(new Callback<LoginList>() {
                    @Override
                    public void onResponse(Call<LoginList> call, Response<LoginList> response) {
                        Log.d("response", response.toString());
                            try{

                            DataLoginList dataLoginList = response.body().getUser();

                            String dataToken = response.body().getAccess_token();
                            Log.d("token",dataToken.toString());

                            String nama = dataLoginList.getNama();
                            String id = dataLoginList.getId();
                            String id_role = dataLoginList.getId_roles();

                            Toast.makeText(MainActivityLogin.this,"Login Berhasil",Toast.LENGTH_SHORT).show();

                            switch (id_role) {
                                case "1" :  sessionManager.createSession(dataToken);
                                            Intent intent = new Intent(MainActivityLogin.this, HomeKalab.class);
                                            intent.putExtra("id",id);
                                            intent.putExtra("nama",nama);
                                            startActivity(intent);
                                            break;

                                case "2" :  sessionManager.createSession(dataToken);
                                            Intent intent2 = new Intent(MainActivityLogin.this, HomeLaboran.class);
                                            intent2.putExtra("id",id);
                                            intent2.putExtra("nama",nama);
                                            startActivity(intent2);
                                            break;

                                case "3" :  sessionManager.createSession(dataToken);
                                            Intent intent3 = new Intent(MainActivityLogin.this, HomeAslab.class);
                                            intent3.putExtra("id",id);
                                            intent3.putExtra("nama",nama);
                                            startActivity(intent3);
                                            break;

                                case "4" :  sessionManager.createSession(dataToken);
                                            Intent intent4 = new Intent(MainActivityLogin.this, HomeDosbim.class);
                                            intent4.putExtra("id",id);
                                            intent4.putExtra("nama",nama);
                                            startActivity(intent4);
                                            break;

                                case "5" :  sessionManager.createSession(dataToken);
                                            Intent intent5 = new Intent(MainActivityLogin.this, HomeMahasiswa.class);
                                            intent5.putExtra("id",id);
                                            intent5.putExtra("nama",nama);
                                            startActivity(intent5);
                                            break;
                            }

                        } catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginList> call, Throwable t) {
                        Toast.makeText(MainActivityLogin.this, "Failed, eror status : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", call.toString() + " "+t.getMessage());
                    }
                });

            }
        });
    }

    //untuk layout drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_login);
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
        getMenuInflater().inflate(R.menu.main_without_login, menu);
        return true;
    }

    //untuk layout drawer
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