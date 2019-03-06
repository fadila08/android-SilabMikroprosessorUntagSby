package untag.daskom.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeKalab extends AppCompatActivity implements View.OnClickListener {

    Button btdatalaboran, btdataaslab, btdatadosbim, btdatamhs, btnilaimhs, btabsmhs, btdatasurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_kalab);

        btdatalaboran = (Button)findViewById(R.id.btdatalaborankalab);
        btdatalaboran.setOnClickListener(this);

        btdataaslab = (Button)findViewById(R.id.btdataaslabkalab);
        btdataaslab.setOnClickListener(this);

        btdatadosbim = (Button)findViewById(R.id.btdatadosbimkalab);
        btdatadosbim.setOnClickListener(this);

        btdatamhs = (Button)findViewById(R.id.btdatamhskalab);
        btdatamhs.setOnClickListener(this);

        btnilaimhs = (Button)findViewById(R.id.btnilaimhskalab);
        btnilaimhs.setOnClickListener(this);

        btabsmhs = (Button)findViewById(R.id.btabsmhskalab);
        btabsmhs.setOnClickListener(this);

        btdatasurat = (Button)findViewById(R.id.btdatasurat);
        btdatasurat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btdatalaborankalab:
                Intent btdatalaboran = new Intent(HomeKalab.this, KALABDataLaboran.class);
                startActivity(btdatalaboran);
                break;
            case R.id.btdataaslabkalab:
                Intent btdataaslab = new Intent(HomeKalab.this, KALABDataAslab.class);
                startActivity(btdataaslab);
                break;
            case R.id.btdatadosbimkalab:
                Intent btdatadosbim = new Intent(HomeKalab.this, KALABDataDosbim.class);
                startActivity(btdatadosbim);
                break;
            case R.id.btdatamhskalab:
                Intent btdatamhs = new Intent(HomeKalab.this, KALABDataMahasiswa.class);
                startActivity(btdatamhs);
                break;
            case R.id.btnilaimhskalab:
                Intent btnilaimhs = new Intent(HomeKalab.this, KALABNilaiMahasiswa.class);
                startActivity(btnilaimhs);
                break;
            case R.id.btabsmhskalab:
                Intent btabsmhs = new Intent(HomeKalab.this, KALABAbsensiMahasiswa.class);
                startActivity(btabsmhs);
                break;
            case R.id.btdatasurat:
                Intent btdatasurat = new Intent(HomeKalab.this, KALABDataSurat.class);
                startActivity(btdatasurat);
                break;
            default:
                break;
        }
    }
}
