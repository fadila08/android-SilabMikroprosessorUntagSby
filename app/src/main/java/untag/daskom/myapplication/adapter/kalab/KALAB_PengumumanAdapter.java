package untag.daskom.myapplication.adapter.kalab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.KALABDetilPengumuman;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.PengumumanModel;

public class KALAB_PengumumanAdapter extends RecyclerView.Adapter<KALAB_PengumumanAdapter.KALAB_PengumumanViewHolder> {

    private ArrayList<PengumumanModel> pengumumanList;
    String id;

    public KALAB_PengumumanAdapter(ArrayList<PengumumanModel> pengumumanList) {
        this.pengumumanList = pengumumanList;
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

        KALAB_PengumumanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_p_kalab);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_p_kalab);
            txtBatasTanggalBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_p_kalab);
            txtId = itemView.findViewById(R.id.txt_id_p_kalab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), KALABDetilPengumuman.class);
//                    int i = position;
                    intent.putExtra("id",txtId.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });

        }
    }
}
