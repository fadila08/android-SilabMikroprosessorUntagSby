package untag.daskom.myapplication.adapter.mahasiswa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.UnduhanModel;

public class MHS_UnduhanAdapter extends RecyclerView.Adapter<MHS_UnduhanAdapter.MHS_UnduhanViewHolder> {

    private ArrayList<UnduhanModel> unduhanList;
    String id;
    Context context;

    public MHS_UnduhanAdapter(ArrayList<UnduhanModel> unduhanList, Context context) {
        this.unduhanList = unduhanList;
        this.context = context;
    }

    @Override
    public MHS_UnduhanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_unduhan,parent,false);
        return new MHS_UnduhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MHS_UnduhanViewHolder holder, int position) {
        holder.txtJudul.setText(unduhanList.get(position).getJudul());
        holder.txtCreatedAt.setText(unduhanList.get(position).getCreated_at());
        holder.txtKeterangan.setText(unduhanList.get(position).getKeterangan());
        holder.txtBatasBerlaku.setText(unduhanList.get(position).getBatas_tanggal_berlaku());

        id = unduhanList.get(position).getId();
        holder.txtId.setText(id);

    }

    @Override
    public int getItemCount() {
        return unduhanList.size();
    }

    class MHS_UnduhanViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtCreatedAt, txtKeterangan, txtId, txtBatasBerlaku;
        Button btnUnduh;

        public MHS_UnduhanViewHolder(View itemView) {
            super(itemView);

            txtJudul = itemView.findViewById(R.id.txt_judul_unduhan);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_unduhan);
            txtKeterangan = itemView.findViewById(R.id.txt_keterangan_unduhan);
            txtBatasBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_unduhan);
            txtId = itemView.findViewById(R.id.txt_id_unduhan);
            btnUnduh = itemView.findViewById(R.id.btn_unduhan);

            btnUnduh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //unduh disini
                }
            });

        }
    }
}
