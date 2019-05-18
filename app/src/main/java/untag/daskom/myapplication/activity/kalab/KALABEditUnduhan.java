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

public class KALABEditUnduhan extends AppCompatActivity {

    EditText etJudul, etKet, etBatasBerlaku;
    TextView txtFile;
    Button btPilihFile, Simpan;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalabedit_unduhan);

        etJudul = findViewById(R.id.edit_judul_unduhan_kalab);
        etKet = findViewById(R.id.edit_ket_unduhan_kalab);
        etBatasBerlaku = findViewById(R.id.edit_tgl_berlaku_unduhan_kalab);
        txtFile = findViewById(R.id.txt_edit_nama_file_unduhan_kalab);
        btPilihFile = findViewById(R.id.btn_edit_file_unduhan_kalab);
        Simpan = findViewById(R.id.bt_simpan_edit_pengumuman_kalab);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        UnduhanDataService service = RetrofitInstance.getRetrofitInstance().create(UnduhanDataService.class);

        Call<UnduhanDetailList> call = service.getUnduhanDetail(id);

        call.enqueue(new Callback<UnduhanDetailList>() {
            @Override
            public void onResponse(Call<UnduhanDetailList> call, Response<UnduhanDetailList> response) {
//                Log.d("rspon", response.body().toString());
                DataUnduhanDetailList dataUnduhanDetailList = response.body().getData();

                //isi variabelnya
                etJudul.setText(dataUnduhanDetailList.getJudul());
                etKet.setText(dataUnduhanDetailList.getKeterangan());
                etBatasBerlaku.setText(dataUnduhanDetailList.getBatas_tanggal_berlaku());

                //ini file lampipran
//                String fileLampiran = dataPengumumanDetailList.getFile_lampiran();

                //cek isi lampiran
//                if ( fileLampiran != null) {
//                    txtFileLampiran.setText(
//                            Html.fromHtml("<a href=\"http://www.google.com\">unduh file lampiran</a>")
//                    );
//                    txtFileLampiran.setMovementMethod(LinkMovementMethod.getInstance());
//                }

            }

            @Override
            public void onFailure(Call<UnduhanDetailList> call, Throwable t) {
                Toast.makeText(KALABEditUnduhan.this,"Something went wrong.....Error message : " + t.getMessage(), Toast.LENGTH_SHORT
                ).show();


            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //simpan
                UnduhanDataService service = RetrofitInstance.getRetrofitInstance().create(UnduhanDataService.class);

                Call<UnduhanDetailList> call = service.editUnduhan("Bearer "+session,id,
                        etJudul.getText().toString(),
                        etKet.getText().toString(),
//                        txtLampiran.getText().toString(),  // ini untuk file lampiran
                        etBatasBerlaku.getText().toString());

                call.enqueue(new Callback<UnduhanDetailList>() {
                    @Override
                    public void onResponse(Call<UnduhanDetailList> call, Response<UnduhanDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataUnduhanDetailList dataUnduhanDetailList = response.body().getData();

                            Toast.makeText(KALABEditUnduhan.this,"Data Berhasil Diedit",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(KALABEditUnduhan.this, KALABHomeUnduhan.class);
                            startActivity(intent);

                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UnduhanDetailList> call, Throwable t) {
                        Toast.makeText(KALABEditUnduhan.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}
