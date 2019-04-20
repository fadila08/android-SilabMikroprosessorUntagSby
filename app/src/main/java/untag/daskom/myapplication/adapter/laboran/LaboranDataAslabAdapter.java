package untag.daskom.myapplication.adapter.laboran;

import android.app.Dialog;
import android.content.Context;
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
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.UserDetailList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class LaboranDataAslabAdapter extends RecyclerView.Adapter<LaboranDataAslabAdapter.LaboranDataAslabViewHolder> {

    private ArrayList<DataUser> dataList;
    Dialog popupDialog;
    Context context;
    SessionManager sessionManager;

    public LaboranDataAslabAdapter(ArrayList<DataUser> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public LaboranDataAslabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_aslab_laboran,parent,false);
        return new LaboranDataAslabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LaboranDataAslabViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtId.setText(dataList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class LaboranDataAslabViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtId, txtPopupNama, txtPopupNInduk, txtPopupWa, txtPopupEmail, txtPopupId;
        Button btEdit, btDelete;

        LaboranDataAslabViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_aslab_laboran);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_aslab_laboran);
            txtId = itemView.findViewById(R.id.txt_id_aslab_laboran);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectId = txtId.getText().toString();
                    Log.d("id", selectId);

                    popupDialog = new Dialog(v.getContext());

                    popupDialog.setContentView(R.layout.kalab_data_aslab_popup);

                    txtPopupNama = popupDialog.findViewById(R.id.txt_detail_nama_aslab_kalab);
                    txtPopupNInduk = popupDialog.findViewById(R.id.txt_detail_nip_aslab_kalab);
                    txtPopupWa = popupDialog.findViewById(R.id.txt_detail_no_wa_aslab_kalab);
                    txtPopupEmail = popupDialog.findViewById(R.id.txt_detail_email_aslab_kalab);
                    txtPopupId = popupDialog.findViewById(R.id.txt_detail_id_aslab_kalab);
                    btEdit = popupDialog.findViewById(R.id.btn_edit_data_aslab_kalab);
                    btDelete = popupDialog.findViewById(R.id.btn_delete_data_aslab_kalab);

                    sessionManager = new SessionManager(context);
                    final String session = sessionManager.getSessionData().get("ID");

                    /** Create handle for the RetrofitInstance interface*/
                    GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

                    /** Call the method with parameter in the interface to get the notice data*/
                    Call<UserDetailList> call = service.getAslabDetailDataKalab("Bearer "+session, selectId);

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
                    popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupDialog.show();


                }
            });


        }
    }
}
