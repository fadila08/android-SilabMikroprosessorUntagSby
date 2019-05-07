package untag.daskom.myapplication.adapter.aslab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.aslab.ASLABPengumuman;
import untag.daskom.myapplication.activity.noAuth.pengumuman_detail;
import untag.daskom.myapplication.adapter.PengumumanAdapter;
import untag.daskom.myapplication.model.PengumumanModel;

public class ASLAB_PengumumanAdapter extends RecyclerView.Adapter<ASLAB_PengumumanAdapter.ASLAB_PengumumanViewHolder> {

    private ArrayList<PengumumanModel> pengumumanList;
    String id;

    public ASLAB_PengumumanAdapter(ArrayList<PengumumanModel> pengumumanList) {
        this.pengumumanList = pengumumanList;
    }

    @Override
    public ASLAB_PengumumanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_pengumuman_aslab,parent,false);
        return new ASLAB_PengumumanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ASLAB_PengumumanViewHolder holder, int position) {
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

    class ASLAB_PengumumanViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtCreatedAt, txtBatasTanggalBerlaku, txtId;

        ASLAB_PengumumanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_p_aslab);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_p_aslab);
            txtBatasTanggalBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_p_aslab);
            txtId = itemView.findViewById(R.id.txt_id_p_aslab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ASLABPengumuman.class);
//                    int i = position;
                    intent.putExtra("id",txtId.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });

        }
    }
}
