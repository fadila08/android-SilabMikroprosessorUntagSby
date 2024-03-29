package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PengumumanList {

    @SerializedName("data")
    private ArrayList<PengumumanModel> pengumumanList;

    public ArrayList<PengumumanModel> getPengumumanArrayList() {
        return pengumumanList;
    }

    public void setPengumumanArrayList (ArrayList<PengumumanModel> pengumumanArrayList) {
        this.pengumumanList = pengumumanArrayList;
    }

}
