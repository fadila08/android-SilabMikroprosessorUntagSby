package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class PengumumanDetail {

    @SerializedName("judul")
    private String judul;

    @SerializedName("isi")
    private String isi;

    @SerializedName("diupload pada")
    private String created_at;

    @SerializedName("id")
    private String id;

    @SerializedName("file_lampiran")
    private String file_lampiran;

    public PengumumanDetail(String judul, String isi, String created_at, String id, String file_lampiran) {
        this.judul = judul;
        this.isi = isi;
        this.created_at = created_at;
        this.id = id;
        this.file_lampiran = file_lampiran;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile_lampiran() {
        return file_lampiran;
    }

    public void setFile_lampiran(String file_lampiran) {
        this.file_lampiran = file_lampiran;
    }
}
