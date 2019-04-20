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
import untag.daskom.myapplication.model.DataUserDetailList;
import untag.daskom.myapplication.model.UserDetailList;
import untag.daskom.myapplication.my_interface.AslabDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANMasukkanDataAslab extends AppCompatActivity {

    EditText etNama, etNomorInduk, etWa, etEmail;
    Button btTambah;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboranmasukkan_data_aslab);

        //pendefinisian edit text dan button
        etNama = findViewById(R.id.etnamaaslablaboran);
        etNomorInduk = findViewById(R.id.etnipaslablaboran);
        etWa = findViewById(R.id.etwaaslablaboran);
        etEmail = findViewById(R.id.etemailaslablaboran);
        btTambah = findViewById(R.id.bttambahaslablaboran);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        //aksi btn simpan
        btTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //untuk mengirim data ke API dengan retrofit
                AslabDataService service = RetrofitInstance.getRetrofitInstance().create(AslabDataService.class);

                Call<UserDetailList> call = service.addAslab("Bearer"+session,
                        etNama.getText().toString(),
                        etNomorInduk.getText().toString(),
                        etWa.getText().toString(),
                        etEmail.getText().toString());

                call.enqueue(new Callback<UserDetailList>() {
                    @Override
                    public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataUserDetailList dataUserDetailList = response.body().getData();

                            Toast.makeText(LABORANMasukkanDataAslab.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();

                            //intent
                            Intent intent = new Intent(LABORANMasukkanDataAslab.this, LABORANDataAslab.class);
                            startActivity(intent);

                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailList> call, Throwable t) {
                        Toast.makeText(LABORANMasukkanDataAslab.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
