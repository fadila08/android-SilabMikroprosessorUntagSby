package untag.daskom.myapplication.adapter.aslab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class ASLAB_DataTugasMahasiswaAdapter extends RecyclerView.Adapter<ASLAB_DataTugasMahasiswaAdapter.DataTugasMahasiswaViewHolder>  {

    private ArrayList<DataUser> dataList;

    public ASLAB_DataTugasMahasiswaAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataTugasMahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_tugas_mahasiswa_aslab, parent, false);
        return new DataTugasMahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataTugasMahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtTugas.setText(dataList.get(position).getNomor_induk());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataTugasMahasiswaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtTugas;

        DataTugasMahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mahasiswa_aslab);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_mahasiswa_aslab);
            txtTugas = itemView.findViewById(R.id.txt_tugas_mahasiswa_aslab);
        }
    }
}