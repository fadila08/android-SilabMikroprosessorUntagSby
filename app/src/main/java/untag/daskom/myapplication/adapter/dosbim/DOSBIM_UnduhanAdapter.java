package untag.daskom.myapplication.adapter.dosbim;

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
import untag.daskom.myapplication.session.SessionManager;

public class DOSBIM_UnduhanAdapter extends RecyclerView.Adapter<DOSBIM_UnduhanAdapter.DOSBIM_UnduhanViewHolder> {

    private ArrayList<UnduhanModel> unduhanList;
    String id;
    Context context;
    SessionManager sessionManager;

    public DOSBIM_UnduhanAdapter(ArrayList<UnduhanModel> unduhanList, Context context) {
        this.unduhanList = unduhanList;
        this.context = context;
    }

    @Override
    public DOSBIM_UnduhanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_unduhan,parent,false);
        return new DOSBIM_UnduhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DOSBIM_UnduhanViewHolder holder, int position) {
        holder.txtJudul.setText(unduhanList.get(position).getJudul());
        holder.txtCreatedAt.setText(unduhanList.get(position).getCreated_at());
        holder.txtKeterangan.setText(unduhanList.get(position).getKeterangan());
        holder.txtBatasBerlaku.setText(unduhanList.get(position).getBatas_tanggal_berlaku());

        id = unduhanList.get(position).getId();
        holder.txtId.setText(id);

//        holder.btnUnduh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //link ke api download
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return unduhanList.size();
    }

    class DOSBIM_UnduhanViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtCreatedAt, txtKeterangan, txtId, txtBatasBerlaku;
        Button btnUnduh;

        public DOSBIM_UnduhanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_unduhan);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_unduhan);
            txtKeterangan = itemView.findViewById(R.id.txt_keterangan_unduhan);
            txtBatasBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_unduhan);
            txtId = itemView.findViewById(R.id.txt_id_unduhan);
            btnUnduh = itemView.findViewById(R.id.btn_unduhan);

            sessionManager = new SessionManager(context);
            final String session = sessionManager.getSessionData().get("ID");

            btnUnduh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //unduh disini
                }
            });
        }
    }
}
