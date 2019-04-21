package untag.daskom.myapplication.adapter.laboran;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import untag.daskom.myapplication.activity.laboran.LABORANInventaris;
import untag.daskom.myapplication.model.DataInventaris;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.my_interface.InventarisDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class InventarisAdapter extends RecyclerView.Adapter<InventarisAdapter.InventarisViewHolder> {

    private ArrayList<DataInventaris> dataList;
    Context context;
    SessionManager sessionManager;

    public InventarisAdapter(ArrayList<DataInventaris> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public InventarisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_inventaris,parent,false);
        return new InventarisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InventarisViewHolder holder, int position) {
        holder.txtId.setText(dataList.get(position).getId());
        holder.txtNamaBarang.setText(dataList.get(position).getNama_barang());
        holder.txtJumlah.setText(dataList.get(position).getJumlah());
        holder.txtKondisi.setText(dataList.get(position).getKondisi());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class InventarisViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtNamaBarang, txtJumlah, txtKondisi, btHapus;

        InventarisViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id_inventaris);
            txtNamaBarang = itemView.findViewById(R.id.txt_nama_barang);
            txtJumlah = itemView.findViewById(R.id.txt_jumlah_barang);
            txtKondisi = itemView.findViewById(R.id.txt_kondisi_barang);
            btHapus = itemView.findViewById(R.id.delete_inventaris);

            sessionManager = new SessionManager(context);
            final String session = sessionManager.getSessionData().get("ID");

            //aksi btn hapus
            btHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                                    InventarisDataService service = RetrofitInstance.getRetrofitInstance().create(InventarisDataService.class);

                                    /** Call the method with parameter in the interface to get the notice data*/
                                    Call<DeleteValue> call = service.deleteInventaris("Bearer "+session, selectIdHapus);

                                    call.enqueue(new Callback<DeleteValue>() {
                                        @Override
                                        public void onResponse(Call<DeleteValue> call, Response<DeleteValue> response) {

//                                                    String message = response.body().getData().getMessage();
//                                                    Log.d("message",message);

                                            Toast.makeText(context,"Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(context, LABORANInventaris.class);
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
