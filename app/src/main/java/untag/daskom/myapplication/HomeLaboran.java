package untag.daskom.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeLaboran extends AppCompatActivity implements View.OnClickListener{

    Button btdatamhslaboran, btdatadosbimlaboran, btdataaslablaboran, btnilaimhslaboran, btinventarislaboran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_laboran);

        btdatamhslaboran = (Button)findViewById(R.id.btdatamhslaboran);
        btdatamhslaboran.setOnClickListener(this);

        btdatadosbimlaboran = (Button)findViewById(R.id.btdatadosbimlaboran);
        btdatadosbimlaboran.setOnClickListener(this);

        btdataaslablaboran = (Button)findViewById(R.id.btdataaslablaboran);
        btdataaslablaboran.setOnClickListener(this);

        btnilaimhslaboran = (Button)findViewById(R.id.btnilaimhslaboran);
        btnilaimhslaboran.setOnClickListener(this);

        btinventarislaboran = (Button)findViewById(R.id.btinventarislaboran);
        btinventarislaboran.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btdatamhslaboran:
                Intent btdatamhslaboran = new Intent(HomeLaboran.this, LABORANDataMahasiswa.class);
                startActivity(btdatamhslaboran);
                break;
            case R.id.btdatadosbimlaboran:
                Intent btdatadosbimlaboran = new Intent(HomeLaboran.this, LABORANDataDosbim.class);
                startActivity(btdatadosbimlaboran);
                break;
            case R.id.btdataaslablaboran:
                Intent btdataaslablaboran = new Intent(HomeLaboran.this, LABORANDataAslab.class);
                startActivity(btdataaslablaboran);
                break;
            case R.id.btnilaimhslaboran:
                Intent btnilaimhslaboran = new Intent(HomeLaboran.this, LABORANNilaiMahasiswa.class);
                startActivity(btnilaimhslaboran);
                break;
            case R.id.btinventarislaboran:
                Intent btinventarislaboran = new Intent(HomeLaboran.this, LABORANInventaris.class);
                startActivity(btinventarislaboran);
                break;
        }
    }
}
