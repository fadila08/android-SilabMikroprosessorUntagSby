package untag.daskom.myapplication.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.adapter.DataUserAdapter;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;

public class Profile extends AppCompatActivity {

    ExpandableRelativeLayout ELprofillaborat, ELprofilkepalalaborat, ELprofillaboran, ELprofilaslab;
    private DataUserAdapter adapter, adapter1, adapter2;
    private RecyclerView recyclerView, recyclerView1, recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
                Toast.makeText(Profile.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        call1.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList1(response.body().getDataUserArrayList());
            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(Profile.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        call2.enqueue(new Callback<DataUserList>() {
            @Override
            public void onResponse(Call<DataUserList> call, Response<DataUserList> response) {
                generateDataUserList2(response.body().getDataUserArrayList());
            }

            @Override
            public void onFailure(Call<DataUserList> call, Throwable t) {
                Toast.makeText(Profile.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataUser> dataUserArrayList) {
        recyclerView = findViewById(R.id.RV_kalab);
        adapter = new DataUserAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Profile.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void generateDataUserList1(ArrayList<DataUser> dataUserArrayList) {
        recyclerView1 = findViewById(R.id.RV_laboran);
        adapter1 = new DataUserAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(Profile.this);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(adapter1);
    }

    private void generateDataUserList2(ArrayList<DataUser> dataUserArrayList) {
        recyclerView2 = findViewById(R.id.RV_aslab);
        adapter2 = new DataUserAdapter(dataUserArrayList);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(Profile.this);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(adapter2);

    }


    public void PROFILLABORAT(View view) {
        ELprofillaborat = findViewById(R.id.ELprofillaborat);
        ELprofillaborat.toggle(); // toggle expand and collapse
    }

    public void PROFILKALABORAT(View view) {
        ELprofilkepalalaborat = findViewById(R.id.ELprofilkepalalaborat);
        ELprofilkepalalaborat.toggle(); // toggle expand and collapse
    }

    public void PROFILLABORAN(View view) {
        ELprofillaboran = findViewById(R.id.ELprofillaboran);
        ELprofillaboran.toggle(); // toggle expand and collapse
    }

    public void PROFILASLAB(View view) {
        ELprofilaslab = findViewById(R.id.ELprofilaslab);
        ELprofilaslab.toggle(); // toggle expand and collapse
    }
}