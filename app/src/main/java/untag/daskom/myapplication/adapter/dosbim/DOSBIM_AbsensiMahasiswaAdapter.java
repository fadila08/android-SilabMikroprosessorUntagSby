package untag.daskom.myapplication.adapter.dosbim;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.model.UserDetailList;
import untag.daskom.myapplication.my_interface.GetUserDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class DOSBIM_AbsensiMahasiswaAdapter extends RecyclerView.Adapter<DOSBIM_AbsensiMahasiswaAdapter.AbsensiMahasiswaViewHolder> {

    private ArrayList<DataUser> dataList;
    Dialog popupDialog;
    String id;
    Context context;
    SessionManager sessionManager;

    public DOSBIM_AbsensiMahasiswaAdapter(ArrayList<DataUser> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public AbsensiMahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_absensi_mahasiswa_dosbim, parent, false);
        return new AbsensiMahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AbsensiMahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNomorInduk.setText(dataList.get(position).getNomor_induk());
        holder.txtKehadiran.setText(dataList.get(position).getNomor_induk());
        id = dataList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class AbsensiMahasiswaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtKehadiran, txtPopupNama, txtPopupNInduk, txtPopupPertemuan1, txtPopupPertemuan2, txtPopupPertemuan3, txtPopupPertemuan4;

        AbsensiMahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mahasiswa_dosbim);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_mahasiswa_dosbim);
            txtKehadiran = itemView.findViewById(R.id.txt_kehadiran_mahasiswa_dosbim);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectId = Integer.parseInt(id);
                    Log.d("id", String.valueOf(selectId));

                    popupDialog = new Dialog(v.getContext());

                    popupDialog.setContentView(R.layout.dosbim_absensi_mahasiswa_popup);

                    txtPopupNama                    = popupDialog.findViewById(R.id.txt_detail_nama_mhs_dosbim);
                    txtPopupNInduk                  = popupDialog.findViewById(R.id.txt_detail_nip_mhs_dosbim);
                    txtPopupPertemuan1              = popupDialog.findViewById(R.id.txt_detail_pertemuan1_dosbim);
                    txtPopupPertemuan2              = popupDialog.findViewById(R.id.txt_detail_pertemuan2_dosbim);
                    txtPopupPertemuan3              = popupDialog.findViewById(R.id.txt_detail_pertemuan3_dosbim);
                    txtPopupPertemuan4              = popupDialog.findViewById(R.id.txt_detail_pertemuan4_dosbim);

                    sessionManager = new SessionManager(context);
                    String session = sessionManager.getSessionData().get("ID");

                    /** Create handle for the RetrofitInstance interface*/
                    GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);

                    /** Call the method with parameter in the interface to get the notice data*/
                    Call<UserDetailList> call = service.getAslabDetailDataKalab("Bearer "+session, id);

                    call.enqueue(new Callback<UserDetailList>() {
                        @Override
                        public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                            //sampai sini
                            Log.d("data id",response.body().getData().getId());
                            txtPopupNama.setText(response.body().getData().getNama());
                            txtPopupNInduk.setText(response.body().getData().getNomor_induk());
                            txtPopupPertemuan1.setText(response.body().getData().getNomor_whatsapp());
                            txtPopupPertemuan2.setText(response.body().getData().getEmail());
                            txtPopupPertemuan3.setText(response.body().getData().getEmail());
                            txtPopupPertemuan4.setText(response.body().getData().getEmail());
                        }

                        @Override
                        public void onFailure(Call<UserDetailList> call, Throwable t) {
                            Toast.makeText(context, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupDialog.show();
                }
            });

        }
    }
}