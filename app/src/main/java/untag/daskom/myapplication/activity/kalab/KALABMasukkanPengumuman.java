package untag.daskom.myapplication.activity.kalab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataPengumumanDetailList;
import untag.daskom.myapplication.model.PengumumanDetailList;
import untag.daskom.myapplication.my_interface.PengumumanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class KALABMasukkanPengumuman extends AppCompatActivity {

    EditText etJudul, etIsi, etTglBerlaku;
    Button btUploadFile, btSimpan;
    TextView txtNamaFile;
    SessionManager sessionManager;
    Byte lampiran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalabmasukkan_pengumuman);

        etJudul = findViewById(R.id.add_judul_pengumuman_kalab);
        etIsi = findViewById(R.id.add_isi_pengumuman_kalab);
        etTglBerlaku = findViewById(R.id.add_tgl_berlaku_pengumuman_kalab);
        txtNamaFile = findViewById(R.id.txt_nama_file_lampiran_kalab);
        btUploadFile = findViewById(R.id.bt_pilih_lampiran_kalab);
        btSimpan = findViewById(R.id.bt_tambah_pengumuman_kalab);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PengumumanDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDataService.class);

                Call<PengumumanDetailList> call = service.addPengumuman("bearer "+session,
                        etJudul.getText().toString(),
                        etIsi.getText().toString(),
//                        lampiran, //ganti dengan variabel file yg didapat
                        etTglBerlaku.getText().toString());

                call.enqueue(new Callback<PengumumanDetailList>() {
                    @Override
                    public void onResponse(Call<PengumumanDetailList> call, Response<PengumumanDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataPengumumanDetailList dataPengumumanDetailList = response.body().getData();

                            Toast.makeText(KALABMasukkanPengumuman.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(KALABMasukkanPengumuman.this, KALABPengumuman.class);
                            startActivity(intent);


//
                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PengumumanDetailList> call, Throwable t) {
                        Toast.makeText(KALABMasukkanPengumuman.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
