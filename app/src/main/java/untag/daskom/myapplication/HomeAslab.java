package untag.daskom.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeAslab extends AppCompatActivity implements View.OnClickListener {

    Button btdatamhsaslab, btdatadosbimaslab, btdatalaboranaslab, bttugasmhsaslab, btnilaimhsaslab, btabsmhsaslab, btdatasurataslab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_aslab);

        btdatamhsaslab = (Button)findViewById(R.id.btdatamhsaslab);
        btdatamhsaslab.setOnClickListener(this);

        btdatadosbimaslab = (Button)findViewById(R.id.btdatadosbimaslab);
        btdatadosbimaslab.setOnClickListener(this);

        btdatalaboranaslab = (Button)findViewById(R.id.btdatalaboranaslab);
        btdatalaboranaslab.setOnClickListener(this);

        bttugasmhsaslab = (Button)findViewById(R.id.bttugasmhsaslab);
        bttugasmhsaslab.setOnClickListener(this);

        btnilaimhsaslab = (Button)findViewById(R.id.btnilaimhsaslab);
        btnilaimhsaslab.setOnClickListener(this);

        btabsmhsaslab = (Button)findViewById(R.id.btabsmhsaslab);
        btabsmhsaslab.setOnClickListener(this);

        btdatasurataslab = (Button)findViewById(R.id.btdatasurataslab);
        btdatasurataslab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btdatamhsaslab:
                Intent btdatamhsaslab = new Intent(HomeAslab.this, ASLABDataMahasiswa.class);
                startActivity(btdatamhsaslab);
                break;
            case R.id.btdatadosbimaslab:
                Intent btdatadosbimaslab = new Intent(HomeAslab.this, ASLABDataDosbim.class);
                startActivity(btdatadosbimaslab);
                break;
            case R.id.btdatalaboranaslab:
                Intent btdatalaboranaslab = new Intent(HomeAslab.this, ASLABDataLaboran.class);
                startActivity(btdatalaboranaslab);
                break;
            case R.id.bttugasmhsaslab:
                Intent bttugasmhsaslab = new Intent(HomeAslab.this, ASLABTugasMahasiswa.class);
                startActivity(bttugasmhsaslab);
                break;
            case R.id.btnilaimhsaslab:
                Intent btnilaimhsaslab = new Intent(HomeAslab.this, ASLABNilaiMahasiswa.class);
                startActivity(btnilaimhsaslab);
                break;
            case R.id.btabsmhsaslab:
                Intent btabsmhsaslab = new Intent(HomeAslab.this, ASLABAbsensiMahasiswa.class);
                startActivity(btabsmhsaslab);
                break;
            case R.id.btdatasurataslab:
                Intent btdatasurataslab = new Intent(HomeAslab.this, ASLABDataSurat.class);
                startActivity(btdatasurataslab);
                break;
            default:
                break;
        }
    }
}
