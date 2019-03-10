package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class DataGaleriDetailList {

    @SerializedName("id")
    private String id_detail;

    @SerializedName("judul")
    private String judul_detail;

    @SerializedName("gambar")
    private String gambar_detail;

    public String getId_detail() {
        return id_detail;
    }

    public void setId_detail(String id_detail) {
        this.id_detail = id_detail;
    }

    public String getJudul_detail() {
        return judul_detail;
    }

    public void setJudul_detail(String judul_detail) {
        this.judul_detail = judul_detail;
    }

    public String getGambar_detail() {
        return gambar_detail;
    }

    public void setGambar_detail(String gambar_detail) {
        this.gambar_detail = gambar_detail;
    }
}
