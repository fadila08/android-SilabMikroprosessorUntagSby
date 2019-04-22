package untag.daskom.myapplication.adapter.dosbim;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class DOSBIM_NilaiMahasiswaAdapter extends RecyclerView.Adapter<DOSBIM_NilaiMahasiswaAdapter.DataNilaiMahasiswaViewHolder>  {

    private ArrayList<DataUser> dataList;

    public DOSBIM_NilaiMahasiswaAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataNilaiMahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_nilai_mahasiswa_dosbim, parent, false);
        return new DataNilaiMahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataNilaiMahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtGrade.setText(dataList.get(position).getNomor_induk());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataNilaiMahasiswaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtGrade;

        DataNilaiMahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mahasiswa_dosbim);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_mahasiswa_dosbim);
            txtGrade = itemView.findViewById(R.id.txt_nilai_mahasiswa_dosbim);
        }
    }
}
