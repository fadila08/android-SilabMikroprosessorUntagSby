package untag.daskom.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RVAdapterUnduhan extends RecyclerView.Adapter<RVAdapterUnduhan.ViewHolder> {
    private Context context;
    private List<DataRvUnduhan> mData;

    public RVAdapterUnduhan(Context context, List list  ){
        this.context    =   context;
        this.mData      =   list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvJU, tvWUU;
        public Button btDonwloadunduhan;
        public ViewHolder(View v){
            super(v);
            tvJU        =   (TextView) v.findViewById(R.id.tvjudulunduhan);
            tvWUU        =   (TextView) v.findViewById(R.id.tvwaktuupload_unduhan);
            btDonwloadunduhan  =   (Button)v.findViewById(R.id.btdownloadunduhan);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v   = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_unduhan, parent, false);
        ViewHolder  vh  =   new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvJU.setText("Judul Unduhan :   "+mData.get(position).getJudul_unduhan());
        holder.tvWUU.setText("Tanggal Upload :   "+mData.get(position).getTanggal_upload_unduhan() );
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}