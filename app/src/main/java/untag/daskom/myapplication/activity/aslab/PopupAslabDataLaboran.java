package untag.daskom.myapplication.activity.aslab;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import untag.daskom.myapplication.R;

public class PopupAslabDataLaboran extends AppCompatActivity {

    TextView txtNama, txtNip, txtWa, txtEmail;
    Dialog popupDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aslab_data_laboran_popup);

        txtNama = findViewById(R.id.txt_detail_nama_laboran_aslab);
        txtNip = findViewById(R.id.txt_detail_nip_laboran_aslab);
        txtWa = findViewById(R.id.txt_detail_no_wa_laboran_aslab);
        txtEmail = findViewById(R.id.txt_detail_email_laboran_aslab);

        popupDialog = new Dialog(PopupAslabDataLaboran.this);

        popupDialog.setContentView(R.layout.aslab_data_laboran_popup);




        txtNama.setText("namaaku");
        txtNip.setText("nipaku");
        txtWa.setText("waku");
        txtEmail.setText("emailu");

        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();
    }
}
