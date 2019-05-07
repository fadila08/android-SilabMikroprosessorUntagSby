package untag.daskom.myapplication.adapter.dosbim;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.PengumumanModel;

public class DOSBIM_PengumumanAdapter extends RecyclerView.Adapter<DOSBIM_PengumumanAdapter.DOSBIM_PengumumanViewHolder> {

    private ArrayList<PengumumanModel> pengumumanList;
    String id;

    public DOSBIM_PengumumanAdapter(ArrayList<PengumumanModel> pengumumanList) {
        this.pengumumanList = pengumumanList;
    }

    @Override
    public DOSBIM_PengumumanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_pengumuman_dosbim,parent,false);
        return new DOSBIM_PengumumanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DOSBIM_PengumumanViewHolder holder, int position) {
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

    class DOSBIM_PengumumanViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtCreatedAt, txtBatasTanggalBerlaku, txtId;

        DOSBIM_PengumumanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_p_dosbim);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_p_dosbim);
            txtBatasTanggalBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_p_dosbim);
            txtId = itemView.findViewById(R.id.txt_id_p_dosbim);


        }
    }
}
