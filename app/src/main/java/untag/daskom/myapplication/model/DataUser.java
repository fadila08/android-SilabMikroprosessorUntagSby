package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class DataUser {

    @SerializedName("nama")
    private String nama;

    @SerializedName("nomor_induk")
    private String nomor_induk;

    @SerializedName("email")
    private String email;

    @SerializedName("nomor_whatsapp")
    private String nomor_whatsapp;

    public DataUser(String nama, String nomor_induk, String email, String nomor_whatsapp) {
        this.nama = nama;
        this.nomor_induk = nomor_induk;
        this.email = email;
        this.nomor_whatsapp = nomor_whatsapp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor_induk() {
        return nomor_induk;
    }

    public void setNomor_induk(String nomor_induk) {
        this.nomor_induk = nomor_induk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomor_whatsapp() {
        return nomor_whatsapp;
    }

    public void setNomor_whatsapp(String nomor_whatsapp) {
        this.nomor_whatsapp = nomor_whatsapp;
    }
}
