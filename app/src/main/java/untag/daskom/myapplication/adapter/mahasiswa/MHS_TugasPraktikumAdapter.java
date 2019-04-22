package untag.daskom.myapplication.adapter.mahasiswa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class MHS_TugasPraktikumAdapter extends RecyclerView.Adapter<MHS_TugasPraktikumAdapter.TugasPraktikumViewHolder>  {
    private ArrayList<DataUser> dataList;

    public MHS_TugasPraktikumAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TugasPraktikumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_tugas_praktikum_mhs, parent, false);
        return new TugasPraktikumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TugasPraktikumViewHolder holder, int position) {
        holder.txtJudulTugas.setText(dataList.get(position).getNama());
        holder.txtJudulFileTugas.setText(dataList.get(position).getNomor_induk());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TugasPraktikumViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudulTugas, txtJudulFileTugas;

        TugasPraktikumViewHolder(View itemView) {
            super(itemView);
            txtJudulTugas = itemView.findViewById(R.id.txt_judul_tugas_praktikum_mhs);
            txtJudulFileTugas = itemView.findViewById(R.id.txt_judul_file_tugas_praktikum_mhs);
        }
    }
}
