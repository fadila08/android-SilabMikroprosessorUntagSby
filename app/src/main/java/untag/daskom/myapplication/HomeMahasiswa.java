package untag.daskom.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeMahasiswa extends AppCompatActivity implements View.OnClickListener {
    Button bttugaspraktikummhs, btdatasuratmhs, btnilaimhsmhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mahasiswa);

        bttugaspraktikummhs = (Button)findViewById(R.id.bttugaspraktikummhs);
        bttugaspraktikummhs.setOnClickListener(this);

        btdatasuratmhs = (Button)findViewById(R.id.btdatasurat);
        btdatasuratmhs.setOnClickListener(this);

        btnilaimhsmhs = (Button)findViewById(R.id.btnilaimhsmhs);
        btnilaimhsmhs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bttugaspraktikummhs:
                Intent bttugaspraktikum = new Intent(HomeMahasiswa.this, MHSHomeTugasMahasiswa.class);
                startActivity(bttugaspraktikum);
                break;
            case R.id.btdatasuratmhs:
                Intent btdatasuratmhs = new Intent(HomeMahasiswa.this, MHSHomeSuratMahasiswa.class);
                startActivity(btdatasuratmhs);
                break;
//            case R.id.btnilaimhsmhs:
//                Intent btnilaimhsmhs = new Intent(HomeMahasiswa.this, .class);
//                startActivity(btnilaimhsmhs);
//                break;
        }
    }
}
