package untag.daskom.myapplication.activity.laboran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataInventaris;
import untag.daskom.myapplication.model.DataInventarisDetailList;
import untag.daskom.myapplication.model.DataInventarisList;
import untag.daskom.myapplication.model.InventarisDetailList;
import untag.daskom.myapplication.my_interface.InventarisDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANMasukkanInventarisLab extends AppCompatActivity {

    EditText etNama, etJumlah, etKondisi;
    Button btSimpan;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboranmasukkan_inventaris_lab);

        //pendefinisian edit text dan button
        etNama = findViewById(R.id.et_nama_barang);
        etJumlah = findViewById(R.id.et_jumlah_barang);
        etKondisi = findViewById(R.id.et_kondisi_barang);
        btSimpan = findViewById(R.id.bt_simpan_inventaris);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        //aksi btn simpan
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //untuk mengirim data ke API dengan retrofit
                InventarisDataService service = RetrofitInstance.getRetrofitInstance().create(InventarisDataService.class);

                Call<InventarisDetailList> call = service.addInventaris("Bearer"+session,
                        etNama.getText().toString(),
                        etJumlah.getText().toString(),
                        etKondisi.getText().toString());

                call.enqueue(new Callback<InventarisDetailList>() {
                    @Override
                    public void onResponse(Call<InventarisDetailList> call, Response<InventarisDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataInventarisDetailList dataUserDetailList = response.body().getData();

                            Toast.makeText(LABORANMasukkanInventarisLab.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();

                            //intent
                            Intent intent = new Intent(LABORANMasukkanInventarisLab.this, LABORANInventaris.class);
                            startActivity(intent);

                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<InventarisDetailList> call, Throwable t) {
                        Toast.makeText(LABORANMasukkanInventarisLab.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
