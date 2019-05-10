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
import untag.daskom.myapplication.model.DataPengumumanDetailList;
import untag.daskom.myapplication.model.PengumumanDetailList;
import untag.daskom.myapplication.my_interface.PengumumanDataService;
import untag.daskom.myapplication.my_interface.PengumumanDetailDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class LABORANEditPengumuman extends AppCompatActivity {

    EditText etJudul, etIsi, etBatasBerlaku;
    TextView txtLampiran;
    Button PilihLampiran, Simpan;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboranedit_pengumuman);

        etJudul = findViewById(R.id.edit_judul_pengumuman_laboran);
        etIsi = findViewById(R.id.edit_isi_pengumuman_laboran);
        etBatasBerlaku = findViewById(R.id.edit_tgl_berlaku_pengumuman_laboran);
        txtLampiran = findViewById(R.id.txt_edit_nama_file_lampiran_laboran);
        PilihLampiran = findViewById(R.id.bt_edit_lampiran_laboran);
        Simpan = findViewById(R.id.edit_p_laboran);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        final String session = sessionManager.getSessionData().get("ID");

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        PengumumanDetailDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDetailDataService.class);

        Call<PengumumanDetailList> call = service.getPengumumanDetail(id);

        call.enqueue(new Callback<PengumumanDetailList>() {
            @Override
            public void onResponse(Call<PengumumanDetailList> call, Response<PengumumanDetailList> response) {
                Log.d("rspon", response.body().toString());
                DataPengumumanDetailList dataPengumumanDetailList = response.body().getData();

                //isi variabelnya
                etJudul.setText(dataPengumumanDetailList.getJudul());
                etIsi.setText(dataPengumumanDetailList.getIsi());
                etBatasBerlaku.setText(dataPengumumanDetailList.getBatas_tanggal_berlaku());

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
            public void onFailure(Call<PengumumanDetailList> call, Throwable t) {
                Toast.makeText(LABORANEditPengumuman.this,"Something went wrong.....Error message : " + t.getMessage(), Toast.LENGTH_SHORT
                ).show();


            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //simpanedit
                PengumumanDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDataService.class);

                Call<PengumumanDetailList> call = service.editPengumuman("Bearer "+session,id,
                        etJudul.getText().toString(),
                        etIsi.getText().toString(),
//                        txtLampiran.getText().toString(),  // ini untuk file lampiran
                        etBatasBerlaku.getText().toString());

                call.enqueue(new Callback<PengumumanDetailList>() {
                    @Override
                    public void onResponse(Call<PengumumanDetailList> call, Response<PengumumanDetailList> response) {
                        Log.d("response", response.toString());

                        try{

                            DataPengumumanDetailList dataPengumumanDetailList = response.body().getData();

                            Toast.makeText(LABORANEditPengumuman.this,"Data Berhasil Diedit",Toast.LENGTH_SHORT).show();
                            //intent
                            Intent intent = new Intent(LABORANEditPengumuman.this, LABORANPengumuman.class);
                            startActivity(intent);

                        }  catch (Exception e) {
                            Log.d("error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<PengumumanDetailList> call, Throwable t) {
                        Toast.makeText(LABORANEditPengumuman.this, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}
