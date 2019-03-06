package untag.daskom.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.PengumumanDetail;
import untag.daskom.myapplication.model.PengumumanDetailList;
import untag.daskom.myapplication.my_interface.PengumumanDetailDataService;
import untag.daskom.myapplication.network.RetrofitInstance;

import java.util.List;

public class pengumuman_detail extends AppCompatActivity {

    String id;
    TextView txtJudul;
    TextView txtCreatedat;
    TextView txtIsi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman_detail);

        txtJudul = findViewById(R.id.txt_judul_pengumuman);
        txtCreatedat = findViewById(R.id.txt_created_at_pengumuman);
        txtIsi = findViewById(R.id.txt_isi_pengumuman);

        id = getIntent().getStringExtra("id");
        Log.d("id", id);

        PengumumanDetailDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDetailDataService.class);

        Call<PengumumanDetailList> call = service.getPengumumanDetail(id);

        call.enqueue(new Callback<PengumumanDetailList>() {
            @Override
            public void onResponse(Call<PengumumanDetailList> call, Response<PengumumanDetailList> response) {
//                List<PengumumanDetail> pengumumanDetails = response.body().getPengumumanDetails();
                PengumumanDetail pengumumanDetail = response.body().getPengumumanDetail();

                //isi variabelnya
//                loadData(pengumumanDetails);
                txtJudul.setText(pengumumanDetail.getJudul());
                txtIsi.setText(pengumumanDetail.getIsi());
                txtCreatedat.setText(pengumumanDetail.getCreated_at());

            }

            @Override
            public void onFailure(Call<PengumumanDetailList> call, Throwable t) {
                Toast.makeText(pengumuman_detail.this,"Something went wrong.....Error message : " + t.getMessage(), Toast.LENGTH_SHORT
                ).show();


            }
        });



    }



//    private void loadData (List<PengumumanDetail> pengumumanDetails) {
//        for (PengumumanDetail data : pengumumanDetails) {
//            txtJudul.setText(data.getJudul());
//            txtCreatedat.setText(data.getCreated_at());
//            txtIsi.setText(data.getIsi());
//        }
//    }
}
