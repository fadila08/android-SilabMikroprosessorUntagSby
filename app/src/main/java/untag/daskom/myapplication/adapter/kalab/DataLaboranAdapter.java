package untag.daskom.myapplication.adapter.kalab;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;

public class DataLaboranAdapter extends RecyclerView.Adapter<DataLaboranAdapter.DataLaboranViewHolder> {

    private ArrayList<DataUser> dataList;
    Dialog popupDialog;

    public DataLaboranAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataLaboranViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_laboran_kalab, parent, false);
        return new DataLaboranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataLaboranViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataLaboranViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk;

        DataLaboranViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_laboran_kalab);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_laboran_kalab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectNomorInduk = txtNomorInduk.getText().toString();

                    popupDialog = new Dialog(v.getContext());

                    popupDialog.setContentView(R.layout.kalab_data_laboran_popup);

                    popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupDialog.show();
                    //ke inten
                }
            });
        }
    }
}
