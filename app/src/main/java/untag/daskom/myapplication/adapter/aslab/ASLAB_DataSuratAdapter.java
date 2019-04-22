package untag.daskom.myapplication.adapter.aslab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class ASLAB_DataSuratAdapter extends RecyclerView.Adapter<ASLAB_DataSuratAdapter.DataSuratViewHolder>  {

    private ArrayList<DataUser> dataList;

    public ASLAB_DataSuratAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataSuratViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_surat_aslab, parent, false);
        return new DataSuratViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataSuratViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataSuratViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk;

        DataSuratViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_judul_surat_aslab);
            txtNomorInduk = itemView.findViewById(R.id.txt_status_approval_aslab);
        }
    }
}