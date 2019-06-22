package untag.daskom.myapplication.adapter.kalab;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import untag.daskom.myapplication.R;
import untag.daskom.myapplication.model.DataMahasiswa;
import untag.daskom.myapplication.model.DataMahasiswaDetail;
import untag.daskom.myapplication.model.DataUser;
import untag.daskom.myapplication.my_interface.MahasiswaDataService;
import untag.daskom.myapplication.network.RetrofitInstance;
import untag.daskom.myapplication.session.SessionManager;

public class DataMahasiswaAdapter extends RecyclerView.Adapter<DataMahasiswaAdapter.DataMahasiswaViewHolder>  {

    private List<DataMahasiswa> dataList;
    Context context;
    Dialog popupDialog;
    SessionManager sessionManager;

    public DataMahasiswaAdapter(List<DataMahasiswa> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public DataMahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row_data_mahasiswa_kalab, parent, false);
        return new DataMahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataMahasiswaViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama_mahasiswa());
        holder.txtNomorInduk.setText(dataList.get(position).getNbi_mahasiswa());
        holder.txtId.setText(dataList.get(position).getId());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataMahasiswaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtNomorInduk, txtId, txtPopupId, txtPopupNama, txtPopupNInduk, txtPopupPraktikum, txtPopupThnPel, txtPopupKelas, txtPopupSemester, txtPopupWa, txtPopupEmail;
        Button btDelete;

        DataMahasiswaViewHolder(View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txt_nama_mahasiswa_kalab);
            txtNomorInduk = itemView.findViewById(R.id.txt_nomor_induk_mahasiswa_kalab);
            txtId = itemView.findViewById(R.id.txt_id_mahasiswa_kalab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                String selectId = txtId.getText().toString();

                popupDialog = new Dialog(v.getContext());

                popupDialog.setContentView(R.layout.kalab_data_mhs_popup);

                txtPopupNama = popupDialog.findViewById(R.id.txt_detail_nama_mhs_on_kalab);
                txtPopupNInduk = popupDialog.findViewById(R.id.txt_detail_nbi_mhs_kalab);
                txtPopupPraktikum = popupDialog.findViewById(R.id.txt_detail_praktikum_mhs_kalab);
                txtPopupSemester = popupDialog.findViewById(R.id.txt_detail_sem_mhs_kalab);
                txtPopupThnPel = popupDialog.findViewById(R.id.txt_detail_thnPel_mhs_kalab);
                txtPopupKelas = popupDialog.findViewById(R.id.txt_detail_kelas_mhs_kalab);
                txtPopupEmail = popupDialog.findViewById(R.id.txt_detail_email_mhs_kalab);
                txtPopupWa = popupDialog.findViewById(R.id.txt_detail_no_wa_mhs_kalab);
                txtPopupId = popupDialog.findViewById(R.id.txt_detail_id_mhs_kalab);
                btDelete = popupDialog.findViewById(R.id.btn_delete_data_mhs_kalab);

                sessionManager = new SessionManager(context);
                final String session = sessionManager.getSessionData().get("ID");

                    MahasiswaDataService service = RetrofitInstance.getRetrofitInstance().create(MahasiswaDataService.class);

                    Call<DataMahasiswaDetail> call = service.getMhsDetail("Bearer "+session, selectId);

                    call.enqueue(new Callback<DataMahasiswaDetail>() {
                        @Override
                        public void onResponse(Call<DataMahasiswaDetail> call, Response<DataMahasiswaDetail> response) {
                            Log.d("data id",response.body().getId());
                            txtPopupNama.setText(response.body().getNama_mahasiswa());
                            txtPopupNInduk.setText(response.body().getNbi_mahasiswa());
                            txtPopupPraktikum.setText(response.body().getNama_praktikum());
                            txtPopupSemester.setText(response.body().getSemester());
                            txtPopupThnPel.setText(response.body().getThn_pel());
                            txtPopupKelas.setText(response.body().getKelas());
                            txtPopupEmail.setText(response.body().getEmail());
                            txtPopupWa.setText(response.body().getWa());
                            txtPopupId.setText(response.body().getId());
                        }

                        @Override
                        public void onFailure(Call<DataMahasiswaDetail> call, Throwable t) {
                            Toast.makeText(context, "Something went wrong....Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                    //aksi btn hapus
                    btDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //hapus
                        }
                    });
                popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupDialog.show();
                }



            });
        }
    }
}