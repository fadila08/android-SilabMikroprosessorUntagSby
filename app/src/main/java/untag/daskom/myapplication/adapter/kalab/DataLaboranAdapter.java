package untag.daskom.myapplication.adapter.kalab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import retrofit2.Retrofit;
import untag.daskom.myapplication.KALABEditDataLaboran;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.kalab.KALABDataLaboran;
import untag.daskom.myapplication.activity.kalab.PopupKalabDataLaboran;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserDetailList;
import untag.daskom.myapplication.model.DataUserList;
import untag.daskom.myapplication.model.DeleteValue;
import untag.daskom.myapplication.model.UserDetailList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.my_interface.LaboranDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class DataLaboranAdapter extends RecyclerView.Adapter<DataLaboranAdapter.DataLaboranViewHolder> {

    private ArrayList<DataUser> dataList;
    Dialog popupDialog;
//    String id;
    Context context;
    SessionManager sessionManager;

    public DataLaboranAdapter(ArrayList<DataUser> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public DataLaboranViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_laboran_kalab, parent, false);
        return new DataLaboranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataLaboranViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtId.setText(dataList.get(position).getId());
//        id = dataList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataLaboranViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtId, txtPopupNama, txtPopupNInduk, txtPopupWa, txtPopupEmail, txtPopupId;
        Button btnEdit, btnHapus;

        DataLaboranViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_laboran_kalab);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_laboran_kalab);
            txtId = itemView.findViewById(R.id.txt_id_laboran_kalab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectId = txtId.getText().toString();
                    Log.d("id", selectId);

                    popupDialog = new Dialog(v.getContext());

                    popupDialog.setContentView(R.layout.kalab_data_laboran_popup);

                    txtPopupNama = popupDialog.findViewById(R.id.txt_detail_nama_laboran_kalab);
                    txtPopupNInduk = popupDialog.findViewById(R.id.txt_detail_nip_laboran_kalab);
                    txtPopupWa = popupDialog.findViewById(R.id.txt_detail_no_wa_laboran_kalab);
                    txtPopupEmail = popupDialog.findViewById(R.id.txt_detail_email_laboran_kalab);
                    txtPopupId = popupDialog.findViewById(R.id.txt_detail_id_laboran_kalab);
                    btnEdit = popupDialog.findViewById(R.id.btn_edit_data_laboran_kalab);
                    btnHapus = popupDialog.findViewById(R.id.btn_delete_data_laboran_kalab);

                    //untuk mengambil data session
                    sessionManager = new SessionManager(context);
                    final String session = sessionManager.getSessionData().get("ID");

                    //mulai dari sini untuk menangkap data dari API dengan retrofit
                    /** Create handle for the RetrofitInstance interface*/
                    GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

                    /** Call the method with parameter in the interface to get the notice data*/
                    Call<UserDetailList> call = service.getLaboranDetailDataKalab("Bearer "+session, selectId);

                    call.enqueue(new Callback<UserDetailList>() {
                        @Override
                        public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                            //sampai sini
                            Log.d("data id",response.body().getData().getId());
                            txtPopupNama.setText(response.body().getData().getNama());
                            txtPopupNInduk.setText(response.body().getData().getNomor_induk());
                            txtPopupWa.setText(response.body().getData().getNomor_whatsapp());
                            txtPopupEmail.setText(response.body().getData().getEmail());
                            txtPopupId.setText(response.body().getData().getId());
                        }

                        @Override
                        public void onFailure(Call<UserDetailList> call, Throwable t) {
                            Toast.makeText(context, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                    //aksi btn edit
                    btnEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), KALABEditDataLaboran.class);
                            v.getContext().startActivity(intent);
                        }
                    });

                    //aksi btn  hapus
                    btnHapus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) { //kalo ada masalah, coba hapus final di View v
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                            alertDialogBuilder.setTitle("Peringatan");
                            alertDialogBuilder
                                    .setMessage("Apakah Anda yakin ingin menghapus data ini ?")
                                    .setCancelable(false)
                                    .setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            String selectIdHapus = txtPopupId.getText().toString();

                                            /** Create handle for the RetrofitInstance interface*/
                                            LaboranDataService service = RetrofitInstance.getRetrofitInstance().create(LaboranDataService.class);

                                            /** Call the method with parameter in the interface to get the notice data*/
                                            Call<DeleteValue> call = service.deleteLaboran("Bearer "+session, selectIdHapus);

                                            call.enqueue(new Callback<DeleteValue>() {
                                                @Override
                                                public void onResponse(Call<DeleteValue> call, Response<DeleteValue> response) {

                                                    String message = response.body().getData().getMessage();

                                                    Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();

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

                    popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupDialog.show();
                }
            });
        }
    }
}
