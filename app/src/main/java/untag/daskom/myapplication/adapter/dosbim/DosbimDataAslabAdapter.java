package untag.daskom.myapplication.adapter.dosbim;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DosbimDataAslabAdapter extends RecyclerView.Adapter<DosbimDataAslabAdapter.DosbimDataAslabViewHolder>{

    private ArrayList<DataUser> dataList;
    Dialog popupDialog;
    Context context;
    SessionManager sessionManager;

    public DosbimDataAslabAdapter(ArrayList<DataUser> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public DosbimDataAslabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_aslab_dosbim, parent, false);
        return new DosbimDataAslabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DosbimDataAslabViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtId.setText(dataList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DosbimDataAslabViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtId, txtPopupNama, txtPopupNInduk, txtPopupWa, txtPopupEmail;

        DosbimDataAslabViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_aslab_dosbim);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_aslab_dosbim);
            txtId = itemView.findViewById(R.id.txt_id_aslab_dosbim);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectId = txtId.getText().toString();
                    Log.d("id", selectId);

                    popupDialog = new Dialog(v.getContext());

                    popupDialog.setContentView(R.layout.dosbim_data_aslab_popup);

                    txtPopupNama = popupDialog.findViewById(R.id.txt_detail_nama_aslab_dosbim);
                    txtPopupNInduk = popupDialog.findViewById(R.id.txt_detail_nip_aslab_dosbim);
                    txtPopupWa = popupDialog.findViewById(R.id.txt_detail_no_wa_aslab_dosbim);
                    txtPopupEmail = popupDialog.findViewById(R.id.txt_detail_email_aslab_dosbim);

                    sessionManager = new SessionManager(context);
                    final String session = sessionManager.getSessionData().get("ID");

                    /** Create handle for the RetrofitInstance interface*/
                    GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

                    /** Call the method with parameter in the interface to get the notice data*/
                    Call<UserDetailList> call = service.getAslabDetailDataDosbim("Bearer "+session, selectId);

                    call.enqueue(new Callback<UserDetailList>() {
                        @Override
                        public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                            //sampai sini
                            Log.d("data id",response.body().getData().getId());
                            txtPopupNama.setText(response.body().getData().getNama());
                            txtPopupNInduk.setText(response.body().getData().getNomor_induk());
                            txtPopupWa.setText(response.body().getData().getNomor_whatsapp());
                            txtPopupEmail.setText(response.body().getData().getEmail());
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
