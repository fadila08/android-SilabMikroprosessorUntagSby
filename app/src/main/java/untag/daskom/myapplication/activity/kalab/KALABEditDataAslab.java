package untag.daskom.myapplication.activity.kalab;

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

public class KALABEditDataAslab extends AppCompatActivity {

    EditText etNama, etNomorInduk, etWa, etEmail;
    Button btEdit;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalabedit_data_aslab);

        etNama = findViewById(R.id.etnama_edit_aslab_kalab);
        etNomorInduk = findViewById(R.id.etnip_edit_aslab_kalab);
        etWa = findViewById(R.id.etwa_edit_aslab_kalab);
        etEmail = findViewById(R.id.etemail_edit_aslab_kalab);
        btEdit = findViewById(R.id.bteditaslab_kalab);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String nomorInduk = intent.getStringExtra("nip");
        String wa = intent.getStringExtra("wa");
        String email = intent.getStringExtra("email");

        etNama.setText(nama);
        etNomorInduk.setText(nomorInduk);
        etWa.setText(wa);
        etEmail.setText(email);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AslabDataService service = RetrofitInstance.getRetrofitInstance().create(AslabDataService.class);

                Call<UserDetailList> call = service.editAslab("Bearer"+session,id,
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

                            Toast.makeText(KALABEditDataAslab.this,"Data Berhasil Diedit",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(KALABEditDataAslab.this, KALABDataAslab.class);
                            startActivity(intent);

                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailList> call, Throwable t) {
                        Toast.makeText(KALABEditDataAslab.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
