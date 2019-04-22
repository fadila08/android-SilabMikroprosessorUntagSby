package untag.daskom.myapplication.adapter.aslab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class ASLAB_DataMahasiswaAdapter extends RecyclerView.Adapter<ASLAB_DataMahasiswaAdapter.DataMahasiswaViewHolder>  {

    private ArrayList<DataUser> dataList;

    public ASLAB_DataMahasiswaAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataMahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_mahasiswa_aslab, parent, false);
        return new DataMahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataMahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataMahasiswaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk;

        DataMahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mahasiswa_aslab);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_mahasiswa_aslab);
        }
    }
}