package untag.daskom.myapplication.activity.kalab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import untag.daskom.myapplication.my_interface.PengumumanDetailDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class KALABEditPengumuman extends AppCompatActivity {

    EditText etJudul, etIsi, etBatasBerlaku;
    TextView txtLampiran;
    Button PilihLampiran, Simpan;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalabedit_pengumuman);

        etJudul = findViewById(R.id.edit_judul_pengumuman_kalab);
        etIsi = findViewById(R.id.edit_isi_pengumuman_kalab);
        etBatasBerlaku = findViewById(R.id.edit_tgl_berlaku_pengumuman_kalab);
        txtLampiran = findViewById(R.id.txt_edit_nama_file_lampiran_kalab);
        PilihLampiran = findViewById(R.id.bt_edit_lampiran_kalab);
        Simpan = findViewById(R.id.edit_p_kalab);

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
                Toast.makeText(KALABEditPengumuman.this,"Something went wrong.....Error message : " + t.getMessage(), Toast.LENGTH_SHORT
                ).show();


            }
        });


    }
}
