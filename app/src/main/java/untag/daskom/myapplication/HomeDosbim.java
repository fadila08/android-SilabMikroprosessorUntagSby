package untag.daskom.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeDosbim extends AppCompatActivity implements View.OnClickListener {

    Button btdatamhsdosbim, btdatalaborandosbim, btdataaslabdosbim, btnilaimhsdosbim, btabsmhsdosbim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dosbim);

        btdatamhsdosbim = (Button)findViewById(R.id.btdatamhsdosbim);
        btdatamhsdosbim.setOnClickListener(this);

        btdatalaborandosbim = (Button)findViewById(R.id.btdatalaborandosbim);
        btdatalaborandosbim.setOnClickListener(this);

        btdataaslabdosbim = (Button)findViewById(R.id.btdataaslabdosbim);
        btdataaslabdosbim.setOnClickListener(this);

        btnilaimhsdosbim = (Button)findViewById(R.id.btnilaimhsdosbim);
        btnilaimhsdosbim.setOnClickListener(this);

        btabsmhsdosbim = (Button)findViewById(R.id.btabsmhsdosbim);
        btabsmhsdosbim.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btdatamhsdosbim:
                Intent btdatamhsdosbim = new Intent(HomeDosbim.this, DOSBIMDataMahasiswa.class);
                startActivity(btdatamhsdosbim);
                break;
            case R.id.btdatalaborandosbim:
                Intent btdatalaborandosbim = new Intent(HomeDosbim.this, DOSBIMDataLaboran.class);
                startActivity(btdatalaborandosbim);
                break;
            case R.id.btdataaslabdosbim:
                Intent btdataaslabdosbim = new Intent(HomeDosbim.this, DOSBIMDataAslab.class);
                startActivity(btdataaslabdosbim);
                break;
            case R.id.btnilaimhsdosbim:
                Intent btnilaimhsdosbim = new Intent(HomeDosbim.this, DOSBIMNilaiMahasiswa.class);
                startActivity(btnilaimhsdosbim);
                break;
            case R.id.btabsmhsdosbim:
                Intent btabsmhsdosbim = new Intent(HomeDosbim.this, DOSBIMAbsensiMahasiswa.class);
                startActivity(btabsmhsdosbim);
                break;
        }
    }
}
