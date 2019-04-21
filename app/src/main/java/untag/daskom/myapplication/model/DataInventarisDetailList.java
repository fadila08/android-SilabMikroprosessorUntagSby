package untag.daskom.myapplication.model;

public class DataInventarisDetailList {

    private String id;
    private String nama_barang;
    private String jumlah;
    private String kondisi;

    public DataInventarisDetailList(String id, String nama_barang, String jumlah, String kondisi) {
        this.id = id;
        this.nama_barang = nama_barang;
        this.jumlah = jumlah;
        this.kondisi = kondisi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }
}
