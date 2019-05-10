package untag.daskom.myapplication.adapter.kalab;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import untag.daskom.myapplication.activity.kalab.KALABDetilPengumuman;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.kalab.KALABEditPengumuman;
import untag.daskom.myapplication.activity.kalab.KALABPengumuman;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.PengumumanModel;
import untag.daskom.myapplication.my_interface.PengumumanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class KALAB_PengumumanAdapter extends RecyclerView.Adapter<KALAB_PengumumanAdapter.KALAB_PengumumanViewHolder> {

    private ArrayList<PengumumanModel> pengumumanList;
    String id;
    Context context;
    SessionManager sessionManager;


    public KALAB_PengumumanAdapter(ArrayList<PengumumanModel> pengumumanList, Context context) {
        this.pengumumanList = pengumumanList;
        this.context = context;
    }

    @Override
    public KALAB_PengumumanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_pengumuman_kalab,parent,false);
        return new KALAB_PengumumanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KALAB_PengumumanViewHolder holder, int position) {
        holder.txtJudul.setText(pengumumanList.get(position).getJudul());
        holder.txtCreatedAt.setText(pengumumanList.get(position).getCreated_at());
        holder.txtBatasTanggalBerlaku.setText(pengumumanList.get(position).getBatas_tanggal_berlaku());
        id = pengumumanList.get(position).getId();
        holder.txtId.setText(id);

    }

    @Override
    public int getItemCount() {
        return pengumumanList.size();
    }

    class KALAB_PengumumanViewHolder extends RecyclerView.ViewHolder{

        TextView txtJudul, txtCreatedAt, txtBatasTanggalBerlaku, txtId;
        ImageView btnEdit, btnHapus;

        KALAB_PengumumanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_p_kalab);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_p_kalab);
            txtBatasTanggalBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_p_kalab);
            txtId = itemView.findViewById(R.id.txt_id_p_kalab);
            btnEdit = itemView.findViewById(R.id.bt_edit_pengumuman_kalab);
            btnHapus = itemView.findViewById(R.id.bt_hapus_pengumuman_kalab);

            sessionManager = new SessionManager(context);
            final String session = sessionManager.getSessionData().get("ID");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), KALABDetilPengumuman.class);
//                    int i = position;
                    intent.putExtra("id",txtId.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //edit
                    String idSelect = txtId.getText().toString();

                    Intent intent = new Intent(v.getContext(), KALABEditPengumuman.class);
                    intent.putExtra("id", idSelect);
                    v.getContext().startActivity(intent);

                }
            });

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //hapus
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
                                    PengumumanDataService service = RetrofitInstance.getRetrofitInstance().create(PengumumanDataService.class);

                                    /** Call the method with parameter in the interface to get the notice data*/
                                    Call<DeleteValue> call = service.deletePengumuman("Bearer "+session, selectIdHapus);

                                    call.enqueue(new Callback<DeleteValue>() {
                                        @Override
                                        public void onResponse(Call<DeleteValue> call, Response<DeleteValue> response) {

//                                                    String message = response.body().getData().getMessage();
//                                                    Log.d("message",message);

                                            Toast.makeText(context,"Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(context, KALABPengumuman.class);
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
