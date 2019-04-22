package untag.daskom.myapplication.activity.dosbim;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import untag.daskom.myapplication.R;

public class PopupDosbimAbsensiMahasiswa extends AppCompatActivity {

    TextView txtNama, txtNip, txtPertemuan1, txtPertemuan2, txtPertemuan3, txtPertemuan4;
    Dialog popupDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dosbim_absensi_mahasiswa_popup);

        txtNama         = findViewById(R.id.txt_detail_nama_mhs_dosbim);
        txtNip          = findViewById(R.id.txt_detail_nip_mhs_dosbim);
        txtPertemuan1   = findViewById(R.id.txt_detail_pertemuan1_dosbim);
        txtPertemuan2   = findViewById(R.id.txt_detail_pertemuan2_dosbim);
        txtPertemuan3   = findViewById(R.id.txt_detail_pertemuan3_dosbim);
        txtPertemuan4   = findViewById(R.id.txt_detail_pertemuan4_dosbim);

        popupDialog = new Dialog(PopupDosbimAbsensiMahasiswa.this);

        popupDialog.setContentView(R.layout.dosbim_absensi_mahasiswa_popup);

        txtNama.setText("namaaku");
        txtNip.setText("nipaku");
        txtPertemuan1.setText("ntp");
        txtPertemuan2.setText("nap");
        txtPertemuan3.setText("napku");
        txtPertemuan4.setText("ntpku");

        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();

    }
}
