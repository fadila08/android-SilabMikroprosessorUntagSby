package untag.daskom.myapplication.model;


import com.google.gson.annotations.SerializedName;

public class DataPengumumanDetailList {

    private String id;
    private String judul;
    private String isi;
    private String file_lampiran;
    private String batas_tanggal_berlaku;

    @SerializedName("diupload pada")
    private String diupload_pada;

    public String getBatas_tanggal_berlaku() {
        return batas_tanggal_berlaku;
    }

    public void setBatas_tanggal_berlaku(String batas_tanggal_berlaku) {
        this.batas_tanggal_berlaku = batas_tanggal_berlaku;
    }

    public String getDiupload_pada() {
        return diupload_pada;
    }

    public void setDiupload_pada(String diupload_pada) {
        this.diupload_pada = diupload_pada;
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

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getFile_lampiran() {
        return file_lampiran;
    }

    public void setFile_lampiran(String file_lampiran) {
        this.file_lampiran = file_lampiran;
    }
}
