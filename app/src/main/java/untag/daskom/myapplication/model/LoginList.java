package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginList {

    @SerializedName("user")
    private List<DataLoginList> data;

//    @SerializedName("user")
//    private DataLoginList data;

    @SerializedName("access_token")
    private String access_token;

    public List<DataLoginList> getData() {
        return data;
    }

    public void setData(List<DataLoginList> data) {
        this.data = data;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }






    /*
    private DataPengumumanDetailList data;

    public DataPengumumanDetailList getData() {
        return data;
    }

    public void setData(DataPengumumanDetailList data) {
        this.data = data;
    }
//

    @SerializedName("data")
    private ArrayList<PengumumanModel> pengumumanList;

    public ArrayList<PengumumanModel> getPengumumanArrayList() {
        return pengumumanList;
    }

    public void setPengumumanArrayList (ArrayList<PengumumanModel> pengumumanArrayList) {
        this.pengumumanList = pengumumanArrayList;
    }

     */
}
