package untag.daskom.myapplication.activity.mahasiswa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
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

public class MHSDetilPengumuman extends AppCompatActivity {

    String id;
    TextView txtJudul;
    TextView txtCreatedat;
    TextView txtIsi;
    TextView txtFileLampiran;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhsdetil_pengumuman);

        txtJudul = findViewById(R.id.txt_judul_pengumuman_mhs);
        txtCreatedat = findViewById(R.id.txt_created_at_pengumuman_mhs);
        txtIsi = findViewById(R.id.txt_isi_pengumuman_mhs);
        txtFileLampiran = findViewById(R.id.txt_file_lampiran_mhs);

        //untuk mengambil data session
        sessionManager = new SessionManager(this);
        String session = sessionManager.getSessionData().get("ID");

        id = getIntent().getStringExtra("id");
        Log.d("id", id);

        PengumumanDetailDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDetailDataService.class);

        Call<PengumumanDetailList> call = service.getPengumumanDetail(id);

        call.enqueue(new Callback<PengumumanDetailList>() {
            @Override
            public void onResponse(Call<PengumumanDetailList> call, Response<PengumumanDetailList> response) {
                Log.d("rspon", response.body().toString());
                DataPengumumanDetailList dataPengumumanDetailList = response.body().getData();

                //isi variabelnya
                txtJudul.setText(dataPengumumanDetailList.getJudul());
                txtIsi.setText(dataPengumumanDetailList.getIsi());
                txtCreatedat.setText(dataPengumumanDetailList.getDiupload_pada());

                String fileLampiran = dataPengumumanDetailList.getFile_lampiran();

                //cek isi lampiran
                if ( fileLampiran != null) {
                    txtFileLampiran.setText(
                            Html.fromHtml("<a href=\"http://www.google.com\">unduh file lampiran</a>")
                    );
                    txtFileLampiran.setMovementMethod(LinkMovementMethod.getInstance());
                }

            }

            @Override
            public void onFailure(Call<PengumumanDetailList> call, Throwable t) {
                Toast.makeText(MHSDetilPengumuman.this,"Something went wrong.....Error message : " + t.getMessage(), Toast.LENGTH_SHORT
                ).show();


            }
        });


    }
}
