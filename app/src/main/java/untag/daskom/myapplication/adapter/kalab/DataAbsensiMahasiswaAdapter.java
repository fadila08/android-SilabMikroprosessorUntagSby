package untag.daskom.myapplication.adapter.kalab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class DataAbsensiMahasiswaAdapter extends RecyclerView.Adapter<DataAbsensiMahasiswaAdapter.DataAbsensiMahasiswaViewHolder> {

    private ArrayList<DataUser> dataList;

    public DataAbsensiMahasiswaAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataAbsensiMahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_absensi_mahasiswa_kalab, parent, false);
        return new DataAbsensiMahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAbsensiMahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtKehadiran.setText(dataList.get(position).getNomor_induk());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataAbsensiMahasiswaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtKehadiran;

        DataAbsensiMahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mahasiswa_kalab);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_mahasiswa_kalab);
            txtKehadiran = itemView.findViewById(R.id.txt_kehadiran_mahasiswa_kalab);
        }
    }
}
