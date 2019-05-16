package untag.daskom.myapplication.activity.aslab;

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
import untag.daskom.myapplication.model.DataGaleriDetailList;
import untag.daskom.myapplication.model.GaleriDetailList;
import untag.daskom.myapplication.my_interface.GaleriDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class ASLABMasukkanGaleri extends AppCompatActivity {

    EditText etJudul;
    Button btUploadGambar, btSimpan;
    TextView txtNamaGambar;
    SessionManager sessionManager;
    Byte gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aslabmasukkan_galeri);

        etJudul = findViewById(R.id.add_judul_gambar_aslab);
        txtNamaGambar = findViewById(R.id.txt_gambar_aslab);
        btUploadGambar = findViewById(R.id.bt_upload_gbr_aslab);
        btSimpan = findViewById(R.id.simpan_gambar_aslab);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriDataService service = RetrofitInstance.getRetrofitInstance().create(GaleriDataService.class);

                Call<GaleriDetailList> call = service.addGaleri("bearer "+session,
                        etJudul.getText().toString(),
                        gambar);

                call.enqueue(new Callback<GaleriDetailList>() {
                    @Override
                    public void onResponse(Call<GaleriDetailList> call, Response<GaleriDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataGaleriDetailList dataGaleriDetailList = response.body().getData();

                            Toast.makeText(ASLABMasukkanGaleri.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(ASLABMasukkanGaleri.this, ASLABHomeGaleri.class);
                            startActivity(intent);


//
                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<GaleriDetailList> call, Throwable t) {
                        Toast.makeText(ASLABMasukkanGaleri.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
