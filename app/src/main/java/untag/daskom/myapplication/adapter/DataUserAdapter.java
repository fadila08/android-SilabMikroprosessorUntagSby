package untag.daskom.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class DataUserAdapter extends RecyclerView.Adapter<DataUserAdapter.DataUserViewHolder> {

    private ArrayList<DataUser> dataList;

    public DataUserAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }


    @Override
    public DataUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_user, parent, false);
        return new DataUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataUserViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtNomorWhatsapp.setText(dataList.get(position).getNomor_whatsapp());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class DataUserViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtEmail, txtNomorWhatsapp;

        DataUserViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk);
            txtEmail = itemView.findViewById(R.id.txt_email);
            txtNomorWhatsapp = itemView.findViewById(R.id.txt_nomor_whatsapp);
        }
    }
}
