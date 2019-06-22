package untag.daskom.myapplication.adapter.kalab;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.session.SessionManager;

public class DataDosbimAdapter extends RecyclerView.Adapter<DataDosbimAdapter.DataDosbimViewHolder>  {

    private ArrayList<DataUser> dataList;
    Dialog popupDialog;
    SessionManager sessionManager;

    public DataDosbimAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataDosbimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_dosbim_kalab, parent, false);
        return new DataDosbimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataDosbimViewHolder holder, int position) {
        holder.txtNBI.setText(dataList.get(position).getNomor_induk());
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtDosbim.setText(dataList.get(position).getNama());
        holder.txtId.setText(dataList.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataDosbimViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNBI, txtDosbim, txtId, txtPopupId, txtPopupNama, txtPopupNBI, txtPopupPrak, txtPopupSem, txtPopupTP, txtPopupKelas, txtPopupDosbim, txtPopupNIP;

        DataDosbimViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mhs_dosbim_kalab);
            txtNBI = itemView.findViewById(R.id.txt_nbi_dosbim_kalab);
            txtDosbim = itemView.findViewById(R.id.txt_dosbim_dosbim_kalab);
            txtId = itemView.findViewById(R.id.txt_id_dosbim_kalab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectId = txtId.getText().toString();

                    popupDialog = new Dialog(v.getContext());

                    popupDialog.setContentView(R.layout.kalab_data_dosbim_popup);

                    txtPopupId = popupDialog.findViewById(R.id.txt_detail_id_dosbim_kalab);
                    txtPopupNama = popupDialog.findViewById(R.id.txt_detail_nama_mhs_dosbim_kalab);
                    txtPopupNBI = popupDialog.findViewById(R.id.txt_detail_nbi_mhs_dosbim_kalab);
                    txtPopupPrak = popupDialog.findViewById(R.id.txt_detail_praktikum_dosbim_kalab);
                    txtPopupSem = popupDialog.findViewById(R.id.txt_detail_semester_dosbim_kalab);
                    txtPopupTP = popupDialog.findViewById(R.id.txt_detail_thnpel_dosbim_kalab);
                    txtPopupKelas = popupDialog.findViewById(R.id.txt_detail_kelas_dosbim_kalab);
                    txtPopupDosbim = popupDialog.findViewById(R.id.txt_detail_dosbim_dosbim_kalab);
                    txtPopupNIP = popupDialog.findViewById(R.id.txt_detail_nip_dosbim_kalab);

//                    sessionManager = new SessionManager(context);
//                    final String session = sessionManager.getSessionData().get("ID");

                    popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupDialog.show();

                }
            });


        }
    }
}