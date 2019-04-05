package untag.daskom.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataGaleriDetailList;
import untag.daskom.myapplication.model.GaleriModel;

public class GaleriAdapter extends RecyclerView.Adapter<GaleriAdapter.GaleriViewHolder> {

    private ArrayList<GaleriModel> galeriList;
    Context context;
    String id;
    String imageString;
//    byte[] imageBytes;
    Dialog popupDialog;

    public GaleriAdapter (ArrayList<GaleriModel> galeriList, Context context) {
        this.galeriList = galeriList;
        this.context = context;
    }

    @Override
    public GaleriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_galeri,parent,false);
        return new  GaleriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GaleriViewHolder holder, int position) {
        holder.txtJudul.setText(galeriList.get(position).getJudul());

        id = galeriList.get(position).getId();
        holder.txtId.setText(id);

        imageString = galeriList.get(position).getGambar();

        Log.wtf("imageString", imageString);

        String object;

//        if(object.has("profile_image")) {
            // imageView should be declared in layout xml file with id `imageView`
//            ImageView imageView = (ImageView) context.findViewById(R.id.imageView);
            com.squareup.picasso.Picasso.with(context).
                    load("http://silab.agus-hermanto.com/api/datagaleri/"+imageString).
                    placeholder(R.mipmap.ic_launcher).
                    into(holder.gambar);
//        }


//        int width, height;
//        ImageView image = (ImageView) findViewById(R.id.imageViewId);
//        Bitmap bMap = BitmapFactory.decodeFile(getExternalFilesDir(null) + File.separator + "AndroidTutorialPoint.jpg");
//        width = 2*bMap.getWidth();
//        height = 6*bMap.getHeight();
//        Bitmap bMap2 = Bitmap.createScaledBitmap(bMap, width, height, false);
//        image.setImageBitmap(bMap2);

//        File imgFile = new  File(imageString.toString());
//
//        if(imgFile.exists()){
//
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//            holder.gambar.setImageBitmap(myBitmap);
//
//        }

//        //ambil data gambar
//        imageString = galeriList.get(position).getGambar();
//        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
//        Bitmap decodeImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        holder.gambar.setImageBitmap(decodeImage);
    }

    @Override
    public int getItemCount() {
        return galeriList.size();
    }

    class GaleriViewHolder extends RecyclerView.ViewHolder{
        TextView txtId, txtJudul;
        ImageView gambar;

        GaleriViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id_gambar);
            txtJudul = itemView.findViewById(R.id.txt_judul_gambar);
            gambar = itemView.findViewById(R.id.gambar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectId = txtId.getText().toString();
                    String selectJudul = txtJudul.getText().toString();

                    popupDialog = new Dialog(v.getContext());

                    Button btnClose;
                    TextView txtJudulPopUp;
                    ImageView gambarPopUp;

                    popupDialog.setContentView(R.layout.galeri_popup);

                    btnClose = (Button) popupDialog.findViewById(R.id.btn_close);
                    txtJudulPopUp = (TextView) popupDialog.findViewById(R.id.txt_judul_gambar_popup);
                    gambarPopUp = (ImageView) popupDialog.findViewById(R.id.gambar_popup);

                    txtJudulPopUp.setText(selectJudul);

                    //isi gambar dan judul by id
                    /*
                        isi disini
                     */

                    btnClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupDialog.dismiss();
                        }
                    });

                    popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupDialog.show();

                }
            });

        }
    }
}
