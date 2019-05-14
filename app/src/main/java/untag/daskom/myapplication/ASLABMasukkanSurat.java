package untag.daskom.myapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ASLABMasukkanSurat extends AppCompatActivity {

    TextView txt_pathfile;
    Button btn_aslab_pilihfileupload;
    Intent myFileIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aslabmasukkan_surat);

        txt_pathfile=(TextView)findViewById(R.id.txt_pathfile);
        btn_aslab_pilihfileupload=(Button)findViewById(R.id.btn_aslab_pilihfileupload);

        btn_aslab_pilihfileupload.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                myFileIntent=new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,10 );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch(requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    String path = data.getData().getPath();
                    txt_pathfile.setText(path);
                }
                break;
        }
    }
}
