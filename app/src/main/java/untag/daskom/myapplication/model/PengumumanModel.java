package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;

public class PengumumanModel {

    @SerializedName("judul")
    private String judul;

    @SerializedName("diupload pada")
    private String created_at;

    @SerializedName("batas_tanggal_berlaku")
    private String batas_tanggal_berlaku;

    @SerializedName("id")
    private String id;

    public PengumumanModel(String judul, String created_at, String batas_tanggal_berlaku, String id) {
        this.judul = judul;
        this.created_at = created_at;
        this.batas_tanggal_berlaku = batas_tanggal_berlaku;
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getBatas_tanggal_berlaku() {
        return batas_tanggal_berlaku;
    }

    public void setBatas_tanggal_berlaku(String batas_tanggal_berlaku) {
        this.batas_tanggal_berlaku = batas_tanggal_berlaku;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
