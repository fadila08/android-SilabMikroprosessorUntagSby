package untag.daskom.myapplication.adapter.laboran;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataMahasiswa;
import untag.daskom.myapplication.model.DataUser;

public class LABORAN_DataMahasiswaAdapter extends RecyclerView.Adapter<LABORAN_DataMahasiswaAdapter.DataMahasiswaViewHolder>  {
    private List<DataMahasiswa> dataList;
    Context context;

    public LABORAN_DataMahasiswaAdapter(List<DataMahasiswa> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public DataMahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_mahasiswa_laboran, parent, false);
        return new DataMahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataMahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama_mahasiswa());
        holder.txtNomorInduk.setText(dataList.get(position).getNbi_mahasiswa());
        holder.txtId.setText(dataList.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataMahasiswaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtId;

        DataMahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mahasiswa_laboran);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_mahasiswa_laboran);
            txtId = itemView.findViewById(R.id.txt_id_mahasiswa_laboran);
        }
    }

}
