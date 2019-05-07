package untag.daskom.myapplication.adapter.mahasiswa;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.activity.mahasiswa.MHSDetilPengumuman;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.PengumumanModel;

public class MHS_PengumumanAdapter extends RecyclerView.Adapter<MHS_PengumumanAdapter.MHS_PengumumanViewHolder> {

    private ArrayList<PengumumanModel> pengumumanList;
    String id;

    public MHS_PengumumanAdapter(ArrayList<PengumumanModel> pengumumanList) {
        this.pengumumanList = pengumumanList;
    }

    @Override
    public MHS_PengumumanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_pengumuman_mahasiswa,parent,false);
        return new MHS_PengumumanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MHS_PengumumanViewHolder holder, int position) {
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

    class MHS_PengumumanViewHolder extends RecyclerView.ViewHolder{

        TextView txtJudul, txtCreatedAt, txtBatasTanggalBerlaku, txtId;

        MHS_PengumumanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_p_mhs);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_p_mhs);
            txtBatasTanggalBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_p_mhs);
            txtId = itemView.findViewById(R.id.txt_id_p_mhs);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MHSDetilPengumuman.class);
//                    int i = position;
                    intent.putExtra("id",txtId.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });

        }
    }
}
