package untag.daskom.myapplication.activity.laboran;

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

public class LABORANMasukkanGaleri extends AppCompatActivity {

    EditText etJudul;
    Button btUploadGambar, btSimpan;
    TextView txtNamaGambar;
    SessionManager sessionManager;
    Byte gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboranmasukkan_galeri);

        etJudul = findViewById(R.id.add_judul_gambar_laboran);
        txtNamaGambar = findViewById(R.id.txt_gambar_laboran);
        btUploadGambar = findViewById(R.id.bt_upload_gbr_laboran);
        btSimpan = findViewById(R.id.simpan_gambar_laboran);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriDataService service = RetrofitInstance.getRetrofitInstance().create(GaleriDataService.class);

                Call<GaleriDetailList> call = service.addGaleri("bearer "+session,
                        etJudul.getText().toString(),
                        gambar); //belom nangkap gambar

                call.enqueue(new Callback<GaleriDetailList>() {
                    @Override
                    public void onResponse(Call<GaleriDetailList> call, Response<GaleriDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataGaleriDetailList dataGaleriDetailList = response.body().getData();

                            Toast.makeText(LABORANMasukkanGaleri.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(LABORANMasukkanGaleri.this, LABORANHomeGaleri.class);
                            startActivity(intent);


//
                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<GaleriDetailList> call, Throwable t) {
                        Toast.makeText(LABORANMasukkanGaleri.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
