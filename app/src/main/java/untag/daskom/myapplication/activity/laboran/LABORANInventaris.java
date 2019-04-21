package untag.daskom.myapplication.activity.laboran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.adapter.laboran.InventarisAdapter;
import untag.daskom.myapplication.model.DataInventaris;
import untag.daskom.myapplication.model.DataInventarisList;
import untag.daskom.myapplication.my_interface.InventarisDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANInventaris extends AppCompatActivity {

    private InventarisAdapter adapter;
    private RecyclerView recyclerView;
    SessionManager sessionManager;
    TextView tambahInv, downloadInv;
    String nama_laboran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboraninventaris);

//        nama_laboran = getIntent().getStringExtra("nama");

        //pendefinisian tambah data laboran
        tambahInv = (TextView) findViewById(R.id.tambahdatainventaris);
        downloadInv = (TextView) findViewById(R.id.printinventaris);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        //mulai dari sini untuk menangkap data dari API dengan retrofit
        /** Create handle for the RetrofitInstance interface*/
        InventarisDataService service = RetrofitInstance.getRetrofitInstance().create(InventarisDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<DataInventarisList> call = service.getInventaris("Bearer "+session);

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<DataInventarisList>() {
            @Override
            public void onResponse(Call<DataInventarisList> call, Response<DataInventarisList> response) {
                generateDataUserList(response.body().getDataInventarisArrayList());
                Log.d("data id", String.valueOf(response.body().getDataInventarisArrayList()));

            }

            @Override
            public void onFailure(Call<DataInventarisList> call, Throwable t) {
                Toast.makeText(LABORANInventaris.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //sampai sini

        //ke halaman tambah data laboran
        tambahInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LABORANInventaris.this, LABORANMasukkanInventarisLab.class);
                startActivity(intent);
            }
        });



    }

    //untuk set data dari API yang sudah diambil tadi ke dalam recycler view data laboran(kalab)
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateDataUserList(ArrayList<DataInventaris> dataUserArrayList) {
        recyclerView = findViewById(R.id.rv_inventaris);
        adapter = new InventarisAdapter(dataUserArrayList,LABORANInventaris.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LABORANInventaris.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
