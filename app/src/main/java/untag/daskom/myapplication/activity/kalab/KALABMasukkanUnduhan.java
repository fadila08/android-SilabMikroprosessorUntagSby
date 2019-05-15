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
import untag.daskom.myapplication.model.DataUnduhanDetailList;
import untag.daskom.myapplication.model.UnduhanDetailList;
import untag.daskom.myapplication.my_interface.UnduhanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class  KALABMasukkanUnduhan extends AppCompatActivity {

    EditText etJudul, etKet, etTglBerlaku;
    Button btUploadFile, btSimpan;
    TextView txtNamaFile;
    SessionManager sessionManager;
    Byte lampiran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalabmasukkan_unduhan);

        etJudul = findViewById(R.id.add_judul_unduhan_kalab);
        etKet = findViewById(R.id.add_ket_unduhan_kalab);
        etTglBerlaku = findViewById(R.id.add_tgl_berlaku_unduhan_kalab);
        txtNamaFile = findViewById(R.id.txt_nama_file_unduhan_kalab);
        btUploadFile = findViewById(R.id.btn_pilih_file_unduhan_kalab);
        btSimpan = findViewById(R.id.bt_simpan_pengumuman_kalab);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnduhanDataService service = RetrofitInstance.getRetrofitInstance().create(UnduhanDataService.class);

                Call<UnduhanDetailList> call = service.addUnduhan("bearer "+session,
                        etJudul.getText().toString(),
                        etKet.getText().toString(),
//                        lampiran, //ganti dengan variabel file yg didapat
                        etTglBerlaku.getText().toString());

                call.enqueue(new Callback<UnduhanDetailList>() {
                    @Override
                    public void onResponse(Call<UnduhanDetailList> call, Response<UnduhanDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataUnduhanDetailList dataUnduhanDetailList = response.body().getData();

                            Toast.makeText(KALABMasukkanUnduhan.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(KALABMasukkanUnduhan.this, KALABHomeUnduhan.class);
                            startActivity(intent);


//
                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UnduhanDetailList> call, Throwable t) {
                        Toast.makeText(KALABMasukkanUnduhan.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
