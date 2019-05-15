package untag.daskom.myapplication.adapter.aslab;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.aslab.ASLABEditUnduhan;
import untag.daskom.myapplication.activity.aslab.ASLABHomeUnduhan;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.UnduhanModel;
import untag.daskom.myapplication.my_interface.UnduhanDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class ASLAB_UnduhanAdapter extends RecyclerView.Adapter<ASLAB_UnduhanAdapter.ASLAB_UnduhanViewHolder> {

    private ArrayList<UnduhanModel> unduhanList;
    String id;
    Context context;
    SessionManager sessionManager;

    public ASLAB_UnduhanAdapter(ArrayList<UnduhanModel> unduhanList, Context context) {
        this.unduhanList = unduhanList;
        this.context = context;
    }

    @Override
    public ASLAB_UnduhanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_unduhan_auth,parent,false);
        return new ASLAB_UnduhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ASLAB_UnduhanViewHolder holder, int position) {
        holder.txtJudul.setText(unduhanList.get(position).getJudul());
        holder.txtCreatedAt.setText(unduhanList.get(position).getCreated_at());
        holder.txtKeterangan.setText(unduhanList.get(position).getKeterangan());
        holder.txtBatasBerlaku.setText(unduhanList.get(position).getBatas_tanggal_berlaku());

        id = unduhanList.get(position).getId();
        holder.txtId.setText(id);

    }

    @Override
    public int getItemCount() {
        return unduhanList.size();
    }

    class ASLAB_UnduhanViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtCreatedAt, txtKeterangan, txtId, txtBatasBerlaku;
        Button btnEdit, btnHapus;

        public ASLAB_UnduhanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_unduhan_auth);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_unduhan_auth);
            txtKeterangan = itemView.findViewById(R.id.txt_keterangan_unduhan_auth);
            txtBatasBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_unduhan_auth);
            txtId = itemView.findViewById(R.id.txt_id_unduhan_auth);
            btnEdit = itemView.findViewById(R.id.btn_edit_unduhan);
            btnHapus = itemView.findViewById(R.id.btn_hapu_unduhan);

            sessionManager = new SessionManager(context);
            final String session = sessionManager.getSessionData().get("ID");

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //edit
                    String idSelect = txtId.getText().toString();

                    Intent intent = new Intent(v.getContext(), ASLABEditUnduhan.class);
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
                                    UnduhanDataService service = RetrofitInstance.getRetrofitInstance().create(UnduhanDataService.class);

                                    /** Call the method with parameter in the interface to get the notice data*/
                                    Call<DeleteValue> call = service.deleteUnduhan("Bearer "+session, selectIdHapus);

                                    call.enqueue(new Callback<DeleteValue>() {
                                        @Override
                                        public void onResponse(Call<DeleteValue> call, Response<DeleteValue> response) {

//                                                    String message = response.body().getData().getMessage();
//                                                    Log.d("message",message);

                                            Toast.makeText(context,"Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(context, ASLABHomeUnduhan.class);
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
