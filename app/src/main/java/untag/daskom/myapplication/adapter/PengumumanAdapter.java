package untag.daskom.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.activity.Pengumuman;
import untag.daskom.myapplication.activity.pengumuman_detail;
import untag.daskom.myapplication.model.PengumumanModel;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.PengumumanViewHolder> {

    private ArrayList<PengumumanModel> pengumumanList;
    String id;


    public PengumumanAdapter(ArrayList<PengumumanModel> pengumumanList) {
        this.pengumumanList = pengumumanList;
    }


    @Override
    public PengumumanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_pengumuman,parent,false);
        return new PengumumanViewHolder(view);
    }

    int position;
    @Override
    public void onBindViewHolder(PengumumanViewHolder holder, int position) {

        holder.txtJudul.setText(pengumumanList.get(position).getJudul());
        holder.txtCreatedAt.setText(pengumumanList.get(position).getCreated_at());
        holder.txtBatasTanggalBerlaku.setText(pengumumanList.get(position).getBatas_tanggal_berlaku());
        id = pengumumanList.get(position).getId();

    }

    @Override
    public int getItemCount() {
        return pengumumanList.size();
    }


    class PengumumanViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtCreatedAt, txtBatasTanggalBerlaku;

        PengumumanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at);
            txtBatasTanggalBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), pengumuman_detail.class);
                    int i = position;
                    intent.putExtra("id",id);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

}
