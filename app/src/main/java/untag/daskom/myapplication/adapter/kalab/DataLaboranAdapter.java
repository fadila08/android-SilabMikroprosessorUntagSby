package untag.daskom.myapplication.adapter.kalab;

import android.app.Dialog;
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

import java.util.ArrayList;

import retrofit2.Call;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.kalab.PopupKalabDataLaboran;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.DataUserDetailList;
import untag.daskom.myapplication.model.UserDetailList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class DataLaboranAdapter extends RecyclerView.Adapter<DataLaboranAdapter.DataLaboranViewHolder> {

    private ArrayList<DataUser> dataList;
    private ArrayList<DataUserDetailList> dataUserDetailLists;
    Dialog popupDialog;
    String id;

    public DataLaboranAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
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
        id = dataList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataLaboranViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama, txtNomorInduk, txtPopupNama, txtPopupNInduk, txtPopupWa, txtPopupEmail;
        DataLaboranViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_laboran_kalab);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_laboran_kalab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int selectId = Integer.parseInt(id);
                    Log.d("id", String.valueOf(selectId));

                    popupDialog = new Dialog(v.getContext());

                    popupDialog.setContentView(R.layout.kalab_data_laboran_popup);

                    txtPopupNama = popupDialog.findViewById(R.id.txt_detail_nama_laboran_kalab);
                    txtPopupNInduk = popupDialog.findViewById(R.id.txt_detail_nip_laboran_kalab);
                    txtPopupWa = popupDialog.findViewById(R.id.txt_detail_no_wa_laboran_kalab);
                    txtPopupEmail = popupDialog.findViewById(R.id.txt_detail_email_laboran_kalab);

//                    txtPopupNama.setText(dataUserDetailLists.get(selectId).getNama());
                    txtNomorInduk.setText("123");
                    txtPopupWa.setText("000");
                    txtPopupEmail.setText("@gmail");

                    popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupDialog.show();
                }
            });
        }
    }
}

////                    String selectNomorInduk = txtNomorInduk.getText().toString();
//                    Intent intent = new Intent(v.getContext(), PopupKalabDataLaboran.class);
////                    int i = position;
//                    intent.putExtra("id",selectId);
//                    v.getContext().startActivity(intent);
//
//                    //untuk mengambil data session

//                    GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);
//                    Call<DataUserDetailList> call = service.getLaboranDetailDataKalab(id);

