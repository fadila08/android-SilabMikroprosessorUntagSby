package untag.daskom.myapplication.activity.laboran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.adapter.laboran.LaboranDataAslabAdapter;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANDataAslab extends AppCompatActivity {

    private LaboranDataAslabAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    TextView tambahDataAslab;
    String nama_laboran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laborandata_aslab);

        nama_laboran = getIntent().getStringExtra("nama");

        //pendefinisian tambah data laboran
        tambahDataAslab = (TextView) findViewById(R.id.add_data_aslab_laboran);

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
