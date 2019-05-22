package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class DataMahasiswa {

    private String id;
    private String nama_mahasiswa;
    private String nbi_mahasiswa;


    public DataMahasiswa(String id, String nama_mahasiswa, String nbi_mahasiswa) {
        this.id = id;
        this.nama_mahasiswa = nama_mahasiswa;
        this.nbi_mahasiswa = nbi_mahasiswa;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }

    public void setNama_mahasiswa(String nama_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getNbi_mahasiswa() {
        return nbi_mahasiswa;
    }

    public void setNbi_mahasiswa(String nbi_mahasiswa) {
        this.nbi_mahasiswa = nbi_mahasiswa;
    }
}
