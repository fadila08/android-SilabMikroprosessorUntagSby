package untag.daskom.myapplication.adapter.laboran;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class LABORAN_DataDosbimAdapter extends RecyclerView.Adapter<LABORAN_DataDosbimAdapter.DataDosbimViewHolder>  {

    private ArrayList<DataUser> dataList;

    public LABORAN_DataDosbimAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataDosbimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_dosbim_laboran, parent, false);
        return new DataDosbimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataDosbimViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataDosbimViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk;

        DataDosbimViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_dosbim_laboran);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_dosbim_laboran);
        }
    }
}
