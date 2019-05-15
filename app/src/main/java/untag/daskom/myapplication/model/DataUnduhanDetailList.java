package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class DataUnduhanDetailList {

    private String id;

    private String judul;

    private String keterangan;

    @SerializedName("diupload pada")
    private String created_at;

    private String file;

    private String batas_tanggal_berlaku;

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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getBatas_tanggal_berlaku() {
        return batas_tanggal_berlaku;
    }

    public void setBatas_tanggal_berlaku(String batas_tanggal_berlaku) {
        this.batas_tanggal_berlaku = batas_tanggal_berlaku;
    }
}
