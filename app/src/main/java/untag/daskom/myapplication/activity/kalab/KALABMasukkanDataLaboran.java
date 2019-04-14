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
import untag.daskom.myapplication.KALABMasukkanDataMahasiswa;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.MainActivityLogin;
import untag.daskom.myapplication.model.DataUserDetailList;
import untag.daskom.myapplication.model.UserDetailList;
import untag.daskom.myapplication.my_interface.LaboranDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class KALABMasukkanDataLaboran extends AppCompatActivity {

    EditText etnama, etnip, etwa, etemail;
    Button btnadd;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalabmasukkan_data_laboran);

        //pendefinisian edit text dan button
        etnama = findViewById(R.id.etnamalaborankalab);
        etnip = findViewById(R.id.etniplaborankalab);
        etwa = findViewById(R.id.etwalaborankalab);
        etemail = findViewById(R.id.etemaillaborankalab);
        btnadd = findViewById(R.id.bttambahlaborankalab);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        //aksi btn simpan
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //untuk mengirim data ke API login dengan retrofit
                LaboranDataService service = RetrofitInstance.getRetrofitInstance().create(LaboranDataService.class);

                Call<UserDetailList> call = service.addLaboran("Bearer"+session,
                                                                etnama.getText().toString(),
                                                                etnip.getText().toString(),
                                                                etwa.getText().toString(),
                                                                etemail.getText().toString());

                call.enqueue(new Callback<UserDetailList>() {
                    @Override
                    public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataUserDetailList dataUserDetailList = response.body().getData();


//                            String dataToken = response.body().getAccess_token();
//                            Log.d("token",dataToken.toString());

//                            String nama = dataLoginList.getNama();
//                            String id = dataLoginList.getId();
//                            String id_role = dataLoginList.getId_roles();

                            Toast.makeText(KALABMasukkanDataLaboran.this,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(KALABMasukkanDataLaboran.this, KALABDataLaboran.class);
                            startActivity(intent);



                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailList> call, Throwable t) {
                        Toast.makeText(KALABMasukkanDataLaboran.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}
