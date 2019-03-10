package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class GaleriModel {

    @SerializedName("id")
    private String id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("diupload pada")
    private String created_at;

    public GaleriModel(String id, String judul, String gambar, String created_at) {
        this.id = id;
        this.judul = judul;
        this.gambar = gambar;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
