package untag.daskom.myapplication.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.UnduhanModel;

import static android.content.Context.DOWNLOAD_SERVICE;

public class UnduhanAdapter extends RecyclerView.Adapter<UnduhanAdapter.UnduhanViewHolder> {

    private ArrayList<UnduhanModel> unduhanList;
    String id;
    String UnduhanString;
    Context context;

    public UnduhanAdapter(ArrayList<UnduhanModel> unduhanList, Context context) {
        this.unduhanList = unduhanList;
        this.context = context;
    }

    @Override
    public UnduhanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_unduhan,parent,false);
        return new UnduhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UnduhanViewHolder holder, int position) {
        holder.txtJudul.setText(unduhanList.get(position).getJudul());
        holder.txtCreatedAt.setText(unduhanList.get(position).getCreated_at());
        holder.txtKeterangan.setText(unduhanList.get(position).getKeterangan());
        holder.txtBatasBerlaku.setText(unduhanList.get(position).getBatas_tanggal_berlaku());

        id = unduhanList.get(position).getId();
        holder.txtId.setText(id);

        UnduhanString = unduhanList.get(position).getFile();

        holder.btnUnduh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DownloadManager.Request r = new DownloadManager.Request(Uri.parse("http://silab.agus-hermanto.com/uploads/"+UnduhanString));

// This put the download in the same Download dir the browser uses
                r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "fileName");

// When downloading music and videos they will be listed in the player
// (Seems to be available since Honeycomb only)
                r.allowScanningByMediaScanner();

// Notify user when download is completed
// (Seems to be available since Honeycomb only)
                r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

// Start download
                DownloadManager dm = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(r);
            }
        });


//        holder.btnUnduh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //link ke api download
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return unduhanList.size();
    }

    class UnduhanViewHolder extends RecyclerView.ViewHolder{

        TextView txtJudul, txtCreatedAt, txtKeterangan, txtId, txtBatasBerlaku;
        Button btnUnduh;

        UnduhanViewHolder(View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txt_judul_unduhan);
            txtCreatedAt = itemView.findViewById(R.id.txt_created_at_unduhan);
            txtKeterangan = itemView.findViewById(R.id.txt_keterangan_unduhan);
            txtBatasBerlaku = itemView.findViewById(R.id.txt_batas_tanggal_berlaku_unduhan);
            txtId = itemView.findViewById(R.id.txt_id_unduhan);
            btnUnduh = itemView.findViewById(R.id.btn_unduhan);

            btnUnduh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //unduh
                }
            });
        }

    }
}
