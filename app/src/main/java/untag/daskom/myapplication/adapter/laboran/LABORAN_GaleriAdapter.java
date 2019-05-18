package untag.daskom.myapplication.adapter.laboran;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.laboran.LABORANHomeGaleri;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.GaleriModel;
import untag.daskom.myapplication.my_interface.GaleriDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class LABORAN_GaleriAdapter extends RecyclerView.Adapter<LABORAN_GaleriAdapter.LABORAN_GaleriViewHolder> {

    private ArrayList<GaleriModel> galeriList;
    Context context;
    String id;
    String imageString;
    //    byte[] imageBytes;
    Dialog popupDialog;
    SessionManager sessionManager;

    public LABORAN_GaleriAdapter(ArrayList<GaleriModel> galeriList, Context context) {
        this.galeriList = galeriList;
        this.context = context;
    }

    @Override
    public LABORAN_GaleriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_galeri_auth,parent,false);
        return new  LABORAN_GaleriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LABORAN_GaleriViewHolder holder, int position) {
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

    class LABORAN_GaleriViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtJudul;
        ImageView gambar, hapus;

        LABORAN_GaleriViewHolder(View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txt_id_gambar_auth);
            txtJudul = itemView.findViewById(R.id.txt_judul_gambar_auth);
            gambar = itemView.findViewById(R.id.gambar_auth);
            hapus = itemView.findViewById(R.id.delete_gbr);

            sessionManager = new SessionManager(context);
            final String session = sessionManager.getSessionData().get("ID");

            hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //happus
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Peringatan");
                    alertDialogBuilder
                            .setMessage("Apakah Anda yakin ingin menghapus data ini ?")
                            .setCancelable(false)
                            .setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    String selectIdHapus = txtId.getText().toString();
                                    Log.d("select id hapus",selectIdHapus);

                                    /** Create handle for the RetrofitInstance interface*/
                                    GaleriDataService service = RetrofitInstance.getRetrofitInstance().create(GaleriDataService.class);

                                    /** Call the method with parameter in the interface to get the notice data*/
                                    Call<DeleteValue> call = service.deleteGaleri("Bearer "+session, selectIdHapus);

                                    call.enqueue(new Callback<DeleteValue>() {
                                        @Override
                                        public void onResponse(Call<DeleteValue> call, Response<DeleteValue> response) {

//                                                    String message = response.body().getData().getMessage();
//                                                    Log.d("message",message);

                                            Toast.makeText(context,"Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(context, LABORANHomeGaleri.class);
                                            context.startActivity(intent);

                                        }

                                        @Override
                                        public void onFailure(Call<DeleteValue> call, Throwable t) {
                                            Toast.makeText(context, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            })
                            .setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            });
        }
    }
}
