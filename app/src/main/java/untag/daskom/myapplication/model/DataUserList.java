package untag.daskom.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataUserList {

    @SerializedName("data")
    private ArrayList<DataUser> dataUserList;

    public ArrayList<DataUser> getDataUserArrayList() {
        return dataUserList;
    }

    public void setDataUserArrayList(ArrayList<DataUser> dataUserArrayList) {
        this.dataUserList = dataUserArrayList;
    }
}
